package com.earthmelon.render.meshes;

import com.earthmelon.render.Colour;
import com.earthmelon.render.MeshFactory;
import com.earthmelon.render.Texture;
import com.earthmelon.render.shader.Tintable;

public class Mesh2D extends Mesh implements Tintable {

    protected static Colour colour = Colour.BLACK;

    public Mesh2D(int vao, int vertices) {
        super(vao, vertices);
    }

    @Override
    public Mesh2D addTexture(String filePath) {
        this.texture = Texture.loadTexture(filePath);
        MeshFactory.getInstance().addTexture(texture);
        return this;
    }

    @Override
    public Colour getTint() {
        return colour;
    }

    @Override
    public Mesh2D setTint(Colour tint) {
        colour = tint;
        return this;
    }
}
