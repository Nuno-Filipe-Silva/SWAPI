package com.vincentganneau.swapi.injection.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.vincentganneau.swapi.injection.annotation.ViewModelKey;
import com.vincentganneau.swapi.ui.lifecycle.PlanetListViewModel;
import com.vincentganneau.swapi.ui.lifecycle.SWViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Dagger {@link Module} that binds {@link ViewModel} instances into a map under keys given in {@link ViewModelKey}.
 * @author Vincent Ganneau
 */
@Module
public abstract class ViewModelModule {

    /**
     * Binds a {@link ViewModelProvider.Factory} instance to the Dagger graph.
     * @param factory the {@link SWViewModelFactory} instance to bind.
     * @return the {@link ViewModelProvider.Factory} instance.
     */
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(SWViewModelFactory factory);

    /**
     * Binds a {@link PlanetListViewModel} instance to the Dagger graph.
     * @param planetListViewModel the {@link PlanetListViewModel} instance to bind.
     * @return the {@link ViewModel} instance.
     */
    @Binds
    @IntoMap
    @ViewModelKey(PlanetListViewModel.class)
    abstract ViewModel bindPlanetListViewModel(PlanetListViewModel planetListViewModel);
}
