package com.earthmelon.engine;

import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Colour;
import com.earthmelon.render.MeshFactory;
import com.earthmelon.render.meshes.BasicMesh;
import com.earthmelon.render.meshes.Mesh;
import com.earthmelon.render.meshes.TextMesh;
import com.earthmelon.render.Renderable;
import com.earthmelon.render.shader.Tintable;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import static com.earthmelon.engine.HChess.RENDERABLES;

public class HTextBox implements Renderable, Tintable {
    Vector3f position;
    float width;
    float height;

    private TextMesh[] textMeshes;
    private String heldText = "";

    BasicMesh background;

    public HTextBox(Vector3f position, float width, float height) {
        this.position = position;
        this.width = width;
        this.height = height;
        background = MeshFactory.createQuad(position, width, height).setTint(Colour.GREY);
        RENDERABLES.add(this);
    }

    @Override
    public Mesh[] getRenderables() {
        if (textMeshes == null) {
            return new Mesh[]{background};
        }
        return  Stream.concat(Arrays.stream(new Mesh[]{background}), Arrays.stream(textMeshes))
                .toArray(Mesh[]::new);
    }

    public HTextBox setText(String text, float size) {
        if (!Objects.equals(heldText, text)) {
            heldText = text;
            this.textMeshes = TextMesh.drawString(text, position, width, height, size);
        }
        return this;
    }

    public HTextBox setTint(Colour colour) {
        background.setTint(colour);
        return this;
    }

    public Colour getTint() {
        return background.getTint();
    }

    public HTextBox setTextTint(Colour colour) {
        for (TextMesh letter : textMeshes) {
            letter.setTint(colour);
        }
        return this;
    }
}
