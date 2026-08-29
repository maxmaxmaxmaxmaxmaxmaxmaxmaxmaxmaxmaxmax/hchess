package com.earthmelon.hchess;

import com.earthmelon.math.Vector3f;
import com.earthmelon.render.Mesh;
import com.earthmelon.render.MeshLoader;

import static com.earthmelon.hchess.Piece.*;

public class PieceGrid {

    Mesh background = MeshLoader.createQuad(new Vector3f(-0.5f,-0.75f,0)).addTexture("board.png");;
    static Piece[] boardState = {wROOK, wKNIGHT, wBISHOP, wQUEEN, wKING, wBISHOP, wKNIGHT, wROOK,
                wPAWN, wPAWN, wPAWN, wPAWN, wPAWN, wPAWN, wPAWN, wPAWN,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                bPAWN, bPAWN, bPAWN, bPAWN, bPAWN, bPAWN, bPAWN, bPAWN,
        bROOK, bKNIGHT, bBISHOP, bQUEEN, bKING, bBISHOP, bKNIGHT, bROOK};

    public Piece selectPiece(int row, int column) {
        return boardState[8*row+column];
    }

    public void renderBoard() {
        for (int i=0; i<boardState.length; i++) {
            int row = i / 8;
            int col = i % 8;
            Piece piece = selectPiece(row, col);
            if (piece != null) {
                Vector3f square = new Vector3f((float) col / 8 - 0.5f, (float) row / 5 - 0.75f,0);
                MeshLoader.createQuad(square, 1f/8).addTexture(piece.getTexture());
            }
        }
    }
}
