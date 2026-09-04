package com.earthmelon.hchess;

import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Mesh;
import com.earthmelon.render.MeshLoader;
import com.earthmelon.render.Window;
import com.earthmelon.render.shader.Render;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import java.nio.DoubleBuffer;

public class HChess {

    // The window handle
    private static Window window;

    public void run() {
        window = Window.createWindow(1280, 800);
        loop();
        window.terminate();
    }

    private void loop() {
        PieceGrid grid = new PieceGrid();
        grid.renderBoard();

        Render render = new Render();
        while(!window.shouldClose()) {
            render.cleanup();
            for (Mesh mesh : Render.toRender) {
                render.render(mesh);
            }

            gameLogic();

            window.update();
        }
    }

    private void gameLogic() {
        DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);

        GLFW.glfwGetCursorPos(window.window, xBuffer, yBuffer);
        double mouseX = xBuffer.get(0);
        double mouseY = yBuffer.get(0);

        if (GLFW.glfwGetMouseButton(window.window, GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS) {
            System.out.println(PieceGrid.selectPiece(mouseX, mouseY));
        }
    }

    public static void main(String[] args) {
        new HChess().run();
    }

}