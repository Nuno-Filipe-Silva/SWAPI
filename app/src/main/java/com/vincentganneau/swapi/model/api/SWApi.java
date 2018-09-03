package com.vincentganneau.swapi.model.api;

import com.vincentganneau.swapi.model.entity.Planet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface that exposes the Star Wars API.
 * @author Vincent Ganneau
 */
public interface SWApi {

    /**
     * Fetches all the planets at a given page.
     * @param page the page index (starting from <code>1</code>).
     * @return a {@link Call} request to the Star Wars API that returns a {@link SWApiResponse} with {@link Planet} objects.
     */
    @GET("planets")
    Call<SWApiResponse<Planet>> getPlanets(@Query("page") int page);
}
