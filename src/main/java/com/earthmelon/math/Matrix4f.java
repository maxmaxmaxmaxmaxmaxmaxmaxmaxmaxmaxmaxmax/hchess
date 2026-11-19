package com.earthmelon.math;

import java.nio.FloatBuffer;

public class Matrix4f {

    float m11,m12,m13,m14;
    float m21,m22,m23,m24;
    float m31,m32,m33,m34;
    float m41,m42,m43,m44;

    public Matrix4f(float[] elems) {
        if (elems.length != 16) {
            throw new IllegalArgumentException("4x4 matrix needs 16 elements");
        }

        m11 = elems[0];
        m12 = elems[1];
        m13 = elems[2];
        m14 = elems[3];
        m21 = elems[4];
        m22 = elems[5];
        m23 = elems[6];
        m24 = elems[7];
        m31 = elems[8];
        m32 = elems[9];
        m33 = elems[10];
        m34 = elems[11];
        m41 = elems[12];
        m42 = elems[13];
        m43 = elems[14];
        m44 = elems[15];
    }

    public Matrix4f() {}


    public void store(FloatBuffer matrix) {
        matrix.put(m11).put(m12).put(m13).put(m14);
        matrix.put(m21).put(m22).put(m23).put(m24);
        matrix.put(m31).put(m32).put(m33).put(m34);
        matrix.put(m41).put(m42).put(m43).put(m44);
    }

    public float[] elems() {
        return new float[]{m11,m12,m13,m14,m21,m22,m23,m24,m31,m32,m33,m34,m41,m42,m43,m44};
    }
}
