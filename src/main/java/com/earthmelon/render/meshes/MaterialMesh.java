package com.earthmelon.render.meshes;

import com.earthmelon.engine.object.Material;
import com.earthmelon.render.Colour;
import com.earthmelon.render.Renderable;
import com.earthmelon.render.shader.Tintable;

public class MaterialMesh extends Mesh implements Renderable, Tintable {

    private Colour colour = Colour.WHITE;

    private Material material;

    public MaterialMesh(int vao, int vertex) {
        super(vao, vertex);
    }

    @Override
    public Colour getTint() {
        return material != null ? material.getDiffuseColour() : colour;
    }

    @Override
    public MaterialMesh setTint(Colour tint) {
        colour = tint;
        return this;
    }

    @Override
    public Mesh[] getRenderables() {
        return new Mesh[]{this};
    }

    public MaterialMesh setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public Material getMaterial() {return material;}
}