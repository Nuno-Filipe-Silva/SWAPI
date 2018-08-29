package com.vincentganneau.swapi.ui.lifecycle;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * {@link ViewModelProvider.Factory} implementation that is responsible for instantiating {@link ViewModel} instances.
 * @author Google Samples
 */
@Singleton
public class SWViewModelFactory implements ViewModelProvider.Factory {

    // View model providers
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> mCreators;

    /**
     * Creates a new instance of {@link SWViewModelFactory}.
     * @param creators the {@link Map} of {@link ViewModel} providers.
     */
    @Inject
    public SWViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        mCreators = creators;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<? extends ViewModel> creator = mCreators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : mCreators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("Unknown model class " + modelClass);
        }
        try {
            return (T) creator.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
