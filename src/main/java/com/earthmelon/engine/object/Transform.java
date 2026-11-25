package com.earthmelon.engine.object;

import com.earthmelon.math.Vector3f;

public class Transform {
    private Vector3f translation = new Vector3f(0,0,0);
    private Vector3f rotation = new Vector3f(0,0,0);

    private Vector3f scale = new Vector3f(1, 1, 1);

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

    public float getScaleX() {
        return scale.getX();
    }

    public float getScaleY() {
        return scale.getY();
    }

    public float getScaleZ() {
        return scale.getZ();
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
        attachedObject.updateTransform();
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
        attachedObject.updateTransform();
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
        attachedObject.updateTransform();
    }

    public Transform rotate(Vector3f rotation) {
        this.rotation = this.rotation.plus(rotation);
        attachedObject.updateTransform();
        return this;
    }

    public Transform translate(Vector3f translation) {
        this.translation = this.translation.plus(translation);
        attachedObject.updateTransform();
        return this;
    }

    public Transform scale(float scale) {
        this.scale.multiply(scale);
        attachedObject.updateTransform();
        return this;
    }

    public Transform scale(Vector3f scale) {
        this.scale = new Vector3f(this.scale.getX() * scale.getX(), this.scale.getY() * scale.getY(), this.scale.getZ() * scale.getZ());
        return this;
    }
}
