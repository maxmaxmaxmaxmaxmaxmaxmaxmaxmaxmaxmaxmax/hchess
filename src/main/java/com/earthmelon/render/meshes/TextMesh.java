package com.earthmelon.render.meshes;

import com.earthmelon.math.Vector3f;
import com.earthmelon.render.MeshFactory;
import com.earthmelon.render.shader.Tintable;

import java.util.Arrays;

public class TextMesh extends Mesh2D implements Tintable {


    private static String belowLineLetters = "qypgj";

    public TextMesh(int vao, int vertices) {
        super(vao, vertices);
    }

    public static Mesh2D drawChar(char c, Vector3f pos, float textSize) {
        Mesh2D letter = MeshFactory.create2DQuadAtlas(pos, textSize, 256, (c-1) % 32, (c-1) / 32, 8);
        return letter.addTexture("hfont.png");
    }

    public static Mesh2D[] drawString(String s, Vector3f pos, float width, float height, float textSize) {
        int col = 0;
        int row = 0;
        float kerning = textSize / 1.5f;
        Mesh2D[] out = new Mesh2D[s.length()];
        char[] chars = s.toCharArray();
        for (int i=0; i< out.length; i++) {
            if (2 * (row-2) * textSize > height) {
                return Arrays.copyOf(out, i);
            }
            char ith = chars[i];
            col++;
            if (ith == '\n' || (col+1) * kerning > width) {
                out[i] = drawChar(ith, pos, textSize);
                pos = pos.plus(-col * kerning, - 2 * textSize, 0);
                col = 0;
                row++;
            }
            else if (belowLineLetters.contains(String.valueOf(ith))) {
                pos = pos.plus(0, -textSize / 2, 0);
                out[i] = drawChar(ith, pos, textSize).setTint(colour);
                pos = pos.plus(0, textSize / 2, 0);
            } else {
                out[i] = drawChar(ith, pos, textSize).setTint(colour);
            }
            pos = pos.plus(kerning, 0,0);
        }
        return out;
    }
}
