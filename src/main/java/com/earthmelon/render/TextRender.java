package com.earthmelon.render;

import com.earthmelon.math.Vector3f;

public class TextRender {

    public static Mesh drawChar(char c, Vector3f pos) {
        Mesh letter = MeshLoader.createQuadAtlas(pos, 256, (c-1) % 32, (c-1) / 32, 8);
        return letter.addTexture("hfont.png");
    }

    public static Mesh[] drawString(String s, Vector3f pos) {
        Mesh[] out = new Mesh[s.length()];
        char[] chars = s.toCharArray();
        for (int i=0; i< out.length; i++) {
            char ith = chars[i];
            out[i] = drawChar(ith, pos);
            pos = pos.plus(0.1f, 0,0);
        }
        return out;
    }
}
