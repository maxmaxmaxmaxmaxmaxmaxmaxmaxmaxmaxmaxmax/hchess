package com.earthmelon.hchess;

import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Mesh;
import com.earthmelon.render.MeshLoader;

import static com.earthmelon.hchess.PieceType.*;

public class PieceGrid {

    // Size of one square in pixels
    public static final int gridSize = 80;

    public static final int topLeftWidth = 320;
    public static final int topLeftHeight = 60;

    Mesh background = MeshLoader.createQuad(new Vector3f(-0.5f,-0.75f,0)).addTexture("board.png");;
    static PieceType[] boardState = {wROOK, wKNIGHT, wBISHOP, wQUEEN, wKING, wBISHOP, wKNIGHT, wROOK,
                wPAWN, wPAWN, wPAWN, wPAWN, wPAWN, wPAWN, wPAWN, wPAWN,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                bPAWN, bPAWN, bPAWN, bPAWN, bPAWN, bPAWN, bPAWN, bPAWN,
        bROOK, bKNIGHT, bBISHOP, bQUEEN, bKING, bBISHOP, bKNIGHT, bROOK};

    public static Piece getPiece(int row, int column) {
        return new Piece(boardState[8*row+column], row, column);
    }

    public static Piece selectPiece(double mouseX, double mouseY) {
        if (mouseX < topLeftWidth || mouseX > topLeftWidth + 8 * gridSize) {
            return null;
        }
        if (mouseY < topLeftHeight || mouseY > topLeftHeight + 8 * gridSize) {
            return null;
        }
        return getPiece((int) (8 - (mouseY - topLeftHeight) / gridSize), (int) ((mouseX - topLeftWidth) / gridSize));
    }

    public void renderBoard() {
        for (int i=0; i<boardState.length; i++) {
            int row = i / 8;
            int col = i % 8;
            Piece piece = getPiece(row, col);
            if (piece.type != null) {
                Vector3f square = new Vector3f((float) col / 8 - 0.5f, (float) row / 5 - 0.75f,0);
                MeshLoader.createQuad(square, 1f/8).addTexture(piece.getTexture());
            }
        }
    }
}
