package com.earthmelon.render;

import com.earthmelon.math.Vector4f;
import com.earthmelon.render.shader.Tintable;

public class BasicMesh implements Tintable {

    private int vao;
    private int vertices;
    private int texture = 0;

    private Vector4f tint = new Vector4f(1,1,1,1);

    public BasicMesh(int vao, int vertex) {
        this.vao = vao;
        this.vertices = vertex;
    }

    public int getVaoID() {
        return vao;
    }

    public int getVertexCount() {
        return vertices;
    }


    public BasicMesh addTexture(String filePath) {
        this.texture = Texture.loadTexture(filePath);
        MeshRegistry.getInstance().addTexture(texture);
        return this;
    }

    public int getTexture(){
        return this.texture;
    }

    @Override
    public Vector4f getTint() {
        return tint;
    }

    @Override
    public BasicMesh setTint(Vector4f tint) {
        this.tint = tint;
        return this;
    }
}