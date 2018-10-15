package com.vincentganneau.swapi.model.entity;

import com.vincentganneau.swapi.testing.TestUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class that tests the {@link Planet} entity.
 * @author Vincent Ganneau
 */
@RunWith(JUnit4.class)
public class PlanetTest {

    /**
     * Tests the {@link Planet#equals(Object)} method.
     */
    @Test
    public void testEquals() {
        final Planet one = TestUtils.PLANETS[0];

        // Assert equality when comparing to same object
        assertEquals(one, one);

        // Assert inequality when comparing to null object
        assertNotEquals(one, null);

        // Assert inequality when comparing to an object of different class
        assertNotEquals(one, "");

        // Assert equality when all properties match
        final Planet two = new Planet(one.getName());
        assertEquals(one, two);

        // Assert inequality when name does not match
        two.setName(TestUtils.PLANETS[1].getName());
        assertNotEquals(one, two);
    }

    /**
     * Tests the {@link Planet#hashCode()} method.
     */
    @Test
    public void testHashCode() {
        final Planet one = TestUtils.PLANETS[0];

        // Assert equality when comparing to same object
        assertEquals(one.hashCode(), one.hashCode());

        // Assert equality when all properties match
        final Planet two = new Planet(one.getName());
        assertEquals(one.hashCode(), two.hashCode());

        // Assert inequality when name does not match
        two.setName(TestUtils.PLANETS[1].getName());
        assertNotEquals(one.hashCode(), two.hashCode());
    }

    /**
     * Tests the {@link Planet#DIFF_CALLBACK}.
     */
    @Test
    public void testDiffCallback() {
        final Planet one = TestUtils.PLANETS[0];
        final Planet two = TestUtils.PLANETS[1];

        // Assert items and contents are the same when comparing to same object
        assertTrue(Planet.DIFF_CALLBACK.areItemsTheSame(one, one));
        assertTrue(Planet.DIFF_CALLBACK.areContentsTheSame(one, one));

        // Assert items and contents are different when name does not match
        assertFalse(Planet.DIFF_CALLBACK.areItemsTheSame(one, two));
        assertFalse(Planet.DIFF_CALLBACK.areContentsTheSame(one, two));
    }
}
