package com.earthmelon.render.meshes;

public interface MeshI {

    int getVaoID();

    int getVertexCount();

    MeshI addTexture(String filePath);

    int getTexture();
}
