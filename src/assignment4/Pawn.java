package assignment4;

import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(Position position, Constants.PieceColor color) {
        super(position, color);
    }

    @Override
    public char getMarker() {
        return 'P';
    }

    @Override
    public ArrayList<Move> getPossibleMoves(Board board) {
        ArrayList<Move> possibleMoves = new ArrayList<>();
        int direction = (color == Constants.PieceColor.Black) ? 1 : -1;
        Position currentPos = getPosition();
        int currentRow = currentPos.getRow();
        int currentCol = currentPos.getCol();

        // Check if moving forward is a valid move
        Position forwardPos = new Position(currentRow + direction, currentCol);
        if (board.getPiece(forwardPos) == null) {
            possibleMoves.add(new Move(currentPos, forwardPos));
        }

        // Check if capturing diagonally is a valid move
        Position captureLeftPos = new Position(currentRow + direction, currentCol - 1);
        Position captureRightPos = new Position(currentRow + direction, currentCol + 1);
        Piece pieceLeft = board.getPiece(captureLeftPos);
        Piece pieceRight = board.getPiece(captureRightPos);
        if (pieceLeft != null && pieceLeft.getColor() != color) {
            possibleMoves.add(new Move(currentPos, captureLeftPos));
        }
        if (pieceRight != null && pieceRight.getColor() != color) {
            possibleMoves.add(new Move(currentPos, captureRightPos));
        }

        return possibleMoves;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pawn other = (Pawn) obj;
        return position.equals(other.position) && color == other.color;
    }

}
