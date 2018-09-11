package com.vincentganneau.swapi.testing;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * {@link Application} subclass for tests to prevent initializing dependency injection.
 * @see SWTestRunner
 * @author Vincent Ganneau
 */
public class SWTestApplication extends Application implements HasActivityInjector, HasSupportFragmentInjector {

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activity -> { };
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragment -> { };
    }
}
