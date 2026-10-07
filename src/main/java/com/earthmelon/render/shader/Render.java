package com.earthmelon.render.shader;

import com.earthmelon.render.meshes.Mesh;
import com.earthmelon.render.meshes.BasicMesh;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.*;

import static com.earthmelon.hchess.HChess.OBJECTS;

// Look at https://learnopengl.com/Getting-started/Hello-Triangle


public class Render {
    static ShaderTextured shader = new ShaderTextured();

    private static final Stack<Mesh> visibleMeshes = new Stack<>();

    public void cleanup(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1,1,1,1);
    }

    public void render() {
        for (Renderable obj : OBJECTS) {
            render(obj.getRenderables());
        }
    }

    public void render(Mesh[] meshes) {
        for (Mesh mesh : meshes) {
            render(mesh);
        }
    }

    public void render(Mesh mesh){
        if (mesh == null) return;
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
