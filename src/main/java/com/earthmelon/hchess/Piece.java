package com.earthmelon.hchess;

import com.earthmelon.render.Texture;

import java.util.ArrayList;
import java.util.Objects;

public class Piece {

    PieceType type;
    int row;
    int column;

    public Piece(PieceType pieceType, int row, int column) {
        type = pieceType;
        this.row = row;
        this.column = column;
    }

    public boolean isWhite() {
        return type.isWhite;
    }

    public String getTexture() {
        return type.texture;
    }

    public ArrayList<Move> getAvailableMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                moves.add(new Move(this, i, j));
            }
        }
        return moves;
    }

    public boolean canMoveToSquare(int row, int col) {
        return this.getAvailableMoves().contains(new Move(this,row,col));
    }

    @Override
    public String toString() {
        return "%s at (%s, %s)".formatted(type, row, column);
    }
}
