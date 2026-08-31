package com.earthmelon.render;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    public long window;
    public static Window instance;
    public static float aspectRatio;

    /* for resizing window */
    private static GLFWFramebufferSizeCallback resizeWindow = new GLFWFramebufferSizeCallback(){
        @Override
        public void invoke(long window, int width, int height){
            GL11.glViewport(0,0,width,height);
            //update any other window vars you might have (aspect ratio, MVP matrices, etc)
        }
    };


    private Window(int width, int height) {
        init(width, height);
    }

    public static Window createWindow(int width, int height) {
        if (instance == null) {
            instance = new Window(width, height);
            return instance;
        } else {
            throw new IllegalStateException("Window already created!");
        }
    }

    public static Window getInstance() {
        return instance;
    }

    private void init(int width, int height) {
        GLFWErrorCallback.createPrint(System.err).set();

        if(!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        window = GLFW.glfwCreateWindow(width, height, "HChess", NULL, NULL);
        if(window == NULL) {
            throw new IllegalStateException("Unable to create GLFW Window");
        }

        GLFW.glfwSetFramebufferSizeCallback(window, resizeWindow);
        GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {});

        GLFW.glfwSetWindowSizeCallback(window,new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                // Handle window resize here
                aspectRatio = (float) width / height;
            }
        });

        try(MemoryStack stack = stackPush()){
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            GLFW.glfwGetWindowSize(window, pWidth, pHeight);
            aspectRatio = (float) pWidth.get() / pHeight.get();

            GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

            GLFW.glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );

            GLFW.glfwMakeContextCurrent(window);
            GLFW.glfwSwapInterval(1);
            GLFW.glfwShowWindow(window);
        }

        GL.createCapabilities();

        GL11.glEnable(GL11.GL_BLEND); // Enabled blending
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); // selects blending method
        GL11.glEnable(GL11.GL_ALPHA_TEST); // allows alpha channels or transperancy
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1f); // sets aplha function
    }

    public void update() {
        GLFW.glfwSwapBuffers(window);
        GLFW.glfwPollEvents();

    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void terminate() {
        Callbacks.glfwFreeCallbacks(window);
        GLFW.glfwDestroyWindow(window);

        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }


}
