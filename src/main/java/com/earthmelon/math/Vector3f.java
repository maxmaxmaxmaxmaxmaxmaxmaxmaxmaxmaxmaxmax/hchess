package com.earthmelon.math;

public class Vector3f {

    private final float x;
    private final float y;
    private final float z;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    /**
     * Immutable 3-dimensional vector class.
     * @param x is the first direction of the vector.
     * @param y is the second direction of the vector.
     * @param z is the third direction of the vector.
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f plus(Vector3f vec) {
        return new Vector3f(x + vec.x, y + vec.y, z + vec.z);
    }

    public Vector3f plus(float f1, float f2, float f3) {
        return new Vector3f(x + f1, y + f2, z + f3);
    }

    public Vector3f minus(Vector3f vec) {
        return new Vector3f(x - vec.x, y - vec.y, z - vec.z);
    }

    public Vector3f divide(float dividend) {return new Vector3f(x / dividend, y / dividend, z / dividend); }

    public Vector3f multiply(float number) {return new Vector3f(x * number, y * number, z * number);}

    public static float[] toFloat(Vector3f[] vecs) {
        float[] out = new float[3* vecs.length];
        for (int i=0; i< vecs.length; i++) {
            out[3*i] = vecs[i].x;
            out[3*i+1] = vecs[i].y;
            out[3*i+2] = vecs[i].z;
        }
        return out;
    }

    public static Vector3f[] fromFloat(float[] floats) {
        if (floats.length % 3 != 0) {
            throw new IllegalArgumentException("Float array must have a length divisible by 3.");
        }
        Vector3f[] outVectors = new Vector3f[floats.length / 3];
        for (int i=0; i<outVectors.length; i++) {
            outVectors[i] = new Vector3f(floats[3*i], floats[3*i+1], floats[3*i+2]);
        }
        return outVectors;
    }

    public float magnitude() {
        return (float) Math.sqrt(x*x+y*y+z*z);
    }

    public static Vector3f cross(Vector3f a, Vector3f b) {
        return new Vector3f(a.y*b.z-a.z*b.y, a.z*b.x- a.x*b.z, a.x*b.y-a.y-b.x);
    }

    public float dot(Vector3f other) {
        return x*other.x+y*other.y+z*other.z;
    }

    public float normalisedDot(Vector3f other) {
        return dot(other) / (magnitude() * other.magnitude());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Vector3f vec)) {
            return false;
        }
        return x == vec.x && y == vec.y && z == vec.z;
    }

    @Override
    public String toString() {
        return "(%s, %s, %s)".formatted(x,y,z);
    }

    public float[] values() {
        return new float[]{x,y,z};
    }
}
