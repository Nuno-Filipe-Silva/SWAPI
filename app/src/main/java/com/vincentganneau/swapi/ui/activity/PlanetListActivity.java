package com.vincentganneau.swapi.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vincentganneau.swapi.R;
import com.vincentganneau.swapi.ui.fragment.PlanetListFragment;

import dagger.android.AndroidInjection;

/**
 * {@link android.app.Activity} that displays planets in a list.
 * @author Vincent Ganneau
 */
public class PlanetListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_list);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, new PlanetListFragment())
                    .commit();
        }
    }
}
