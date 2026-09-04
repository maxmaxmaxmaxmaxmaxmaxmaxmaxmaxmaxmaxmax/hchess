package com.earthmelon.hchess;

import com.earthmelon.render.Mesh;
import com.earthmelon.render.Window;
import com.earthmelon.render.shader.Render;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import java.nio.DoubleBuffer;

import static com.earthmelon.hchess.Board.turn;

public class HChess {

    // The window handle
    private static Window window;

    public void run() {
        window = Window.createWindow(1280, 800);
        loop();
        window.terminate();
    }

    private void loop() {
        Board grid = new Board();


        Render render = new Render();
        while(!window.shouldClose()) {
            render.cleanup();
            grid.renderBoard();
            for (Mesh mesh : Render.toRender) {
                render.render(mesh);
            }

            gameLogic();

            window.update();
        }
    }


    Piece selected = new Piece();
    private void gameLogic() {
        DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);

        GLFW.glfwGetCursorPos(window.window, xBuffer, yBuffer);
        double mouseX = xBuffer.get(0);
        double mouseY = yBuffer.get(0);

        if (GLFW.glfwGetMouseButton(window.window, GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS) {
            System.out.println(selected);
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