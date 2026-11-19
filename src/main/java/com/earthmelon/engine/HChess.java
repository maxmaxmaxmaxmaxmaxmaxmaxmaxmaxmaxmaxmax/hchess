package com.earthmelon.engine;

import com.earthmelon.math.Matrix4f;
import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Render;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class HChess {

    // The window handle
    private static Window window;
    public static ArrayList<SceneObject> SCENE_OBJECTS = new ArrayList<>();

    SceneObject box;

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
                case GLFW.GLFW_KEY_A -> moveDir = new Vector3f(0.1f, 0, 0);
                case GLFW.GLFW_KEY_D -> moveDir = new Vector3f(-0.1f, 0, 0);
                case GLFW.GLFW_KEY_W -> moveDir = new Vector3f(0, 0, 0.1f);
                case GLFW.GLFW_KEY_S -> moveDir = new Vector3f(0, 0, -0.1f);
            }

            for (SceneObject object : SCENE_OBJECTS) {
                object.getTransform().translate(moveDir);
            }
        }

    }

    public void run() {
        window = Window.createWindow(1280, 800);
//        Light light = new Light("light", new Vector3f(0.5f, 0.5f, 0.5f));
        box = new SceneObject("box", new Vector3f(0,0,0));
        box.getTransform().translate(new Vector3f(-0.5f, -0.5f, -50f));
        loop();
        window.terminate();
    }

    private void loop() {
        Render render = new Render();
        while(!window.shouldClose()) {
            UIElement.calculateFrames();

            render.render();

            window.update();
            render.cleanup();

            box.getTransform().rotate(new Vector3f(0.01f, 0.001f, 0.004f));
        }
    }

    public static void main(String[] args) {
        new HChess().run();
    }


}