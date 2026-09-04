package com.earthmelon.hchess;

public enum PieceType {
    wPAWN(true, "white_pawn.png"),
    wKNIGHT(true,"white_knight.png"),
    wBISHOP(true,"white_bishop.png"),
    wROOK(true,"white_rook.png"),
    wQUEEN(true,"white_queen.png"),
    wKING(true,"white_king.png"),
    bPAWN(false,"black_pawn.png"),
    bKNIGHT(false,"black_knight.png"),
    bBISHOP(false,"black_bishop.png"),
    bROOK(false,"black_rook.png"),
    bQUEEN(false,"black_queen.png"),
    bKING(false,"black_king.png");

    final boolean isWhite;
    final String texture;

    PieceType(boolean isWhite, String texture) {
        this.isWhite = isWhite;
        this.texture = texture;
    }
}
