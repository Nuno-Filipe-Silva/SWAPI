package com.vincentganneau.swapi.model.api;

import com.vincentganneau.swapi.model.entity.Planet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface that exposes the Star Wars API.
 * @author Vincent Ganneau
 */
public interface SWApi {

    /**
     * Fetches all the planets.
     * @return a {@link Call} request to the Star Wars API that returns a {@link List} of {@link Planet} objects.
     */
    @GET("planets")
    Call<List<Planet>> getPlanets();
}
