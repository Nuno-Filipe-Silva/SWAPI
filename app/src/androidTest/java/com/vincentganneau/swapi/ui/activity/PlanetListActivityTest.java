package com.vincentganneau.swapi.ui.activity;

import android.arch.lifecycle.MutableLiveData;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import com.vincentganneau.swapi.R;
import com.vincentganneau.swapi.testing.ViewModelUtils;
import com.vincentganneau.swapi.ui.fragment.PlanetListFragment;
import com.vincentganneau.swapi.ui.lifecycle.PlanetListViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Class that tests the {@link PlanetListActivity} class.
 * @author Vincent Ganneau
 */
@RunWith(AndroidJUnit4.class)
public class PlanetListActivityTest extends FragmentActivityTest<PlanetListActivity> {

    // Dependencies
    @Mock private PlanetListViewModel mPlanetListViewModel;

    @Override
    protected Class<PlanetListActivity> getActivityClass() {
        return PlanetListActivity.class;
    }

    @Override
    protected void initMocks() {
        super.initMocks();
        when(mPlanetListViewModel.getPlanets()).thenReturn(new MutableLiveData<>());
    }

    @Override
    protected void onFragmentCreated(Fragment fragment) {
        super.onFragmentCreated(fragment);
        if (fragment instanceof PlanetListFragment) {
            ((PlanetListFragment) fragment).viewModelFactory = ViewModelUtils.createFor(mPlanetListViewModel);
        }
    }

    /**
     * Tests the {@link Fragment} identified by {@link R.id#fragment}.
     */
    @Test
    public void testFragment() {
        assertEquals(PlanetListFragment.class, activityRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment).getClass());
    }
}