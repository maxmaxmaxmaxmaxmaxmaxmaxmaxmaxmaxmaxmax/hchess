package com.earthmelon.engine;

import com.earthmelon.math.Matrix3f;
import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Colour;
import com.earthmelon.render.MeshFactory;
import com.earthmelon.render.Transform;
import com.earthmelon.render.meshes.BasicMesh;

import java.util.ArrayList;
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
    Vector3f[] transformedVertices = new Vector3f[vertices.length];

    BasicMesh mesh;

    public SceneObject(String id) {
        this.id = id;

        // temp value
        pointOfRotation = new Vector3f(0.5f, 0.5f, 0.5f);

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
        applyRotation(Arrays.copyOf(vertices, vertices.length));
        Arrays.setAll(transformedVertices, i -> transformedVertices[i].plus(getTransform().getTranslation()));
        mesh = MeshFactory.createMesh(Vector3f.toFloat(transformedVertices), uvs, indices).setTint(Colour.YELLOW);
    }

    private void applyRotation(Vector3f[] vertices) {
        ArrayList<Vector3f> rotated = new ArrayList<>();
        float yaw = transform.getRotation().getX();
        float pitch = transform.getRotation().getY();
        float roll = transform.getRotation().getZ();
        Matrix3f yawRotate = new Matrix3f(new float[]{
                1,0,0,
                0, (float) Math.cos(yaw), (float) -Math.sin(yaw),
                0, (float) Math.sin(yaw), (float) Math.cos(yaw)});
        Matrix3f pitchRotate = new Matrix3f(new float[]{
                (float) Math.cos(pitch), 0, (float) -Math.sin(pitch),
                0, 1, 0,
                (float) Math.sin(pitch), 0, (float) Math.cos(pitch)});
        Matrix3f rollRotate = new Matrix3f(new float[]{
                (float) Math.cos(roll), (float) -Math.sin(roll), 0,
                (float) Math.sin(roll), (float) Math.cos(roll), 0,
                0, 0, 1});

        for (Vector3f point : vertices) {
            rotated.add(rollRotate.multiply(pitchRotate.multiply(
                    yawRotate.multiply(point.minus(pointOfRotation))))
                    .plus(pointOfRotation));
        }
        transformedVertices = rotated.toArray(transformedVertices);
    }

    public BasicMesh getMesh() {
        return mesh;
    }
}
