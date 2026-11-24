package com.earthmelon.render.shader;

public class Shader2D extends Shader{

    public Shader2D() {
        super("2DTextured.vs", "Textured.fs");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "texCoords");
    }

    @Override
    protected void getAllUniformLocations() {

    }
}
