package com.earthmelon.render;

import com.earthmelon.math.Vector3f;

public class TextRender {

    public static Mesh drawChar(char c, Vector3f pos) {
        Mesh letter = MeshLoader.createQuad(pos).addTexture("hfont.png");
        return letter;
    }
}
