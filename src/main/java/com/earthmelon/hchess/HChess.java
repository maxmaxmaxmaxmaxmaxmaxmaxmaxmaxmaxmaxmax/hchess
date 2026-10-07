package com.earthmelon.hchess;

import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Window;
import com.earthmelon.render.meshes.Mesh;
import com.earthmelon.render.shader.Render;
import com.earthmelon.render.shader.Renderable;

import java.util.ArrayList;

public class HChess {

    // The window handle
    private static Window window;
    public static ArrayList<Renderable> OBJECTS = new ArrayList<>();

    public void run() {
        window = Window.createWindow(1280, 800);
        HTextBox box = new HTextBox(new Vector3f(0,0,0), 1, 1).setText("Text", 0.05f);
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
        }
    }

    public static void main(String[] args) {
        new HChess().run();
    }

}