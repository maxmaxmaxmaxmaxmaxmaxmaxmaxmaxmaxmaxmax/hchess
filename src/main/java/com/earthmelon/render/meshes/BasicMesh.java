package com.earthmelon.render.meshes;

import com.earthmelon.math.Vector4f;
import com.earthmelon.render.Colour;
import com.earthmelon.render.Renderable;
import com.earthmelon.render.shader.Tintable;

import static com.earthmelon.hchess.HChess.RENDERABLES;

public class BasicMesh extends Mesh implements Renderable, Tintable {

    private Colour colour = Colour.WHITE;

    public BasicMesh(int vao, int vertex) {
        super(vao, vertex);
        RENDERABLES.add(this);
    }

    @Override
    public Colour getTint() {
        return colour;
    }

    @Override
    public BasicMesh setTint(Colour tint) {
        colour = tint;
        return this;
    }

    @Override
    public Mesh[] getRenderables() {
        return new Mesh[]{this};
    }
}