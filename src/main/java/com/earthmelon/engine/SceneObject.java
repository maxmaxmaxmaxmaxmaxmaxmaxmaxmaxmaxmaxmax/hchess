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
import java.util.Objects;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class SceneObject {

    String id;
    public Vector3f position;

    public Vector3f[] vertices;
    public float[] uvs;
    public int[] indices;
    public float[] normals;

    Transform transform;
    public Vector3f[] transformedVertices;

    BasicMesh mesh;
    Colour colour = Colour.GREY;

    /**
     * Used for when there is no associated mesh.
     * @param id
     * @param position
     */
    public SceneObject(String id, Vector3f position) {
        this.id = id;
        this.position = position;

        attachTransform();
        if (HChess.SCENE_OBJECTS.containsKey(id)) throw new IllegalArgumentException("Duplicate id!");
        HChess.SCENE_OBJECTS.put(id, this);
    }

    public SceneObject(String id, Vector3f[] vertices, float[] uvs, int[] indices, float[] normals) {
        this.id = id;
        this.vertices = vertices;
        this.uvs = uvs;
        this.indices = indices;
        this.normals =
//                normals.length > 0 ? normals :
                MeshFactory.generateNormals(Vector3f.toFloat(vertices));

        this.position = new Vector3f(calculateCentre(vertices, 0),calculateCentre(vertices, 1),calculateCentre(vertices, 2));
        this.transformedVertices = Arrays.copyOf(vertices, vertices.length);

        mesh = MeshFactory.createMesh(this).setTint(colour);

        attachTransform();
        if (HChess.SCENE_OBJECTS.containsKey(id)) throw new IllegalArgumentException("Duplicate id!");
        HChess.SCENE_OBJECTS.put(id, this);
    }

    private float calculateCentre(Vector3f[] vertices, int axis) {
        float centre = 0.0f;

        for (Vector3f vertex : vertices) {
            centre += switch(axis) {
                case 0 -> vertex.getX();
                case 1 -> vertex.getY();
                case 2 -> vertex.getZ();
                default -> throw new IllegalArgumentException();
            };
        }
        return centre / vertices.length;
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
        position.plus(getTransform().getTranslation());
        if (!(this instanceof Light)) {
            applyTransformation(Arrays.copyOf(vertices, vertices.length));
            mesh = MeshFactory.createMesh(this).setTint(colour);
        }
    }

    private void applyTransformation(Vector3f[] vertices) {
        ArrayList<Vector3f> rotated = new ArrayList<>();
        float yaw = transform.getRotation().getX();
        float pitch = transform.getRotation().getY();
        float roll = transform.getRotation().getZ();

        Matrix4f transformMatrix = new Matrix4f(new float[]{
                (float) (getTransform().getScaleX() * (cos(roll) * cos(pitch))), (float) (getTransform().getScaleY() * (cos(roll)*sin(pitch)*sin(yaw)-sin(roll)*cos(yaw))), (float) (getTransform().getScaleZ() * (cos(roll)*sin(pitch)*cos(yaw)+sin(roll)*sin(yaw))), getTransform().getTranslation().getX(),
                (float) (getTransform().getScaleX() * sin(roll)*cos(pitch)), (float) (getTransform().getScaleY() * (sin(roll)*sin(pitch)*sin(yaw)+cos(roll)*cos(yaw))), (float) (getTransform().getScaleZ() * (sin(roll)*sin(pitch)*cos(yaw)-cos(roll)*sin(yaw))), getTransform().getTranslation().getY(),
                (float) (getTransform().getScaleX() * -sin(pitch)), (float) (getTransform().getScaleY() * cos(pitch)*sin(yaw)), (float) (getTransform().getScaleZ() * (cos(pitch)*cos(yaw))), getTransform().getTranslation().getZ(),
                0,0,0,1
        });

        for (Vector3f point : vertices) {
            Vector4f homogenised = new Vector4f(point.minus(position), 1);
            Vector4f transformed = transformMatrix.multiply(homogenised);
            rotated.add(new Vector3f(transformed.getX() + position.getX(),
                    transformed.getY() + position.getY(),
                    transformed.getZ() + position.getZ()));
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
