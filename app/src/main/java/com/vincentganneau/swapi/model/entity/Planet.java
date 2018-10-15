package com.vincentganneau.swapi.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * {@link Entity} that stores data from a planet resource.
 * <p>
 * A planet resource is a large mass, planet or planetoid in the Star Wars Universe, at the time of 0 ABY.
 * </p>
 * @author Vincent Ganneau
 */
@Entity(tableName = Planet.TABLE_NAME)
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
    @NonNull
    @ColumnInfo(name = COLUMN_NAME)
    @SerializedName("name")
    private String mName;

    // Constructors
    /**
     * Creates a new instance of {@link Planet} with the given name.
     * @param name the name for the planet.
     */
    public Planet(@NonNull String name) {
        mName = name;
    }

    // Getters
    /**
     * Returns the name for this planet.
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
        if (!(object instanceof Planet)) {
            return false;
        }
        final Planet planet = (Planet) object;
        return Objects.equals(mName, planet.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(mName);
    }

    // Setters
    /**
     * Sets the name for this planet.
     * @param name the name for this {@link Planet}.
     */
    public void setName(String name) {
        mName = name;
    }

    // Paging
    /**
     * Callback for calculating the diff between two non-null {@link Planet} items in a list.
     */
    public static DiffUtil.ItemCallback<Planet> DIFF_CALLBACK = new DiffUtil.ItemCallback<Planet>() {

        @Override
        public boolean areItemsTheSame(@NonNull Planet oldItem, @NonNull Planet newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Planet oldItem, @NonNull Planet newItem) {
            return oldItem.equals(newItem);
        }
    };
}
