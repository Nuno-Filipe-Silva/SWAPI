package com.vincentganneau.swapi;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.vincentganneau.swapi.injection.component.AppComponent;
import com.vincentganneau.swapi.injection.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * The Star Wars {@link Application}.
 * <p>
 * Builds the graph of dependencies using {@link AppComponent}.
 * </p>
 * @author Vincent Ganneau
 */
public class SWApplication extends Application implements HasActivityInjector, HasSupportFragmentInjector {

    /**
     * The {@link Activity} injector.
     */
    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    /**
     * The support {@link Fragment} injector.
     */
    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;

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
        return activityInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }
}
