package com.vincentganneau.swapi.model.entity;

import com.vincentganneau.swapi.testing.TestUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

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
        assertTrue(one.equals(one));

        // Assert inequality when comparing to null object
        assertFalse(one.equals(null));

        // Assert inequality when comparing to an object of different class
        assertFalse(one.equals(""));

        // Assert equality when all properties match
        final Planet two = new Planet(one.getName());
        assertTrue(one.equals(two));

        // Assert inequality when name does not match
        two.setName(TestUtils.PLANETS[1].getName());
        assertFalse(one.equals(two));
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
}
