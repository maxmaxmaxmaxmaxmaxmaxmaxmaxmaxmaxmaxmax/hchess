package com.earthmelon.render.meshes;

import com.earthmelon.render.MeshRegistry;
import com.earthmelon.render.Texture;

import java.util.List;

public class BasicMesh implements MeshI {

    private int vao;
    private int vertices;
    private int texture = 0;

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
    public List<MeshI> getContents() {
        return List.of();
    }
}