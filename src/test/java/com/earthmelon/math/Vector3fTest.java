package com.earthmelon.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector3fTest {

    Vector3f zero;
    Vector3f random;

    @BeforeEach
    public void makeVecs() {
        zero = new Vector3f(0,0,0);
        random = new Vector3f((float) (Math.random()*1000), (float) (Math.random()*1000), (float) (Math.random()*1000));
    }

    @Test
    public void testPlus() {
        assertEquals(zero, zero.plus(0,0,0));
        assertEquals(new Vector3f(1,2,3), zero.plus(new Vector3f(1,2,3)));
    }
}
