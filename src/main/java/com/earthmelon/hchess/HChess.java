package com.earthmelon.hchess;

import com.earthmelon.math.Vector3f;
import com.earthmelon.math.Vector4f;
import com.earthmelon.render.Window;
import com.earthmelon.render.shader.Render;

public class HChess {

    // The window handle
    private static Window window;
    HTextBox fpsCounter;

    public void run() {
        window = Window.createWindow(1280, 800);
        loop();
        window.terminate();
    }

    private void loop() {

        HTextBox box = new HTextBox(new Vector3f(-0.5f,1f,0), 0.8f, 0.5f)
                .setText("My text is dynamically changing colour!", 0.08f)
                .setTextTint(new Vector4f(0,0.6f,0.9f,1))
                .setTint(new Vector4f(1, 1, 0, 1));

        Render render = new Render();
        float rtri = 0;

        while(!window.shouldClose()) {
            render.cleanup();
            UIElement.calculateFrames();
            render.render(UIElement.getFPSBox());


            render.render(box);
            box.setTextTint(new Vector4f((float) Math.sin(rtri) / 2 + 0.5f, 0.6f, 0.9f, 1));
//            box.setText(String.valueOf(Math.random()));
            window.update();
            rtri += 0.01f;
        }
    }

    public static void main(String[] args) {
        new HChess().run();
    }

}