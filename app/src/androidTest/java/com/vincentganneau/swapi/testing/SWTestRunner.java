package com.vincentganneau.swapi.testing;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * {@link AndroidJUnitRunner} subclass that disables dependency injection.
 * @author Vincent Ganneau
 */
public class SWTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, SWTestApplication.class.getName(), context);
    }
}
