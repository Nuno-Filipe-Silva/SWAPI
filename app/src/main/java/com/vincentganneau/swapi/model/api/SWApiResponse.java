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

    @SerializedName("next")
    private String mNext;

    /**
     * Creates a new instance of {@link SWApiResponse}.
     */
    public SWApiResponse() {
        this(null, null);
    }

    /**
     * Creates a new instance of {@link SWApiResponse}.
     * @param results the results from the response as a {@link List}.
     */
    public SWApiResponse(@Nullable List<T> results) {
        this(results, null);
    }

    /**
     * Creates a new instance of {@link SWApiResponse}.
     * @param results the results from the response as a {@link List}.
     * @param next the next page URL if there are more results to fetch or <code>null</code> otherwise.
     */
    public SWApiResponse(@Nullable List<T> results, @Nullable String next) {
        setResults(results);
        setNext(next);
    }

    // Getters
    /**
     * Returns the results from the response.
     * @return the results as a {@link List}.
     */
    public @Nullable List<T> getResults() {
        return mResults;
    }

    /**
     * Indicates whether there are more results to fetch (by increasing the page index).
     * @return <code>true</code> if there are more results to fetch, <code>false</code> otherwise.
     */
    public boolean hasMoreResults() {
        return mNext != null;
    }

    // Setters
    /**
     * Sets the next page URL.
     * @param next the next page URL if there are more results to fetch or <code>null</code> otherwise.
     */
    public void setNext(@Nullable String next) {
        mNext = next;
    }

    /**
     * Sets the results for the response.
     * @param results the results as a {@link List}.
     */
    public void setResults(@Nullable List<T> results) {
        mResults = results;
    }
}
