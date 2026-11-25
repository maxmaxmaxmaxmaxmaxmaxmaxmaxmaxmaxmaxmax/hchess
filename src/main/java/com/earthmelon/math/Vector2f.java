package com.earthmelon.math;

public class Vector2f {

    private final float x;
    private final float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Vector2f plus(float nx, float ny) {
        return new Vector2f(x + nx, y + ny);
    }

    public static float[] toFloat(Vector2f[] vecs) {
        float[] out = new float[vecs.length * 2];
        int i=0;
        for (Vector2f vector : vecs) {
            out[i] = vector.getX();
            out[i+1] = vector.getY();
            i+=2;
        }
        return out;
    }
}
