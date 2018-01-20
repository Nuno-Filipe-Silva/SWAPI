package com.vincentganneau.swapi.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.vincentganneau.swapi.model.dao.PlanetDao;
import com.vincentganneau.swapi.model.entity.Planet;

/**
 * The Star Wars database.
 * <p>
 * {@link Room} automatically provides an implementation of it when calling {@link Room#databaseBuilder(Context, Class, String)} method.
 * </p>
 */
@Database(entities = {Planet.class}, version = 1, exportSchema = false)
public abstract class SWDatabase extends RoomDatabase {

    /**
     * Returns the {@link PlanetDao}.
     * @return the {@link PlanetDao}.
     */
    public abstract PlanetDao planetDao();
}
