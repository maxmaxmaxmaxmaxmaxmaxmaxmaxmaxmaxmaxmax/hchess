package com.earthmelon.hchess;

import com.earthmelon.math.Vector3f;
import com.earthmelon.math.Vector4f;
import com.earthmelon.render.*;
import com.earthmelon.render.meshes.BasicMesh;
import com.earthmelon.render.shader.Render;
import com.earthmelon.render.shader.Renderable;

import java.util.ArrayList;

public class HChess {

    // The window handle
    private static Window window;
    public static ArrayList<Renderable> RENDERABLES = new ArrayList<>();

    public void run() {
        window = Window.createWindow(1280, 800);
        BasicMesh box = MeshFactory.createQuad(new Vector3f(0,0,0)).setTint(new Vector4f(1,1,0,1));
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