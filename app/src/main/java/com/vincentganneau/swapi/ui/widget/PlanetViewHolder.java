package com.vincentganneau.swapi.ui.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * {@link RecyclerView.ViewHolder} that describes a planet item within a {@link RecyclerView} widget.
 */
public class PlanetViewHolder extends RecyclerView.ViewHolder {

    // Views
    /**
     * The {@link TextView} that holds the planet's name.
     */
    final TextView nameTextView;

    /**
     * Creates a new instance of {@link PlanetViewHolder}.
     * @param itemView the item view.
     */
    public PlanetViewHolder(View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(android.R.id.text1);
    }
}
