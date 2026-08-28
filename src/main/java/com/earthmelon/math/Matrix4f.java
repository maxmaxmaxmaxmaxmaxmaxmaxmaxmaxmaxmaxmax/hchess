package com.earthmelon.math;

import java.nio.FloatBuffer;

public class Matrix4f {

    float m11,m12,m13,m14;
    float m21,m22,m23,m24;
    float m31,m32,m33,m34;
    float m41,m42,m43,m44;

    public Matrix4f() {

    }

    public void store(FloatBuffer matrix) {
        matrix.put(m11).put(m12).put(m13).put(m14);
        matrix.put(m21).put(m22).put(m23).put(m24);
        matrix.put(m31).put(m32).put(m33).put(m34);
        matrix.put(m41).put(m42).put(m43).put(m44);
    }
}
