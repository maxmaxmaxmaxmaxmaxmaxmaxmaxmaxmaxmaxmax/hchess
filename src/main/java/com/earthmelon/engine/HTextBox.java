package com.earthmelon.engine;

import com.earthmelon.math.Vector2f;
import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Colour;
import com.earthmelon.render.MeshFactory;
import com.earthmelon.render.meshes.Mesh2D;
import com.earthmelon.render.meshes.TextMesh;
import com.earthmelon.render.Renderable;
import com.earthmelon.render.shader.Tintable;

import java.util.Arrays;
import java.util.stream.Stream;


// Todo: make this work with scene object
public class HTextBox implements Renderable, Tintable {
    Vector2f position;
    float width;
    float height;

    private Mesh2D[] text;

    Mesh2D background;

    public HTextBox(Vector2f position, float width, float height) {
        this.position = position;
        this.width = width;
        this.height = height;
        background = MeshFactory.create2DQuad(position, width, height).setTint(Colour.GREY);
    }

    // TODO: fix background being on same z-layer as text.
    @Override
    public Mesh2D[] getRenderables() {
        if (text == null) {
            return new Mesh2D[]{background};
        }
        return
//                Stream.concat(Arrays.stream(new Mesh2D[]{background}),
                        Arrays.stream(text)
//                        )
                .toArray(Mesh2D[]::new);
    }

    public HTextBox setText(String text, float size) {
        this.text = TextMesh.drawString(text, position, width, height, size);
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
        for (Mesh2D letter : text) {
            letter.setTint(colour);
        }
        return this;
    }
}
