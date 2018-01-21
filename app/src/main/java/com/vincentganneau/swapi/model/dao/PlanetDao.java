package com.vincentganneau.swapi.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.vincentganneau.swapi.model.entity.Planet;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * The {@link Planet} data access object.
 * @author Vincent Ganneau
 */
@Dao
public interface PlanetDao {

    /**
     * Inserts a {@link Planet} object in the database.
     * @param planet the {@link Planet} object to be inserted.
     */
    @Insert(onConflict = REPLACE)
    void insertPlanet(Planet planet);

    /**
     * Inserts a {@link List} of {@link Planet} objects in the database.
     * @param planets the {@link List} of {@link Planet} objects to be inserted.
     */
    @Insert(onConflict = REPLACE)
    void insertPlanets(List<Planet> planets);

    /**
     * Loads all {@link Planet} objects from the database.
     * @return the {@link List} of {@link Planet} objects as an observable {@link LiveData}
     */
    @Query("SELECT * FROM " + Planet.TABLE_NAME + " ORDER BY " + Planet.COLUMN_NAME)
    LiveData<List<Planet>> loadPlanets();
}
