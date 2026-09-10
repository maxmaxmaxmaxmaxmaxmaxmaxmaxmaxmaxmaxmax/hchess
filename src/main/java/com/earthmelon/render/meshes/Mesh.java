package com.earthmelon.render.meshes;

import com.earthmelon.render.MeshFactory;
import com.earthmelon.render.Texture;

import java.util.List;

public abstract class Mesh {

    protected int vao;
    protected int vertices;
    int texture = 0;

    protected Mesh(int vao, int vertices) {
        this.vao = vao;
        this.vertices = vertices;
    }

    public int getVaoID() {
        return vao;
    }

    public int getVertexCount() {
        return vertices;
    }

    public Mesh addTexture(String filePath) {
        this.texture = Texture.loadTexture(filePath);
        MeshFactory.getInstance().addTexture(texture);
        return this;
    }

    public int getTexture() {
        return texture;
    }

    public List<Mesh> getRenderables() {
        return List.of(this);
    }
}
