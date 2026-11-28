package com.earthmelon.engine.object;

import com.earthmelon.render.Colour;

public class Material {
    // https://www.loc.gov/preservation/digital/formats/fdd/fdd000508.shtml#identification

    String name;

    Colour ambientColour;
    Colour diffuseColour;
    Colour specularColour;
    Colour emissiveColour;

    float specularHighlights;
    float opticalDensity;
    float dissolve;

    String colourMap;

    public Material(String name) {
        this.name = name;
    }

    public Colour getAmbientColour() {
        return ambientColour;
    }

    public void setAmbientColour(Colour ambientColour) {
        this.ambientColour = ambientColour;
    }

    public String getColourMap() {
        return colourMap;
    }

    public void setColourMap(String colourMap) {
        this.colourMap = colourMap;
    }

    public Colour getDiffuseColour() {
        return diffuseColour;
    }

    public void setDiffuseColour(Colour diffuseColour) {
        this.diffuseColour = diffuseColour;
    }

    public float getDissolve() {
        return dissolve;
    }

    public void setDissolve(float dissolve) {
        this.dissolve = dissolve;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getOpticalDensity() {
        return opticalDensity;
    }

    public void setOpticalDensity(float opticalDensity) {
        this.opticalDensity = opticalDensity;
    }

    public Colour getSpecularColour() {
        return specularColour;
    }

    public void setSpecularColour(Colour specularColour) {
        this.specularColour = specularColour;
    }

    public float getSpecularHighlights() {
        return specularHighlights;
    }

    public void setSpecularHighlights(float specularHighlights) {
        this.specularHighlights = specularHighlights;
    }

    public Colour getEmissiveColour() {
        return emissiveColour;
    }

    public void setEmissiveColour(Colour emissiveColour) {
        this.emissiveColour = emissiveColour;
    }
}
