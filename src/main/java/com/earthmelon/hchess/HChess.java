package com.earthmelon.hchess;

import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Mesh;
import com.earthmelon.render.MeshLoader;
import com.earthmelon.render.TextRender;
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
        Mesh text = TextRender.drawChar('a', new Vector3f(-0.5f,0.5f,0)); //Kudos if you got that reference

        Render render = new Render();
        while(!window.shouldClose()) {
            render.cleanup();
            render.render(text);

            window.update();
        }
    }

    public static void main(String[] args) {
        new HChess().run();
    }

}