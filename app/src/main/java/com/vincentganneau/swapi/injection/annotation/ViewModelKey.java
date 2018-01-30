package com.vincentganneau.swapi.injection.annotation;

import android.arch.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * Defines a key type annotation for binding {@link ViewModel} instances into injected map.
 * @author Google Samples
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {

    /**
     * Returns the {@link ViewModel} instance value to which a specified key is mapped.
     * @return the {@link ViewModel} instance value.
     */
    Class<? extends ViewModel> value();
}
