package com.earthmelon.math;

public class Vector4f {

    public final float x;
    public final float y;
    public final float z;
    public final float a;

    /**
     * Immutable 3-dimensional vector class.
     * @param x is the first direction of the vector.
     * @param y
     * @param z
     */
    public Vector4f(float x, float y, float z, float a) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.a = a;
    }
}
