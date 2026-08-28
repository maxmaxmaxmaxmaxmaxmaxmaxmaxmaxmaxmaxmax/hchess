package com.earthmelon.hchess;

import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Window;
import com.earthmelon.render.shader.Render;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class HChess {

    // The window handle
    private static Window window;

    public void run() {
        window = new Window(640, 400);
        loop();
        window.terminate();
    }

    private void loop() {
        Mesh quad = MeshLoader.createQuad(new Vector3f(0,0,0)).addTexture("bar.png"); //Kudos if you got that reference

        Render render = new Render();
        while(!window.shouldClose()) {
            render.cleanup();
            render.render(quad);

            window.update();
        }
    }

    public static void main(String[] args) {
        new HChess().run();
    }

}