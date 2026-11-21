package com.earthmelon.render.shader;

import com.earthmelon.engine.HChess;
import com.earthmelon.engine.Window;
import com.earthmelon.math.Vector3f;
import com.earthmelon.render.meshes.Mesh;
import org.lwjgl.opengl.GL20;

public class ShaderUntextured extends Shader {


    public ShaderUntextured() {
        super("Textured.vs", "Untextured.fs");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "texCoords");
    }

    @Override
    protected void getAllUniformLocations() {

    }

    @Override
    public void start(Mesh mesh) {
        super.start(mesh);
        int perspectiveMatrixLocation = GL20.glGetUniformLocation(programID, "ppmatrix");
        int lightLocation = GL20.glGetUniformLocation(programID, "lightpos");
        Vector3f lightpos = HChess.SCENE_OBJECTS.get("light").position;
        GL20.glUniform3fv(lightLocation, lightpos.values());
        GL20.glUniformMatrix4fv(perspectiveMatrixLocation, false, makeViewFrustum(45.0f, Window.aspectRatio, 1f, 100f).values());
        GL20.glEnableVertexAttribArray(2);
    }

    @Override
    public void stop() {
        GL20.glDisableVertexAttribArray(2);
        super.stop();
    }
}
