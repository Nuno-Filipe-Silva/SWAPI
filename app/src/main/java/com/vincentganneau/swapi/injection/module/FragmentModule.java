package com.vincentganneau.swapi.injection.module;

import com.vincentganneau.swapi.ui.fragment.PlanetListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Dagger {@link Module} that provides {@link android.support.v4.app.Fragment} related instances.
 * @author Vincent Ganneau
 */
@Module
public abstract class FragmentModule {

    /**
     * Binds a {@link PlanetListFragment} instance to the Dagger graph.
     * @return the {@link PlanetListFragment} instance.
     */
    @ContributesAndroidInjector
    abstract PlanetListFragment bindPlanetListFragment();
}
