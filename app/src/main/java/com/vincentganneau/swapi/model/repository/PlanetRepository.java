package com.vincentganneau.swapi.model.repository;

import android.arch.lifecycle.LiveData;

import com.vincentganneau.swapi.model.api.SWApi;
import com.vincentganneau.swapi.model.dao.PlanetDao;
import com.vincentganneau.swapi.model.entity.Planet;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * Repository that handles operations on {@link Planet}s.
 * @author Vincent Ganneau
 */
public class PlanetRepository {

    // Dependencies
    private final SWApi mApi;
    private final PlanetDao mPlanetDao;
    private final Executor mExecutor;

    /**
     * Creates a new instance of {@link PlanetRepository} by injecting dependencies.
     * @param api the instance of {@link SWApi}.
     * @param planetDao the instance of {@link PlanetDao}.
     * @param executor the instance of {@link Executor}.
     */
    @Inject
    public PlanetRepository(SWApi api, PlanetDao planetDao, Executor executor) {
        mApi = api;
        mPlanetDao = planetDao;
        mExecutor = executor;
    }

    /**
     * Gets all the planets.
     * @return the {@link List} of {@link Planet} objects as an observable {@link LiveData}.
     */
    public LiveData<List<Planet>> getPlanets() {
        fetchPlanets();
        return mPlanetDao.loadPlanets();
    }

    /**
     * Fetches all the planets asynchronously.
     */
    public void fetchPlanets() {
        mExecutor.execute(() -> {
            try {
                final Response<List<Planet>> response = mApi.getPlanets().execute();
                if (response.isSuccessful()) {
                    mPlanetDao.insertPlanets(response.body());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
