package com.vincentganneau.swapi.ui.lifecycle;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import com.vincentganneau.swapi.model.entity.Planet;
import com.vincentganneau.swapi.model.repository.PlanetRepository;
import com.vincentganneau.swapi.testing.TestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Class that tests the {@link PlanetListViewModel} class.
 * @author Vincent Ganneau
 */
@RunWith(JUnit4.class)
public class PlanetListViewModelTest {

    /**
     * The rule that forces Architecture Components to instantly execute any background operation on the calling thread.
     */
    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    // Dependencies
    @Mock private PlanetRepository mPlanetRepository;

    // Observer
    @Mock private Observer<List<Planet>> mObserver;

    // View model
    private PlanetListViewModel mPlanetListViewModel;

    /**
     * Initializes mock objects and creates an instance of {@link PlanetListViewModel}.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mPlanetListViewModel = new PlanetListViewModel(mPlanetRepository);
    }

    /**
     * Tests the {@link PlanetListViewModel#getPlanets()} method.
     */
    @Test
    public void testGetPlanets() {
        // Given
        final MutableLiveData<List<Planet>> data = new MutableLiveData<>();
        final List<Planet> planets = Arrays.asList(TestUtils.PLANETS[0], TestUtils.PLANETS[1]);

        // When
        when(mPlanetRepository.getPlanets()).thenReturn(data);
        mPlanetListViewModel.getPlanets().observeForever(mObserver);
        data.setValue(Arrays.asList(TestUtils.PLANETS[0], TestUtils.PLANETS[1]));

        // Then
        verify(mObserver).onChanged(planets);
    }
}
