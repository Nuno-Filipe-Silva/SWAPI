package com.vincentganneau.swapi.testing;

import com.vincentganneau.swapi.model.entity.Planet;

/**
 * Helper class for testing.
 * @author Vincent Ganneau
 */
public class TestUtils {

    // This class can not be instantiated.
    private TestUtils() { }

    /**
     * The planets from the Solar System.
     */
    public static final Planet[] PLANETS = {
            new Planet("Mercury"),
            new Planet("Venus"),
            new Planet("Earth"),
            new Planet("Mars"),
            new Planet("Jupiter"),
            new Planet("Saturn"),
            new Planet("Uranus"),
            new Planet("Neptune")
    };
}
