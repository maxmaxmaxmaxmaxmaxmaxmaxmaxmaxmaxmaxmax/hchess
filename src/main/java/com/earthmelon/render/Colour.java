package com.earthmelon.render;

import com.earthmelon.math.Vector4f;

public class Colour {

    public static Colour RED = new Colour(new Vector4f(1,0,0,1));
    public static Colour YELLOW = new Colour(new Vector4f(1,1,0,1));
    public static Colour GREEN = new Colour(new Vector4f(0,1,0,1));
    public static Colour CYAN = new Colour(new Vector4f(0,1,1,1));
    public static Colour BLUE = new Colour(new Vector4f(0,0,1,1));
    public static Colour MAGENTA = new Colour(new Vector4f(1,0,1,1));
    public static Colour BLACK = new Colour(new Vector4f(0,0,0,1));
    public static Colour WHITE = new Colour(new Vector4f(1,1,1,1));
    public static Colour GREY = new Colour(new Vector4f(0.6f,0.6f,0.6f,1));



    Vector4f RGBA;

    public Colour(String hex) {
        if (!hex.startsWith("#") || !(hex.length() == 7)) {
            throw new IllegalArgumentException("Invalid hexadecimal colour!");
        }
        // TODO: add hex to decimal conversion.
    }

    public Colour(Vector4f rgb) {
        RGBA = rgb;
    }

    public Vector4f getVector() {
        return RGBA;
    }
}
