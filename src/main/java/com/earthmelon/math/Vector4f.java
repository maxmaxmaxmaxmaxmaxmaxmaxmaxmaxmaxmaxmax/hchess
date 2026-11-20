package com.earthmelon.math;

public class Vector4f {

    private final float x;
    private final float y;
    private final float z;
    private final float w;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getW() {
        return w;
    }

    /**
     * Immutable 3-dimensional vector class.
     * @param x is the first direction of the vector.
     * @param y
     * @param z
     */
    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4f(Vector3f xyz, float w) {
        this.x = xyz.getX();
        this.y = xyz.getY();
        this.z = xyz.getZ();
        this.w = w;
    }
}
