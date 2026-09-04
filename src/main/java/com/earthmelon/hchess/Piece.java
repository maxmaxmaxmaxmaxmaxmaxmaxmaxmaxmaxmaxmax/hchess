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

    public Piece() {
        type = PieceType.NONE;
        this.row = -1;
        this.column = -1;
    }

    public boolean isEnemy(Piece other) {
        return type.isEnemy(other.type);
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
        if (row == this.row && col == this.column) {
            return false;
        }
        return this.getAvailableMoves().contains(new Move(this,row,col));
    }

    @Override
    public String toString() {
        return "%s at (%s, %s)".formatted(type, row, column);
    }
}
