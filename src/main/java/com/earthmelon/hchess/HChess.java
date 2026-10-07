package com.earthmelon.hchess;

import com.earthmelon.math.Vector3f;
import com.earthmelon.math.Vector4f;
import com.earthmelon.render.Window;
import com.earthmelon.render.shader.Render;

public class HChess {

    // The window handle
    private static Window window;

    public void run() {
        window = Window.createWindow(1280, 800);
        loop();
        window.terminate();
    }

    private void loop() {
        Render render = new Render();
        while(!window.shouldClose()) {
            UIElement.calculateFrames();

            Render.notifyRender(UIElement.getFPSBox().getRenderables());
            render.render();

            window.update();
            render.cleanup();
        }
    }

    public static void main(String[] args) {
        new HChess().run();
    }

}