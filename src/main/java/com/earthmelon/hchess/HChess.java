package com.earthmelon.hchess;

import com.earthmelon.shader.Render;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class HChess {

    // The window handle
    private long window;

    public void run() {
        init();
        loop();

        Callbacks.glfwFreeCallbacks(window);
        GLFW.glfwDestroyWindow(window);

        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if(!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

//        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 4);
//        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 5);

        window = GLFW.glfwCreateWindow(640, 480, "HChess", NULL, NULL);
        if(window == NULL) {
            throw new IllegalStateException("Unable to create GLFW Window");
        }

        GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {});

        try(MemoryStack stack = stackPush()){
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            GLFW.glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

            GLFW.glfwSetWindowPos(window,(vidmode.width() - pWidth.get(0)) / 2,(vidmode.height() - pHeight.get(0)) / 2);

            GLFW.glfwMakeContextCurrent(window);
            GLFW.glfwSwapInterval(1);
            GLFW.glfwShowWindow(window);
        }
    }

    private void loop() {
        GL.createCapabilities();
        float[] vertices = {-0.5f,-0.5f,0f,
                0.5f, -0.5f, 0f,
                0f,0.5f,0f};
        int[] indices = {0,1,2};
        float[] uvs = {0.0f, 1.0f};
        Mesh meshmeyek = MeshLoader.createMesh(vertices, uvs, indices).addTexture("bar.png"); //Kudos if you got that reference

        Render render = new Render();
        while(!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);

            render.cleanup();
            render.render(meshmeyek);

            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new HChess().run();
    }

}