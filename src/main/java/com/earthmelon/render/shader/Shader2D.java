package com.earthmelon.render.shader;

public class Shader2D extends Shader{
    public Shader2D(String Vert, String Frag) {
        super(Vert, Frag);
    }

    public Shader2D() {
        super("2DTextured.vs", "Textured.fs");
    }

    @Override
    protected void bindAttributes() {

    }

    @Override
    protected void getAllUniformLocations() {

    }
}
