package com.earthmelon.engine;

import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Colour;
import com.earthmelon.render.Render;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;

public class HChess {

    // The window handle
    private static Window window;
    public static HashMap<String, SceneObject> SCENE_OBJECTS = new HashMap<>();

    public static float cameraMoveSpeed = 1.5f;

    SceneObject teapot;
    SceneObject iphone;
    SceneObject ship;

    public static void calculateKeyInput(int key, int action) {
        Vector3f moveDir = new Vector3f(0,0,0);
        if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
            switch (key) {
                case GLFW.GLFW_KEY_A -> moveDir = new Vector3f(cameraMoveSpeed, 0, 0);
                case GLFW.GLFW_KEY_D -> moveDir = new Vector3f(-cameraMoveSpeed, 0, 0);
                case GLFW.GLFW_KEY_W -> moveDir = new Vector3f(0, 0, cameraMoveSpeed);
                case GLFW.GLFW_KEY_S -> moveDir = new Vector3f(0, 0, -cameraMoveSpeed);
                case GLFW.GLFW_KEY_Q -> moveDir = new Vector3f(0, cameraMoveSpeed, 0);
                case GLFW.GLFW_KEY_E -> moveDir = new Vector3f(0, -cameraMoveSpeed, 0);
            }

            for (SceneObject object : SCENE_OBJECTS.values()) {
                object.getTransform().translate(moveDir);
            }
        }

    }

    public void run() {
        window = Window.createWindow(1280, 800);
//        Light light = new Light("light", new Vector3f(0.5f, 0.5f, 0.5f));
        teapot = OBJHandler.importFromOBJ("teapot", "teapot.obj").setColour(Colour.BLUE);
        teapot.getTransform().translate(new Vector3f(5, 10, 20));
        iphone = OBJHandler.importFromOBJ("iphone", "iphone_13_toilet.obj").setColour(Colour.RED);
        ship = OBJHandler.importFromOBJ("ship", "EverGiven.obj").setColour(Colour.GREEN);
        ship.getTransform().scale(0.03f).translate(new Vector3f(70, 0 ,0));

        System.out.println(teapot.position);

        loop();
        window.terminate();
    }

    private void loop() {
        Render render = new Render();
        while(!window.shouldClose()) {
            UIElement.calculateFrames();

            render.render();

            teapot.getTransform().rotate(new Vector3f(0.0f, 0.01f, 0));
            iphone.getTransform().rotate(new Vector3f(0.01f, 0, 0.001f));
            ship.getTransform().rotate(new Vector3f(0, 0.01f, 0.05f));

            window.update();
            render.cleanup();
        }
    }

    public static void main(String[] args) {
        new HChess().run();
    }


}