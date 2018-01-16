package com.vincentganneau.swapi.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Objects;

/**
 * {@link Entity} that stores data from a planet resource.
 * <p>
 * A planet resource is a large mass, planet or planetoid in the Star Wars Universe, at the time of 0 ABY.
 * </p>
 * @author Vincent Ganneau
 */
@Entity
public class Planet {

    // Contract
    /**
     * The table that stores planets.
     */
    public static final String TABLE_NAME = "planets";
    /**
     * Unique string identifying the name column.
     */
    public static final String COLUMN_NAME = "name";

    // Properties
    @PrimaryKey
    @ColumnInfo(name = COLUMN_NAME)
    private String mName;

    // Setters
    /**
     * Sets the name for this {@link Planet}.
     * @param name the name for this {@link Planet}.
     */
    public void setName(String name) {
        mName = name;
    }

    // Getters
    /**
     * Returns the name for this {@link Planet}.
     * @return the name for this {@link Planet}.
     */
    public String getName() {
        return mName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || !(object instanceof Planet)) {
            return false;
        }
        final Planet planet = (Planet) object;
        return Objects.equals(mName, planet.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(mName);
    }
}
