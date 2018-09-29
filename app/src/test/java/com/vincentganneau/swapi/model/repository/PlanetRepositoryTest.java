package com.vincentganneau.swapi.model.repository;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import com.google.gson.Gson;
import com.vincentganneau.swapi.model.api.SWApi;
import com.vincentganneau.swapi.model.api.SWApiResponse;
import com.vincentganneau.swapi.model.dao.PlanetDao;
import com.vincentganneau.swapi.model.entity.Planet;
import com.vincentganneau.swapi.testing.TestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

import okhttp3.ResponseBody;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Class that tests the {@link PlanetRepository} class.
 * @author Vincent Ganneau
 */
@RunWith(JUnit4.class)
public class PlanetRepositoryTest {

    /**
     * The rule that forces Architecture Components to instantly execute any background operation on the calling thread.
     */
    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    // Dependencies
    @Mock private PlanetDao mPlanetDao;
    private static final Executor mInstantExecutor = Runnable::run;

    // Call
    @Mock private Call<SWApiResponse<Planet>> mCall;

    // Observer
    @Mock private Observer<List<Planet>> mObserver;

    /**
     * Initializes mock objects and creates an instance of {@link PlanetRepository}.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Tests the {@link PlanetRepository#getPlanets()} method.
     */
    @Test
    public void testGetPlanets() throws IOException {
        // Given
        final SWApi api = mock(SWApi.class);
        final MutableLiveData<List<Planet>> data = new MutableLiveData<>();
        final List<Planet> planets = Arrays.asList(TestUtils.PLANETS[0], TestUtils.PLANETS[1]);
        final PlanetRepository planetRepository = new PlanetRepository(api, mPlanetDao, mInstantExecutor);

        // When
        when(api.getPlanets(1)).thenReturn(mCall);
        when(mCall.execute()).thenReturn(Response.error(HttpURLConnection.HTTP_INTERNAL_ERROR, mock(ResponseBody.class)));
        when(mPlanetDao.loadPlanets()).thenReturn(data);
        planetRepository.getPlanets().observeForever(mObserver);

        // Then
        verify(api).getPlanets(1);
        verify(mPlanetDao).loadPlanets();
        verify(mPlanetDao, never()).insertPlanets(planets);

        // When
        when(mCall.execute()).thenReturn(Response.success(new SWApiResponse<>(planets)));
        planetRepository.fetchPlanets();
        data.setValue(planets);

        // Then
        verify(api, times(2)).getPlanets(1);
        verify(mPlanetDao).insertPlanets(planets);
        verify(mObserver).onChanged(planets);

        // When
        when(mCall.execute()).thenThrow(mock(IOException.class));
        planetRepository.fetchPlanets();

        // Then
        verifyNoMoreInteractions(mPlanetDao);
    }

    /**
     * Tests the {@link PlanetRepository#fetchPlanets()} method.
     */
    @Test
    public void testFetchPlanets() throws IOException, InterruptedException {
        // Given
        final MockWebServer server = new MockWebServer();
        server.setDispatcher(new PlanetDispatcher());
        final SWApi api = new Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SWApi.class);
        final PlanetRepository planetRepository = new PlanetRepository(api, mPlanetDao, mInstantExecutor);

        // When
        planetRepository.fetchPlanets();

        // Then
        assertEquals("/planets?page=1", server.takeRequest().getPath());
        assertEquals("/planets?page=2", server.takeRequest().getPath());
        verify(mPlanetDao).insertPlanets(Collections.singletonList(TestUtils.PLANETS[0]));
        verify(mPlanetDao).insertPlanets(Collections.singletonList(TestUtils.PLANETS[1]));
        server.shutdown();
    }

    /**
     * Handler for mock server requests.
     */
    private class PlanetDispatcher extends Dispatcher {

        @Override
        public MockResponse dispatch(RecordedRequest request) {
            final SWApiResponse<Planet> response = new SWApiResponse<>();
            if (request.getPath().equals("/planets?page=1")) {
                response.setResults(Collections.singletonList(TestUtils.PLANETS[0]));
                response.setNext("/planets?page=2");
            } else if (request.getPath().equals("/planets?page=2")) {
                response.setResults(Collections.singletonList(TestUtils.PLANETS[1]));
            }
            return new MockResponse().setResponseCode(200).setBody(new Gson().toJson(response));
        }
    }
}
