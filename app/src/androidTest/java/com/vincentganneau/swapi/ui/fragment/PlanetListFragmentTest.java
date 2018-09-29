package com.vincentganneau.swapi.ui.fragment;

import android.arch.lifecycle.MutableLiveData;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vincentganneau.swapi.model.entity.Planet;
import com.vincentganneau.swapi.testing.SingleFragmentActivity;
import com.vincentganneau.swapi.testing.TestUtils;
import com.vincentganneau.swapi.testing.ViewModelUtils;
import com.vincentganneau.swapi.ui.lifecycle.PlanetListViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.mockito.Mockito.when;

/**
 * Class that tests the {@link PlanetListFragment} class.
 * @author Vincent Ganneau
 */
@RunWith(AndroidJUnit4.class)
public class PlanetListFragmentTest {

    // Rule
    @Rule
    public final ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class, true, true);

    // Dependencies
    @Mock private PlanetListViewModel mPlanetListViewModel;

    // Planets
    private final MutableLiveData<List<Planet>> mPlanets = new MutableLiveData<>();

    // Fragment
    private final PlanetListFragment mFragment = new PlanetListFragment();

    /**
     * Initializes mock objects and sets the fragment to be tested.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mPlanetListViewModel.getPlanets()).thenReturn(mPlanets);
        mFragment.viewModelFactory = ViewModelUtils.createFor(mPlanetListViewModel);
        activityRule.getActivity().setFragment(mFragment);
    }

    /**
     * Tests the {@link com.vincentganneau.swapi.ui.widget.PlanetListAdapter}.
     */
    @Test
    public void testAdapter() {
        // Given
        final List<Planet> planets = Arrays.asList(TestUtils.PLANETS[0], TestUtils.PLANETS[1]);

        // When
        mPlanets.postValue(planets);

        // Then
        onView(allOf(withText(TestUtils.PLANETS[0].getName()), withId(android.R.id.text1))).check(matches(isDisplayed()));
        onView(allOf(withText(TestUtils.PLANETS[1].getName()), withId(android.R.id.text1))).check(matches(isDisplayed()));
    }
}