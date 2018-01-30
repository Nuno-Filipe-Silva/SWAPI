package com.vincentganneau.swapi.injection.module;

import com.vincentganneau.swapi.ui.activity.PlanetListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Dagger {@link Module} that provides {@link android.app.Activity} related instances.
 * @author Vincent Ganneau
 */
@Module(includes = FragmentModule.class)
public abstract class ActivityModule {

    /**
     * Binds a {@link PlanetListActivity} instance to the Dagger graph.
     * @return the {@link PlanetListActivity} instance.
     */
    @ContributesAndroidInjector
    abstract PlanetListActivity bindPlanetListActivity();
}
