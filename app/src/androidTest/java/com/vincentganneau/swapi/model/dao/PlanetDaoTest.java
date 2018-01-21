package com.vincentganneau.swapi.model.dao;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.vincentganneau.swapi.model.database.SWDatabase;
import com.vincentganneau.swapi.model.entity.Planet;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Class that tests the {@link PlanetDao}.
 * @author Vincent Ganneau
 */
@RunWith(AndroidJUnit4.class)
public class PlanetDaoTest {

    /**
     * The test database.
     */
    private SWDatabase mDatabase;

    /**
     * The rule that makes sure {@link Room} executes all the database operations instantly.
     */
    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    // Observer
    @Mock private Observer<List<Planet>> mObserver;

    /**
     * Initializes mock objects and builds the database by allowing main thread queries for the purpose of testing.
     */
    @Before
    public void buildDatabase() {
        MockitoAnnotations.initMocks(this);
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), SWDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    /**
     * Tests inserting and loading planets.
     */
    @Test
    public void testInsertAndLoadPlanets() {
        // Given
        final Planet venus = new Planet("Venus");
        final Planet mercury = new Planet("Mercury");
        final List<Planet> planets = Arrays.asList(mercury, venus);
        mDatabase.planetDao().loadPlanets().observeForever(mObserver);

        // When
        mDatabase.planetDao().insertPlanet(venus);
        mDatabase.planetDao().insertPlanet(mercury);
        mDatabase.planetDao().insertPlanets(planets);

        // Then
        verify(mObserver, times(1)).onChanged(Collections.emptyList());
        verify(mObserver, times(1)).onChanged(Collections.singletonList(venus));
        verify(mObserver, times(2)).onChanged(planets);
    }

    /**
     * Closes the database.
     */
    @After
    public void closeDatabase() {
        mDatabase.close();
    }
}
