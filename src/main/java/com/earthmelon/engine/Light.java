package com.earthmelon.engine;

import com.earthmelon.math.Vector3f;

public class Light extends SceneObject {

    public Light(String id, Vector3f position) {
        super(id, position);
        pointOfRotation = position;

        HChess.SCENE_OBJECTS.add(this);
    }
}
