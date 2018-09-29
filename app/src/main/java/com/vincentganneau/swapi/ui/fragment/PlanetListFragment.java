package com.vincentganneau.swapi.ui.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincentganneau.swapi.R;
import com.vincentganneau.swapi.model.entity.Planet;
import com.vincentganneau.swapi.ui.lifecycle.PlanetListViewModel;
import com.vincentganneau.swapi.ui.widget.PlanetListAdapter;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * {@link Fragment} that displays planets in a list.
 * @author Vincent Ganneau
 */
public class PlanetListFragment extends Fragment {

    // View model factory
    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    // Adapter
    private PlanetListAdapter mPlanetListAdapter;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planet_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set views
        final RecyclerView recyclerView = view.findViewById(android.R.id.list);
        recyclerView.setAdapter(mPlanetListAdapter = new PlanetListAdapter(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Setup view model
        final PlanetListViewModel planetListViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(PlanetListViewModel.class);
        planetListViewModel.getPlanets().observe(this, this::onPlanetsChanged);
    }

    /**
     * Callback invoked when the {@link PlanetListViewModel#getPlanets()} is changed.
     * @param planets the {@link List} of {@link Planet} objects.
     */
    private void onPlanetsChanged(List<Planet> planets) {
        mPlanetListAdapter.setPlanets(planets);
    }
}
