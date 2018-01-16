package com.vincentganneau.swapi.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger {@link Module} that provides application scope instances.
 * @author Vincent Ganneau
 */
@Module
public class AppModule {

    /**
     * Provides the application {@link Context} instance as a singleton.
     * @param application the {@link Application} instance.
     * @return the application {@link Context} instance.
     */
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }
}
