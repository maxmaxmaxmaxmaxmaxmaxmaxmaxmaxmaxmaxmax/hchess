package com.earthmelon.render.meshes;

import com.earthmelon.math.Vector3f;
import com.earthmelon.math.Vector4f;
import com.earthmelon.render.MeshFactory;
import com.earthmelon.render.Texture;
import com.earthmelon.render.shader.Tintable;

import java.util.List;

public class TextMesh extends Mesh implements Tintable {

    public static float textSize = 0.1f;

    private static Vector4f colour = new Vector4f(1.0f, 0.0f, 1.0f, 1.0f);

    private static String belowLineLetters = "qypgj";

    public TextMesh(int vao, int vertices) {
        super(vao, vertices);
    }

    public static TextMesh drawChar(char c, Vector3f pos) {
        TextMesh letter = MeshFactory.createQuadAtlas(pos, textSize, 256, (c-1) % 32, (c-1) / 32, 8);
        return letter.addTexture("hfont.png");
    }

    public static TextMesh[] drawString(String s, Vector3f pos) {
        int col = 1;
        int row = 1;
        float kerning = textSize / 1.5f;
        TextMesh[] out = new TextMesh[s.length()];
        char[] chars = s.toCharArray();
        for (int i=0; i< out.length; i++) {
            char ith = chars[i];
            col++;
            if (ith == '\n') {
                out[i] = drawChar(ith, pos);
                pos = pos.plus(-col * kerning, - 2 * textSize, 0);
                col = 0;
            }
            else if (belowLineLetters.contains(String.valueOf(ith))) {
                pos = pos.plus(0, -textSize / 2, 0);
                out[i] = drawChar(ith, pos).setTint(colour);
                pos = pos.plus(0, textSize / 2, 0);
            } else {
                out[i] = drawChar(ith, pos).setTint(colour);
            }
            pos = pos.plus(kerning, 0,0);
        }
        return out;
    }

    @Override
    public Vector4f getTint() {
        return colour;
    }

    @Override
    public TextMesh setTint(Vector4f tint) {
        colour = tint;
        return this;
    }

    @Override
    public TextMesh addTexture(String filePath) {
        this.texture = Texture.loadTexture(filePath);
        MeshFactory.getInstance().addTexture(texture);
        return this;
    }
}
