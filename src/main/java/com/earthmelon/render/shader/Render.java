package com.earthmelon.render.shader;

import com.earthmelon.render.BasicMesh;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

// Look at https://learnopengl.com/Getting-started/Hello-Triangle


public class Render {
    ShaderTextured shader = new ShaderTextured();

    public void cleanup(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1,1,1,1);
    }

    public void render(BasicMesh basicMesh){
        shader.start(basicMesh);
        GL30.glBindVertexArray(basicMesh.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, basicMesh.getTexture());
        GL11.glDrawElements(GL11.GL_TRIANGLES, basicMesh.getVertexCount(), GL11.GL_UNSIGNED_INT,0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        shader.stop();
    }

    public void render(BasicMesh[] basicMeshes) {
        for (BasicMesh basicMesh : basicMeshes) {
            render(basicMesh);
        }
    }
}
