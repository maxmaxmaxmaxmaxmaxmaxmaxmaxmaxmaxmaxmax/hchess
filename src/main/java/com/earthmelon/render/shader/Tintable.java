package com.earthmelon.render.shader;

import com.earthmelon.render.Colour;

public interface Tintable {

    Colour getTint();

    Tintable setTint(Colour tint);

    default float getRed() {
        return getTint().getVector().getX();
    }

    default float getGreen() {
        return getTint().getVector().getY();
    }

    default float getBlue() {
        return getTint().getVector().getZ();
    }

    default float getAlpha() {
        return getTint().getVector().getW();
    }

}
