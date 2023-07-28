package assignment4;

import java.util.Arrays;

public class Move {

    protected Position startPos;
    protected Position endPos;

    public Move(Position startPos, Position endPos){
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public Position getStartPos() {
        return startPos;
    }

    public Position getEndPos() {
        return endPos;
    }

    public void updateBoard(Board board) {
        Piece piece = board.getPiece(startPos);
        if (piece != null) {
            board.removePiece(startPos);
            piece.setPosition(endPos);
            board.addPiece(piece);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Move move = (Move) obj;
        return startPos.equals(move.startPos) && endPos.equals(move.endPos);
    }


}
