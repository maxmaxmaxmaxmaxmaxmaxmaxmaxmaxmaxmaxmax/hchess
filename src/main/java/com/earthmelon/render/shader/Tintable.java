package com.earthmelon.render.shader;

import com.earthmelon.math.Vector4f;

public interface Tintable {

    Vector4f getTint();

    Tintable setTint(Vector4f tint);

    default float getRed() {
        return getTint().x;
    }

    default float getGreen() {
        return getTint().y;
    }

    default float getBlue() {
        return getTint().z;
    }

    default float getAlpha() {
        return getTint().a;
    }

}
