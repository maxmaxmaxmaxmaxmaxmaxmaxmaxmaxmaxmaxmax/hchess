package com.earthmelon.hchess;

import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Mesh;
import com.earthmelon.render.MeshLoader;
import org.lwjgl.opengl.GL11;

import static com.earthmelon.hchess.PieceType.*;

public class Board {

    // Size of one square in pixels
    public static final int gridSize = 80;

    public static final int topLeftWidth = 320;
    public static final int topLeftHeight = 60;

    public static Turn turn = Turn.WHITE;

    public enum Turn {
        WHITE,
        BLACK;

        public Turn swap() {
            if (this.equals(WHITE)) {
                return BLACK;
            }
            return WHITE;
        }
    }

    static PieceType[] boardState = {
            wROOK, wKNIGHT, wBISHOP, wQUEEN, wKING, wBISHOP, wKNIGHT, wROOK,
            wPAWN, wPAWN, wPAWN, wPAWN, wPAWN, wPAWN, wPAWN, wPAWN,
            NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE,
            NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE,
            bPAWN, bPAWN, bPAWN, bPAWN, bPAWN, bPAWN, bPAWN, bPAWN,
            bROOK, bKNIGHT, bBISHOP, bQUEEN, bKING, bBISHOP, bKNIGHT, bROOK
    };

    private static Piece getPiece(int row, int column) {
        return new Piece(boardState[8*row+column], row, column);
    }

    public static Piece selectPiece(double mouseX, double mouseY) {
        if (mouseX < topLeftWidth || mouseX > topLeftWidth + 8 * gridSize) {
            return new Piece(NONE, -1, -1);
        }
        if (mouseY < topLeftHeight || mouseY > topLeftHeight + 8 * gridSize) {
            return new Piece(NONE, -1, -1);
        }
        return getPiece((int) (8 - (mouseY - topLeftHeight) / gridSize), (int) ((mouseX - topLeftWidth) / gridSize));
    }

    public static void setPiece(Piece piece, int row, int col) {
        boardState[8*piece.row+piece.column] = NONE;
        boardState[8*row+col] = piece.type;
    }

    public void render() {
        GL11.glClearColor(0,0,0,0);
        MeshLoader.createQuad(new Vector3f(-0.5f,-0.75f,0)).addTexture("board.png");
        for (int i=0; i<boardState.length; i++) {
            int row = i / 8;
            int col = i % 8;
            Piece piece = getPiece(row, col);
            if (piece.type != NONE) {
                Vector3f square = new Vector3f((float) col / 8 - 0.5f, (float) row / 5 - 0.75f,0);
                MeshLoader.createQuad(square, 1f/8).addTexture(piece.getTexture());
            }
        }
    }
}
