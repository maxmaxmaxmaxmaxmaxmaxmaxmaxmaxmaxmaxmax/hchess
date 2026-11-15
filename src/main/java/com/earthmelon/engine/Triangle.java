package com.earthmelon.engine;

import com.earthmelon.math.Vector3f;

public class Triangle {

    Vector3f point1;
    Vector3f point2;
    Vector3f point3;

    Vector3f normal;


    public Triangle(Vector3f point1, Vector3f point2, Vector3f point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;

        Vector3f normWithMagnitude = Vector3f.cross(point2.minus(point1), point3.minus(point1));
        normal = normWithMagnitude.divide(normWithMagnitude.magnitude());
    }

    public Vector3f getNormal() {
        return normal;
    }
}
