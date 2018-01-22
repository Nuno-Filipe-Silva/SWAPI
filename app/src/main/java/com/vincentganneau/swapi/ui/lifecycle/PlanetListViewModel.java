package com.vincentganneau.swapi.ui.lifecycle;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.vincentganneau.swapi.model.entity.Planet;
import com.vincentganneau.swapi.model.repository.PlanetRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * {@link ViewModel} that is responsible for preparing and managing planets.
 * @author Vincent Ganneau
 */
public class PlanetListViewModel extends ViewModel {

    // Dependencies
    private final PlanetRepository mPlanetRepository;

    // Observables
    private final MutableLiveData<List<Planet>> mPlanets = new MutableLiveData<>();

    /**
     * Creates a new instance of {@link PlanetListViewModel} by injecting an instance of {@link PlanetRepository}.
     * @param planetRepository the instance of {@link PlanetRepository} that will be provided from {@link } TODO: method.
     */
    @Inject
    public PlanetListViewModel(PlanetRepository planetRepository) {
        mPlanetRepository = planetRepository;
    }

    /**
     * Exposes the {@link List} of {@link Planet} objects as an observable {@link LiveData} so the UI can observe it.
     * @return the {@link List} of {@link Planet} objects as an observable {@link LiveData}.
     */
    public LiveData<List<Planet>> getPlanets() {
        return mPlanetRepository.getPlanets();
    }
}
