package com.earthmelon.math;

public class Matrix3f {

    float m11,m12,m13;
    float m21,m22,m23;
    float m31,m32,m33;

    public Matrix3f(float[] elems) {
        if (elems.length != 9) {
            throw new IllegalArgumentException("3x3 matrix requires 9 entries");
        }

        m11 = elems[0];
        m12 = elems[1];
        m13 = elems[2];
        m21 = elems[3];
        m22 = elems[4];
        m23 = elems[5];
        m31 = elems[6];
        m32 = elems[7];
        m33 = elems[8];
    }

    public Vector3f multiply(Vector3f vector) {
        return new Vector3f(vector.getX() * m11 + vector.getY() * m12 + vector.getZ() * m13,
                vector.getX() * m21 + vector.getY() * m22 + vector.getZ() * m23,
                vector.getX() * m31 + vector.getY() * m32 + vector.getZ() * m33);
    }
}
