package com.earthmelon.engine;

import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Render;

import java.util.ArrayList;

public class HChess {

    // The window handle
    private static Window window;
    public static ArrayList<SceneObject> SCENE_OBJECTS = new ArrayList<>();

    SceneObject box;

    public void run() {
        window = Window.createWindow(1280, 800);
        box = new SceneObject("box", new Vector3f(0,0,0));
//        box.getTransform().translate(new Vector3f(-0.5f, -0.5f, 0.5f));
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