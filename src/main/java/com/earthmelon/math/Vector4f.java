package com.earthmelon.math;

public class Vector4f {

    private final float x;
    private final float y;
    private final float z;
    private final float a;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getA() {
        return a;
    }

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

    public Vector4f(Vector3f xyz, float a) {
        this.x = xyz.getX();
        this.y = xyz.getY();
        this.z = xyz.getZ();
        this.a = a;
    }
}
