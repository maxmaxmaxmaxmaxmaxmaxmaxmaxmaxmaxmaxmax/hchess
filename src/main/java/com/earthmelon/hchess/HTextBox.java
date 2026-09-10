package com.earthmelon.hchess;

import com.earthmelon.math.Vector3f;
import com.earthmelon.math.Vector4f;
import com.earthmelon.render.MeshFactory;
import com.earthmelon.render.meshes.Mesh;
import com.earthmelon.render.meshes.TextMesh;
import com.earthmelon.render.shader.Renderable;

import java.util.Arrays;
import java.util.stream.Stream;

public class HTextBox implements Renderable {
    Vector3f position;
    float width;
    float height;

    private TextMesh[] text;

    Mesh background;


    public HTextBox(Vector3f position, float width, float height) {
        this.position = position;
        this.width = width;
        this.height = height;

        // Todo: change this method to allow for scaling in both directions separately.

        background = MeshFactory.createQuad(position, width, height).addTexture("bar.png");
    }

    @Override
    public Mesh[] getRenderables() {
        if (background == null) {
            return new Mesh[0];
        }
        return  Stream.concat(Arrays.stream(new Mesh[]{background}), Arrays.stream(text))
                .toArray(Mesh[]::new);
    }

    public HTextBox setText(String text, float size) {
        this.text = TextMesh.drawString(text, position, width, height, size);
        return this;
    }

    public HTextBox setColour(Vector4f colour) {
        for (TextMesh letter : text) {
            letter.setTint(colour);
        }
        return this;
    }
}
