package com.earthmelon.render.shader;

import com.earthmelon.math.Vector4f;
import com.earthmelon.render.Colour;

public interface Tintable {

    Colour getTint();

    Tintable setTint(Colour tint);

    default float getRed() {
        return getTint().getVector().x;
    }

    default float getGreen() {
        return getTint().getVector().y;
    }

    default float getBlue() {
        return getTint().getVector().z;
    }

    default float getAlpha() {
        return getTint().getVector().a;
    }

}
