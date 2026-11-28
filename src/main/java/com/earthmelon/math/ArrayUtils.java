package com.earthmelon.math;

public class ArrayUtils {

    public static float[] toPrimitive(Vector3f[] vecs) {
        float[] out = new float[3* vecs.length];
        for (int i=0; i< vecs.length; i++) {
            out[3*i] = vecs[i].getX();
            out[3*i+1] = vecs[i].getY();
            out[3*i+2] = vecs[i].getZ();
        }
        return out;
    }

    public static float[] toPrimitive(Float[] floats) {
        float[] out = new float[floats.length];
        for (int i=0; i< floats.length; i++) {
            out[i] = floats[i];
        }
        return out;
    }

    public static int[] toPrimitive(Integer[] ints) {
        int[] out = new int[ints.length];
        for (int i = 0; i< ints.length; i++) {
            out[i] = ints[i];
        }
        return out;
    }
}
