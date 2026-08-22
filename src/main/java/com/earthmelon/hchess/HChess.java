package com.earthmelon.hchess;

import com.earthmelon.render.Window;
import com.earthmelon.render.shader.Render;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class HChess {

    // The window handle
    private static Window window;

    public void run() {
        window = new Window(640, 400);
        loop();
        window.terminate();
    }

    private void loop() {
        float[] vertices = {0f,0f,0f,
                            1f,0f,0f,
                            0f,1f,0f,
                            1f,0f,0f,
                            0f,1f,0f,
                            1f,1f,0f};
        int[] indices = {0,1,2,3};
        float[] uvs = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};
        Mesh meshmeyek = MeshLoader.createMesh(vertices, uvs, indices).addTexture("bar.png"); //Kudos if you got that reference

        Render render = new Render();
        while(!window.shouldClose()) {
            render.cleanup();
            render.render(meshmeyek);

            window.update();
        }
    }

    public static void main(String[] args) {
        new HChess().run();
    }

}