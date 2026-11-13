package com.earthmelon.render;

import com.earthmelon.math.Vector3f;

public class Transform {
    Vector3f translation;
    Vector3f rotation;
    float scale;

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public float getScale() {
        return scale;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void rotate(Vector3f rotation) {

    }

    public void translate(Vector3f translation) {

    }

    public void scale(float scale) {

    }
}
