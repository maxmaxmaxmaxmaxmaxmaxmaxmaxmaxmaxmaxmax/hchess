package com.earthmelon.engine;

import com.earthmelon.math.Matrix4f;
import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Colour;
import com.earthmelon.render.Render;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.HashMap;

public class HChess {

    // The window handle
    private static Window window;
    public static HashMap<String, SceneObject> SCENE_OBJECTS = new HashMap<>();

    public static float cameraMoveSpeed = 1.5f;

    SceneObject box;
    SceneObject box2;
    SceneObject box3;

    public static Matrix4f makeViewFrustum(float fovX, float aspectRatio, float front, float back) {

        float degreeToRadians = (float) (Math.PI / 180);

        float tangent = (float) Math.tan(fovX/2 * degreeToRadians);
        float right = front * tangent;
        float top = right / aspectRatio;

        return new Matrix4f(new float[]{
                front / right,0,0,0,
                0,front / top,0,0,
                0,0,-(back+front) / (back-front),-1,
                0,0,-(2 * back * front) / (back - front),0});
    }

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
        box = OBJHandler.importFromOBJ("box", "teapot.obj").setColour(Colour.BLUE);
        box2 = OBJHandler.importFromOBJ("box2", "cube.obj").setColour(Colour.CYAN);
        box2.getTransform().translate(new Vector3f(10, 10, -30f));
        box3 = OBJHandler.importFromOBJ("box3", "cube.obj").setColour(Colour.RED);
        box3.getTransform().translate(new Vector3f(-20, -10, -100f));

        System.out.println(box.position);

        loop();
        window.terminate();
    }

    private void loop() {
        Render render = new Render();
        while(!window.shouldClose()) {
            UIElement.calculateFrames();

            render.render();

            box2.getTransform().rotate(new Vector3f(0.0001f, 0.01f, -0.02f));
            box.getTransform().rotate(new Vector3f(0f, 0.01f, 0));
            box.getTransform().setScale(40);

            window.update();
            render.cleanup();
        }
    }

    public static void main(String[] args) {
        new HChess().run();
    }


}