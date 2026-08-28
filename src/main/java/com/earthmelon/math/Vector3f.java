package com.earthmelon.math;

public class Vector3f {

    public final float x;
    public final float y;
    public final float z;

    /**
     * Immutable 3-dimensional vector class.
     * @param x is the first direction of the vector.
     * @param y
     * @param z
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

    public static float[] toFloat(Vector3f[] vecs) {
        float[] out = new float[3* vecs.length];
        for (int i=0; i< vecs.length; i++) {
            out[3*i] = vecs[i].x;
            out[3*i+1] = vecs[i].y;
            out[3*i+2] = vecs[i].z;
        }
        return out;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Vector3f vec)) {
            return false;
        }
        return x == vec.x && y == vec.y && z == vec.z;
    }
}
