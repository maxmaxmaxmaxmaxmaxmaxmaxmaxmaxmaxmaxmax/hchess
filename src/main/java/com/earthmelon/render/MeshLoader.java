package com.earthmelon.render;

import java.nio.FloatBuffer; //The buffers that the Vertex data is ultimately stored in
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List; //List and ArrayLists are containers for storing data, in this case the VBO/VAO IDs

import com.earthmelon.math.Vector3f;
import com.earthmelon.render.shader.Render;
import org.lwjgl.BufferUtils; //For creating the FloatBuffer
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class MeshLoader{
    // Add to these so they get destroyed when program exits, apparently
    private static List<Integer> vaos = new ArrayList<Integer>();
    private static List<Integer> vbos = new ArrayList<Integer>();
    private static List<Integer> textures = new ArrayList<>();

    private static MeshLoader instance = new MeshLoader();

    public static MeshLoader getInstance() {
        return instance;
    }

    public void addTexture(Integer textureID) {
        textures.add(textureID);
    }

    private static FloatBuffer createFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    private static IntBuffer createIntBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    private static void storeData(int attribute, int dimensions, float[] data) {
        int vbo = GL15.glGenBuffers(); //Creates a VBO ID
        vbos.add(vbo);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo); //Loads the current VBO to store the data
        FloatBuffer buffer = createFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attribute, dimensions, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0); //Unloads the current VBO when done.
    }

    private static void bindIndices(int[] data) {
        int vbo = GL15.glGenBuffers();
        vbos.add(vbo);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vbo);
        IntBuffer buffer = createIntBuffer(data);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    public static Mesh createMesh(float[] positions, float[] UVs, int[] indices){
        int vao = genVAO();
        storeData(0,3,positions);
        storeData(1,2,UVs);
        bindIndices(indices);
        GL30.glBindVertexArray(0);
        return new Mesh(vao,indices.length);
    }

    private static int genVAO() {
        int vao = GL30.glGenVertexArrays();
        vaos.add(vao);
        GL30.glBindVertexArray(vao);
        return vao;
    }

    public static Mesh createQuad(Vector3f pos, float scale) {
        float aspect_ratio = Window.aspectRatio;
        System.out.println(aspect_ratio);
        int vao = genVAO();
        int[] indices = {0,1,2,3,4,5};
        Vector3f[] vertices = new Vector3f[]{pos, pos.plus(scale,0,0), pos.plus(0,scale*aspect_ratio,0), pos.plus(scale,0,0), pos.plus(0,scale*aspect_ratio,0), pos.plus(scale,scale*aspect_ratio,0)};
        float[] uvs = {0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0};
        float[] positions = Vector3f.toFloat(vertices);
        storeData(0,3,positions);
        storeData(1,2,uvs);
        bindIndices(indices);
        GL30.glBindVertexArray(0);
        Mesh out = new Mesh(vao, indices.length);
        Render.toRender.add(out);
        return out;
    }

    public static Mesh createQuad(Vector3f pos) {
        return createQuad(pos, 1);
    }


}
