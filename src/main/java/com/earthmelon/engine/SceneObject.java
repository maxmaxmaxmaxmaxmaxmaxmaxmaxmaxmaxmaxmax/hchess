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
    Vector3f position;
    Vector3f pointOfRotation;

    Vector3f[] vertices;
    float[] uvs = new float[0];
    int[] indices = {0,2,1,
            1,2,3,
            7,6,5,
            6,4,5,
            3,2,7,
            2,6,7,
            4,0,2,
            1,3,7,
            4,0,5,
            5,0,1,
            1,7,5,
            4,6,2};

    Transform transform;
    Vector3f[] transformedVertices;

    BasicMesh mesh;
    Colour colour = Colour.GREY;

    public SceneObject(String id, Vector3f position) {
        this.id = id;
        this.position = position;

        setVertices(new Vector3f[]{position,
                /*1*/position.plus(10,0,0),
                /*2*/position.plus(0,10,0),
                /*3*/position.plus(10f,10f,0),
                /*4*/position.plus(0,0,10),
                /*5*/position.plus(10,0,10),
                /*6*/position.plus(0,10f,10),
                /*7*/position.plus(10,10,10)});

        // temp value
        pointOfRotation = new Vector3f(5, 5, 5);

        attachTransform();
        HChess.SCENE_OBJECTS.add(this);

        // Default until .obj conversion works.
        mesh = MeshFactory.createMesh(Vector3f.toFloat(vertices), uvs, indices).setTint(colour);
    }

    private void setVertices(Vector3f[] vertices) {
        this.vertices = vertices;
        this.transformedVertices = new Vector3f[vertices.length];
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
        pointOfRotation.plus(getTransform().getTranslation());
        mesh = MeshFactory.createMesh(Vector3f.toFloat(transformedVertices), uvs, indices).setTint(colour);
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

    public void setColour(Colour colour) {
        this.colour = colour;
    }
}
