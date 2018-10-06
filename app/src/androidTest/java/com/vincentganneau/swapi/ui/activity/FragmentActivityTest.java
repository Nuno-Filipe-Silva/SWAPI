package com.vincentganneau.swapi.ui.activity;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.vincentganneau.swapi.testing.SWTestApplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.mockito.MockitoAnnotations;

/**
 * Base class that tests {@link FragmentActivity} subclasses.
 * @author Vincent Ganneau
 */
public abstract class FragmentActivityTest<T extends FragmentActivity> implements Application.ActivityLifecycleCallbacks {

    // Rule
    @Rule
    public final ActivityTestRule<T> activityRule = new ActivityTestRule<>(getActivityClass(), true, false);

    /**
     * Returns the {@link FragmentActivity} subclass being tested.
     * @return the {@link FragmentActivity} subclass.
     */
    protected abstract Class<T> getActivityClass();

    /**
     * Initializes mock objects and registers activity lifecycle callbacks.
     */
    @Before
    public final void setUp() {
        MockitoAnnotations.initMocks(this);
        initMocks();
        final SWTestApplication application = (SWTestApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        application.registerActivityLifecycleCallbacks(this);
        activityRule.launchActivity(null);
    }

    /**
     * Unregisters activity lifecycle callbacks.
     */
    @After
    public final void tearDown() {
        final SWTestApplication application = (SWTestApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        application.unregisterActivityLifecycleCallbacks(this);
    }

    /**
     * Initializes objects annotated with Mockito annotations.
     */
    protected void initMocks() {
        // Default implementation.
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {

            @Override
            public void onFragmentCreated(@NonNull FragmentManager fm, @NonNull Fragment f, Bundle savedInstanceState) {
                FragmentActivityTest.this.onFragmentCreated(f);
            }
        }, true);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        // Default implementation.
    }

    @Override
    public void onActivityResumed(Activity activity) {
        // Default implementation.
    }

    @Override
    public void onActivityPaused(Activity activity) {
        // Default implementation.
    }

    @Override
    public void onActivityStopped(Activity activity) {
        // Default implementation.
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        // Default implementation.
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        // Default implementation.
    }

    /**
     * Called after the {@link Fragment} has returned from the {@link FragmentManager}'s call to {@link Fragment#onCreate(Bundle)}.
     * @param fragment the {@link Fragment} changing state.
     */
    protected void onFragmentCreated(Fragment fragment) {
        // Default implementation.
    }
}