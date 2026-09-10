package com.earthmelon.hchess;

import com.earthmelon.math.Vector3f;
import com.earthmelon.render.meshes.Mesh;
import com.earthmelon.render.meshes.TextMesh;
import com.earthmelon.render.shader.Renderable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class HTextBox implements Renderable {

    private Mesh background;
    Vector3f position;
    float width;
    float height;
    private TextMesh[] text = TextMesh.drawString("", new Vector3f(0,0,0));


    public HTextBox(String text) {

    }

    @Override
    public Mesh[] getRenderables() {
        return  Stream.concat(Arrays.stream(new Mesh[]{background}), Arrays.stream(text))
                .toArray(Mesh[]::new);
    }
}
