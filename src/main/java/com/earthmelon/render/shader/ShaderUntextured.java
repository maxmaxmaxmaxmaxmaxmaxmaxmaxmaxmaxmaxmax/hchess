package com.earthmelon.render.shader;

public class ShaderUntextured extends Shader{


    public ShaderUntextured() {
        super("Textured.vs", "Untextured.fs");
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
