package com.earthmelon.engine;

import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Colour;
import com.earthmelon.render.MeshFactory;
import com.earthmelon.render.Transform;
import com.earthmelon.render.Window;
import com.earthmelon.render.meshes.BasicMesh;
import com.earthmelon.render.meshes.Mesh;

import java.util.Arrays;

public class SceneObject {

    String id;
    Vector3f position = new Vector3f(0,0,0);

    Vector3f[] vertices = new Vector3f[]{position, position.plus(1,0,0), position.plus(0, -Window.aspectRatio,0), position.plus(1,0,0), position.plus(0,-Window.aspectRatio,0), position.plus(1,-Window.aspectRatio,0)};
    float[] uvs = {0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1};
    int[] indices = {0,1,2,3,4,5};

    Transform transform;
    Vector3f[] transformedVertices;

    BasicMesh mesh;

    public SceneObject(String id) {
        this.id = id;
        attachTransform();
        HChess.SCENE_OBJECTS.add(this);

        // Default until .obj conversion works.
        mesh = MeshFactory.createMesh(Vector3f.toFloat(vertices), uvs, indices).setTint(Colour.YELLOW);
    }

    public Transform getTransform() {
        return transform;
    }

    public void attachTransform() {
        transform = new Transform(this);
    }

    public void detachTransform() {
        transform = null;
    }

    public void updateTransform() {
        transformedVertices = Arrays.copyOf(vertices, vertices.length);
        Arrays.setAll(transformedVertices, i -> transformedVertices[i].plus(getTransform().getTranslation()));
        mesh = MeshFactory.createMesh(Vector3f.toFloat(transformedVertices), uvs, indices).setTint(Colour.YELLOW);
    }

    public BasicMesh getMesh() {
        return mesh;
    }
}
