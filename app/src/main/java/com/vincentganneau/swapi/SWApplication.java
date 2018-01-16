package com.vincentganneau.swapi;

import android.app.Activity;
import android.app.Application;

import com.vincentganneau.swapi.injection.component.AppComponent;
import com.vincentganneau.swapi.injection.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * The Star Wars {@link Application}.
 * <p>
 * Builds the graph of dependencies using {@link AppComponent}.
 * @author Vincent Ganneau
 */
public class SWApplication extends Application implements HasActivityInjector {

    /**
     * The {@link Activity} injector.
     */
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
