package com.vincentganneau.swapi.testing;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * Helper class that creates {@link ViewModelProvider.Factory} instances for tests.
 * @author Vincent Ganneau
 */
public class ViewModelUtils {

    // This class can not be instantiated.
    private ViewModelUtils() { }

    /**
     * Creates a one off {@link ViewModelProvider.Factory} for a given {@link ViewModel} instance.
     * @param model the {@link ViewModel} instance.
     * @return the {@link ViewModelProvider.Factory}.
     */
    public static <T extends ViewModel> ViewModelProvider.Factory createFor(T model) {
        return new ViewModelProvider.Factory() {

            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if (modelClass.isAssignableFrom(model.getClass())) {
                    return (T) model;
                }
                throw new IllegalArgumentException("Unexpected model class " + modelClass);
            }
        };
    }
}
