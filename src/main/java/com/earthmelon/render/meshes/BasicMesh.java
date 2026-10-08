package com.earthmelon.render.meshes;

import com.earthmelon.math.Vector4f;
import com.earthmelon.render.shader.Renderable;
import com.earthmelon.render.shader.Tintable;

import static com.earthmelon.hchess.HChess.RENDERABLES;

public class BasicMesh extends Mesh implements Renderable, Tintable {

    private Vector4f colour = new Vector4f(1,1,1,1);

    public BasicMesh(int vao, int vertex) {
        super(vao, vertex);
        RENDERABLES.add(this);
    }

    @Override
    public Vector4f getTint() {
        return colour;
    }

    @Override
    public BasicMesh setTint(Vector4f tint) {
        colour = tint;
        return this;
    }

    @Override
    public Mesh[] getRenderables() {
        return new Mesh[]{this};
    }
}