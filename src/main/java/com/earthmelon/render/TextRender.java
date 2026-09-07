package com.earthmelon.render;

import com.earthmelon.math.Vector3f;

public class TextRender {

    public static Mesh drawChar(char c, Vector3f pos) {
        Mesh letter = MeshLoader.createQuadAtlas(pos, 256, 1, 1, 8);
        return letter.addTexture("hfont.png");
    }
}
