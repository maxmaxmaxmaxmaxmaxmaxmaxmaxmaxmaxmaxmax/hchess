package com.earthmelon.render.meshes;

import com.earthmelon.render.shader.Render;

public class BasicMesh extends Mesh {

    public BasicMesh(int vao, int vertex) {
        super(vao, vertex);
        Render.notifyRender(this);
    }
}