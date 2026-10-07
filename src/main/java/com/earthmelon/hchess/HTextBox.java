package com.earthmelon.hchess;

import com.earthmelon.math.Vector3f;
import com.earthmelon.math.Vector4f;
import com.earthmelon.render.MeshFactory;
import com.earthmelon.render.meshes.Mesh;
import com.earthmelon.render.meshes.TextMesh;
import com.earthmelon.render.shader.Render;
import com.earthmelon.render.shader.Renderable;
import com.earthmelon.render.shader.Tintable;

import java.util.Arrays;
import java.util.stream.Stream;

public class HTextBox implements Renderable, Tintable {
    Vector3f position;
    float width;
    float height;

    private TextMesh[] text;

    Mesh background;
    Vector4f tint = new Vector4f(1,1,1,1);


    public HTextBox(Vector3f position, float width, float height) {
        this.position = position;
        this.width = width;
        this.height = height;
        background = MeshFactory.createQuad(position, width, height).addTexture("bar.png");
        Render.notifyRender(getRenderables());
    }

    @Override
    public Mesh[] getRenderables() {
        if (text == null) {
            return new Mesh[]{background};
        }
        return  Stream.concat(Arrays.stream(new Mesh[]{background}), Arrays.stream(text))
                .toArray(Mesh[]::new);
    }

    public HTextBox setText(String text, float size) {
        this.text = TextMesh.drawString(text, position, width, height, size);
        return this;
    }

    public HTextBox setTint(Vector4f colour) {
        this.tint = colour;
        return this;
    }

    public Vector4f getTint() {
        return tint;
    }

    public HTextBox setTextTint(Vector4f colour) {
        for (TextMesh letter : text) {
            letter.setTint(colour);
        }
        return this;
    }
}
