package com.earthmelon.engine;

import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Colour;
import com.earthmelon.render.MeshFactory;
import com.earthmelon.render.Transform;
import com.earthmelon.render.meshes.BasicMesh;

import java.util.Arrays;

public class SceneObject {

    String id;
    Vector3f position = new Vector3f(0,0,0);
    Vector3f pointOfRotation;

    Vector3f[] vertices = new Vector3f[]{position,
            /*1*/position.plus(1,0,0),
            /*2*/position.plus(0,1,0),
            /*3*/position.plus(1,1,0),
            /*4*/position.plus(0,0,1),
            /*5*/position.plus(1,0,1),
            /*6*/position.plus(0,1,1),
            /*7*/position.plus(1,1,1)};
    float[] uvs = new float[0];
    int[] indices = {0,1,2,
            1,2,3,
            7,6,5,
            6,5,4,
            3,2,6,
            2,6,7,
            4,0,3,
            0,3,7,
            4,5,1,
            5,1,0,
            1,5,6,
            5,6,2};

    Transform transform;
    Vector3f[] transformedVertices;

    BasicMesh mesh;

    public SceneObject(String id) {
        this.id = id;
        pointOfRotation = vertices[0];

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
        transformedVertices = applyRotation(Arrays.copyOf(vertices, vertices.length));
        Arrays.setAll(transformedVertices, i -> transformedVertices[i].plus(getTransform().getTranslation()));
        mesh = MeshFactory.createMesh(Vector3f.toFloat(transformedVertices), uvs, indices).setTint(Colour.YELLOW);
    }

    private Vector3f[] applyRotation(Vector3f[] vertices) {
        Vector3f[] rotated = new Vector3f[vertices.length];


        return transformedVertices;
    }

    public BasicMesh getMesh() {
        return mesh;
    }
}
