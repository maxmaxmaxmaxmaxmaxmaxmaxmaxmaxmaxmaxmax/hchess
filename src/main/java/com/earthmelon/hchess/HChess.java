package com.earthmelon.hchess;

import com.earthmelon.math.Vector3f;
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
        HTextBox box = new HTextBox("I love you Yujing, with all my heart and body. Look at my text fitting in the box! Sort of...", new Vector3f(-0.5f,1f,0), 0.5f, 1f);


        Render render = new Render();
        while(!window.shouldClose()) {
            render.cleanup();
            render.render(box);
            box.setText(String.valueOf(Math.random()));
            window.update();
        }
    }

    public static void main(String[] args) {
        new HChess().run();
    }

}