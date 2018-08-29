package com.vincentganneau.swapi.ui.widget;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincentganneau.swapi.model.entity.Planet;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that exposes {@link Planet} objects from a {@link List} to a {@link RecyclerView} widget.
 * @author Vincent Ganneau
 */
public class PlanetListAdapter extends RecyclerView.Adapter<PlanetViewHolder> {

    // Layout inflater
    private final LayoutInflater mLayoutInflater;

    // Planets
    private List<Planet> mPlanets;

    /**
     * Creates a new instance of {@link PlanetListAdapter}.
     * @param context the {@link Context} where the {@link RecyclerView} associated with this {@link PlanetListAdapter} is running.
     */
    public PlanetListAdapter(Context context) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mPlanets != null ? mPlanets.size() : 0;
    }

    @NonNull
    @Override
    public PlanetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new PlanetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetViewHolder holder, int position) {
        final Planet planet = getPlanet(position);
        holder.nameTextView.setText(planet.getName());
    }

    // Getters
    /**
     * Returns the planet associated with the specified position.
     * @param position the position of the planet.
     * @return the {@link Planet} object at the specified position or <code>null</code>.
     */
    public Planet getPlanet(int position) {
        if (position < getItemCount()) {
            return mPlanets.get(position);
        }
        return null;
    }

    // Setters
    /**
     * Sets the planets for this adapter.
     * <p>
     * This method is responsible for calling {@link RecyclerView.Adapter#notifyDataSetChanged()}.
     * </p>
     * @param planets the {@link List} of {@link Planet} objects.
     */
    public void setPlanets(List<Planet> planets) {
        mPlanets = planets;
        notifyDataSetChanged();
    }
}
