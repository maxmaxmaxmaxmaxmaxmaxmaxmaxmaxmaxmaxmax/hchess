package com.earthmelon.render;

import com.earthmelon.math.Vector3f;
import com.earthmelon.math.Vector4f;
import com.earthmelon.render.shader.Tintable;

public class TextRender implements Tintable {

    public float textSize = 0.1f;

    private Vector4f colour = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);

    private static String belowLineLetters = "qypgj";

    public BasicMesh drawChar(char c, Vector3f pos) {
        BasicMesh letter = MeshRegistry.createQuadAtlas(pos, textSize, 256, (c-1) % 32, (c-1) / 32, 8);
        return letter.addTexture("hfont.png");
    }

    public BasicMesh[] drawString(String s, Vector3f pos) {
        BasicMesh[] out = new BasicMesh[s.length()];
        char[] chars = s.toCharArray();
        for (int i=0; i< out.length; i++) {
            char ith = chars[i];
            if (belowLineLetters.contains(String.valueOf(ith))) {
                pos = pos.plus(0, -textSize / 2, 0);
                out[i] = drawChar(ith, pos).setTint(colour);
                pos = pos.plus(0, textSize / 2, 0);
            } else {
                out[i] = drawChar(ith, pos).setTint(colour);
            }
            pos = pos.plus(textSize / 1.5f, 0,0);
        }
        return out;
    }

    @Override
    public Vector4f getTint() {
        return colour;
    }

    @Override
    public Tintable setTint(Vector4f tint) {
        colour = tint;
        return this;
    }

    public BasicMesh setTint(Vector4f tint, BasicMesh basicMesh) {
        colour = tint;
        return basicMesh.setTint(tint);
    }
}
