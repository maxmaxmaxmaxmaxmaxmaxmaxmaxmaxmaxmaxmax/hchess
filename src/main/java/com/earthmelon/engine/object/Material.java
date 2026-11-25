package com.earthmelon.engine.object;

import com.earthmelon.render.Colour;

public class Material {
    // https://www.loc.gov/preservation/digital/formats/fdd/fdd000508.shtml#identification

    String name;

    Colour ambientColour;
    Colour diffuseColour;
    Colour specularColour;
    float specularHighlights;
    float opticalDensity;
    float dissolve;

    String colourTextureFile;
}
