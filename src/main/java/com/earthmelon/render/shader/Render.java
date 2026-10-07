package com.earthmelon.render.shader;

import com.earthmelon.render.meshes.Mesh;
import com.earthmelon.render.meshes.BasicMesh;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

// Look at https://learnopengl.com/Getting-started/Hello-Triangle


public class Render {
    static ShaderTextured shader = new ShaderTextured();

    private static final LinkedList<Mesh> visibleMeshes = new LinkedList<>();

    public static void notifyRender(Mesh mesh) {
        visibleMeshes.add(mesh);
    }

    public static void notifyRender(Mesh[] meshes) {
        visibleMeshes.addAll(Arrays.asList(meshes));
    }

    public void cleanup(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1,1,1,1);
    }

    public void render() {
        while (!visibleMeshes.isEmpty()) {
            render(visibleMeshes.poll());
        }
    }

    public static void render(Mesh mesh){
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
