package com.earthmelon.hchess;

import com.earthmelon.render.meshes.MeshI;

import java.util.List;

public class HTextBox implements MeshI {


    @Override
    public int getVaoID() {
        return 0;
    }

    @Override
    public int getVertexCount() {
        return 0;
    }

    @Override
    public MeshI addTexture(String filePath) {
        return null;
    }

    @Override
    public int getTexture() {
        return 0;
    }

    @Override
    public List<MeshI> getContents() {
        return List.of();
    }
}
