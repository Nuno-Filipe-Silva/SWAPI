package com.vincentganneau.swapi.model.repository;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import com.vincentganneau.swapi.model.api.SWApi;
import com.vincentganneau.swapi.model.api.SWApiResponse;
import com.vincentganneau.swapi.model.dao.PlanetDao;
import com.vincentganneau.swapi.model.entity.Planet;

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
import java.util.List;
import java.util.concurrent.Executor;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static org.mockito.Mockito.mock;
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
    @Mock private SWApi mApi;
    @Mock private PlanetDao mPlanetDao;
    private static final Executor mInstantExecutor = Runnable::run;

    // Call
    @Mock private Call<SWApiResponse<Planet>> mCall;

    // Observer
    @Mock private Observer<List<Planet>> mObserver;

    // Repository
    private PlanetRepository mPlanetRepository;

    /**
     * Initializes mock objects and creates an instance of {@link PlanetRepository}.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mPlanetRepository = new PlanetRepository(mApi, mPlanetDao, mInstantExecutor);
    }

    /**
     * Tests the {@link PlanetRepository#getPlanets()} method.
     */
    @Test
    public void testGetPlanets() throws IOException {
        // Given
        final MutableLiveData<List<Planet>> data = new MutableLiveData<>();
        final Planet venus = new Planet("Venus");
        final Planet mercury = new Planet("Mercury");
        final List<Planet> planets = Arrays.asList(mercury, venus);

        // When
        when(mApi.getPlanets()).thenReturn(mCall);
        when(mCall.execute()).thenReturn(Response.error(HttpURLConnection.HTTP_INTERNAL_ERROR, mock(ResponseBody.class)), Response.success(new SWApiResponse<>(planets)));
        when(mPlanetDao.loadPlanets()).thenReturn(data);
        mPlanetRepository.getPlanets().observeForever(mObserver);
        mPlanetRepository.fetchPlanets();
        data.setValue(planets);

        // Then
        verify(mApi, times(2)).getPlanets();
        verify(mPlanetDao).loadPlanets();
        verify(mPlanetDao).insertPlanets(planets);
        verify(mObserver).onChanged(planets);

        // When
        when(mCall.execute()).thenThrow(mock(IOException.class));
        mPlanetRepository.fetchPlanets();

        // Then
        verifyNoMoreInteractions(mPlanetDao);
    }
}
