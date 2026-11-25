package com.earthmelon.render;

import com.earthmelon.engine.object.SceneObject;
import com.earthmelon.engine.UIElement;
import com.earthmelon.render.meshes.Mesh;
import com.earthmelon.render.meshes.Mesh2D;
import com.earthmelon.render.shader.Shader;
import com.earthmelon.render.shader.Shader2D;
import com.earthmelon.render.shader.ShaderTextured;
import com.earthmelon.render.shader.ShaderUntextured;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import static com.earthmelon.engine.HChess.SCENE_OBJECTS;

// Look at https://learnopengl.com/Getting-started/Hello-Triangle

public class Render {

    Shader[] shaders = new Shader[]{new ShaderTextured(), new ShaderUntextured(), new Shader2D()};

    public void cleanup(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1,1,1,1);
    }

    public void render() {
        render(UIElement.fpsCounter.getRenderables());
        for (SceneObject obj : SCENE_OBJECTS.values()) {
            render(obj.getMeshes());
        }
    }

    public void render(Mesh[] meshes) {
        for (Mesh mesh : meshes) {
            render(mesh);
        }
    }

    public void render(Mesh mesh){
        if (mesh == null) return;

        // Meshes with no texture can still be coloured.
        Shader shader = (mesh.getTexture() == 0 ? shaders[1] : shaders[0]);

        if (mesh instanceof Mesh2D) shader = shaders[2];

        shader.start(mesh);
        GL30.glBindVertexArray(mesh.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, mesh.getTexture());
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getVertexCount(), GL11.GL_UNSIGNED_INT,0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        shader.stop();
    }
}
