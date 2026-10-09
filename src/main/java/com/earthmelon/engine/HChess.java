package com.earthmelon.engine;

import com.earthmelon.hchess.Board;
import com.earthmelon.hchess.Piece;
import com.earthmelon.hchess.PieceType;
import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Colour;
import com.earthmelon.render.MeshFactory;
import com.earthmelon.render.Renderable;
import com.earthmelon.render.Window;
import com.earthmelon.render.meshes.BasicMesh;
import com.earthmelon.render.shader.Render;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

public class HChess {

    // The window handle
    private static Window window;
    public static ArrayList<Renderable> RENDERABLES = new ArrayList<>();
    public static HTextBox INFO_DISPLAY;

    public void run() {
        window = Window.createWindow(1280, 800);
        Board grid = new Board();
        INFO_DISPLAY = new HTextBox(new Vector3f(-0.99f, 0.8f, 0), 0.4f, 1);

        loop();
        window.terminate();
    }

    private void loop() {
        Render render = new Render();
        while(!window.shouldClose()) {
            UIElement.calculateFrames();

            render.render();
            gameLogic();

            window.update();
            render.cleanup();
        }
    }


    Piece selected = new Piece();
    private void gameLogic() {
        DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);

        GLFW.glfwGetCursorPos(window.window, xBuffer, yBuffer);
        double mouseX = xBuffer.get(0);
        double mouseY = yBuffer.get(0);

        // 7 is the Left Click action
        if (Window.MOUSE_STATE == 7) {
            if (selected.type == PieceType.NONE) {
                selected = Board.selectPiece(mouseX, mouseY);
            } else {
                Piece moveSquare = Board.selectPiece(mouseX, mouseY);
                if (selected.canMoveToSquare(moveSquare.row, moveSquare.column)) {
                    Board.setPiece(selected, moveSquare.row, moveSquare.column);
                    selected = new Piece();
                }
            }
        }
    }

    public static void main(String[] args) {
        new HChess().run();
    }

}