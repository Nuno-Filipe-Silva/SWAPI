package com.vincentganneau.swapi.model.api;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Generic class that handles a Star Wars API response.
 * @author Vincent Ganneau
 */
public class SWApiResponse<T> {

    // Properties
    @SerializedName("results")
    private List<T> mResults;

    /**
     * Creates a new instance of {@link SWApiResponse}.
     * @param results the results from the response as a {@link List}.
     */
    public SWApiResponse(@Nullable List<T> results) {
        setResults(results);
    }

    // Getters
    /**
     * Returns the results from the response.
     * @return the results as a {@link List}.
     */
    public List<T> getResults() {
        return mResults;
    }

    // Setters
    /**
     * Sets the results for the response.
     * @param results the results as a {@link List}.
     */
    public void setResults(List<T> results) {
        mResults = results;
    }
}
