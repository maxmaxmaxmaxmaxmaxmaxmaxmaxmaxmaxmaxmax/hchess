package com.earthmelon.render.meshes;

import java.util.List;

public interface MeshI {

    int getVaoID();

    int getVertexCount();

    MeshI addTexture(String filePath);

    int getTexture();

    List<MeshI> getContents();
}
