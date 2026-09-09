package com.earthmelon.render;

import com.earthmelon.math.Vector3f;

public class Mesh {

    private int vao;
    private int vertices;
    private int texture = 0;

    private Vector3f colour = new Vector3f(1,0,0);

    public Mesh(int vao, int vertex) {
        this.vao = vao;
        this.vertices = vertex;
    }

    public int getVaoID() {
        return vao;
    }

    public int getVertexCount() {
        return vertices;
    }


    public Mesh addTexture(String filePath) {
        this.texture = Texture.loadTexture(filePath);
        MeshLoader.getInstance().addTexture(texture);
        return this;
    }

    public int getTexture(){
        return this.texture;
    }

    public float getRed() {
        return colour.x;
    }

    public float getGreen() {
        return colour.y;
    }

    public float getBlue() {
        return colour.z;
    }

    public void setColour(Vector3f colour) {
        this.colour = colour;
    }
}