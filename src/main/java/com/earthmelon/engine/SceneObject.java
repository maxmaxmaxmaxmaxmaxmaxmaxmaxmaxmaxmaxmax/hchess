package com.earthmelon.engine;

import com.earthmelon.math.Matrix4f;
import com.earthmelon.math.Vector3f;
import com.earthmelon.math.Vector4f;
import com.earthmelon.render.Colour;
import com.earthmelon.render.MeshFactory;
import com.earthmelon.render.Transform;
import com.earthmelon.render.meshes.BasicMesh;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

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
            4,2,0,
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
        pointOfRotation = new Vector3f(position.getX() + 5, position.getY() + 5, position.getZ() + 5);
        this.transformedVertices = Arrays.copyOf(vertices, vertices.length);

        attachTransform();
        HChess.SCENE_OBJECTS.add(this);

        // Default until .obj conversion works.
        mesh = MeshFactory.createMesh(Vector3f.toFloat(vertices), uvs, indices).setTint(colour);
    }

    public SceneObject(String id, Vector3f[] vertices, float[] uvs, int[] indices) {
        this.id = id;
        this.vertices = vertices;
        this.position = new Vector3f(0,0,0);
        this.transformedVertices = Arrays.copyOf(vertices, vertices.length);

        mesh = MeshFactory.createMesh(Vector3f.toFloat(vertices), uvs, indices).setTint(colour);

        pointOfRotation = new Vector3f(position.getX() + 5, position.getY() + 5, position.getZ() + 5);

        attachTransform();
        HChess.SCENE_OBJECTS.add(this);
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
        applyTransformation(Arrays.copyOf(vertices, vertices.length));
        pointOfRotation.plus(getTransform().getTranslation());
        mesh = MeshFactory.createMesh(Vector3f.toFloat(transformedVertices), uvs, indices).setTint(colour);
    }

    private void applyTransformation(Vector3f[] vertices) {
        ArrayList<Vector3f> rotated = new ArrayList<>();
        float yaw = transform.getRotation().getX();
        float pitch = transform.getRotation().getY();
        float roll = transform.getRotation().getZ();

        Matrix4f transformMatrix = new Matrix4f(new float[]{
                (float) (getTransform().getScale() * (cos(roll) * cos(pitch))), (float) (cos(roll)*sin(pitch)*sin(yaw)-sin(roll)*cos(yaw)), (float) (cos(roll)*sin(pitch)*cos(yaw)+sin(roll)*sin(yaw)), getTransform().getTranslation().getX(),
                (float) (sin(roll)*cos(pitch)), (float) (getTransform().getScale() * (sin(roll)*sin(pitch)*sin(yaw)+cos(roll)*cos(yaw))), (float) (sin(roll)*sin(pitch)*cos(yaw)-cos(roll)*sin(yaw)), getTransform().getTranslation().getY(),
                (float) -sin(pitch), (float) (cos(pitch)*sin(yaw)), (float) (getTransform().getScale() * (cos(pitch)*cos(yaw))), getTransform().getTranslation().getZ(),
                0,0,0,1
        });

        for (Vector3f point : vertices) {
            Vector4f homogenised = new Vector4f(point.minus(pointOfRotation), 1);
            Vector4f transformed = transformMatrix.multiply(homogenised);
            rotated.add(new Vector3f(transformed.getX() + pointOfRotation.getX(),
                    transformed.getY() + pointOfRotation.getY(),
                    transformed.getZ() + pointOfRotation.getZ()));
        }
        transformedVertices = rotated.toArray(transformedVertices);
    }

    public BasicMesh getMesh() {
        return mesh;
    }

    public SceneObject setColour(Colour colour) {
        this.colour = colour;
        return this;
    }
}
