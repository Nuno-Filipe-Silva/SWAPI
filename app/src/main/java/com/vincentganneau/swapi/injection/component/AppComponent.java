package com.vincentganneau.swapi.injection.component;

import android.app.Application;

import com.vincentganneau.swapi.SWApplication;
import com.vincentganneau.swapi.injection.module.ActivityModule;
import com.vincentganneau.swapi.injection.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Dagger {@link Component} that provides injected instances by using modules.
 * <p>
 * This {@link Component} is responsible for providing application scope instances.<br>
 * This {@link Component} is the root of the Dagger graph.<br>
 * This {@link Component} is providing <b>3</b> modules:
 * <ul>
 *     <li>{@link AndroidInjectionModule}: contains bindings to ensure the usability of Android framework classes.</li>
 *     <li>{@link AppModule}: provides application scope instances.</li>
 *     <li>{@link ActivityModule}: provides {@link android.app.Activity} related instances.</li>
 * </ul>
 * </p>
 * @author Vincent Ganneau
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityModule.class})
public interface AppComponent {

    /**
     * {@link Component.Builder} that enables to bind an {@link Application} instance to the Dagger graph.
     */
    @Component.Builder
    interface Builder {

        /**
         * Binds an {@link Application} instance to the Dagger graph.
         * @param application the {@link Application} instance.
         * @return the {@link Builder} instance.
         */
        @BindsInstance Builder application(Application application);

        /**
         * Builds the Dagger graph.
         * @return the {@link AppComponent} instance.
         */
        AppComponent build();
    }

    /**
     * Injects the {@link SWApplication} instance into the graph.
     * @param application the {@link SWApplication} instance.
     */
    void inject(SWApplication application);
}
