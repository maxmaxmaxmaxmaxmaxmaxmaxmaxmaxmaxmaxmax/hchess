package com.earthmelon.engine;

import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Colour;
import com.earthmelon.render.MeshFactory;
import com.earthmelon.render.Transform;
import com.earthmelon.render.meshes.BasicMesh;

public class SceneObject {

    String id;
    Vector3f position;

    Vector3f[] vertices;

    Transform transform;
    Vector3f[] transformedVertices;

    public SceneObject(String id) {
        this.id = id;
        this.position = new Vector3f(0,0,0);

        // Default until .obj conversion works.
        BasicMesh box = MeshFactory.createQuad(position).setTint(Colour.YELLOW);
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

    }
}
