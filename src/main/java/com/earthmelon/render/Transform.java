package com.earthmelon.render;

import com.earthmelon.engine.SceneObject;
import com.earthmelon.math.Vector3f;

public class Transform {
    private Vector3f translation = new Vector3f(0,0,0);
    private Vector3f rotation = new Vector3f(0,0,0);
    private float scale = 1.0f;

    private final SceneObject attachedObject;

    public Transform(SceneObject attachedObject) {
        this.attachedObject = attachedObject;
    }

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
        attachedObject.updateTransform();
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
        attachedObject.updateTransform();
    }

    public void setScale(float scale) {
        this.scale = scale;
        attachedObject.updateTransform();
    }

    public void rotate(Vector3f rotation) {
        this.rotation = this.rotation.plus(rotation);
        attachedObject.updateTransform();
    }

    public void translate(Vector3f translation) {
        this.translation = this.translation.plus(translation);
        attachedObject.updateTransform();
    }

    public Transform scale(float scale) {
        this.scale *= scale;
        attachedObject.updateTransform();
        return this;
    }
}
