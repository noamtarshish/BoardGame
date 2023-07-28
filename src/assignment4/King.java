package assignment4;

import java.util.ArrayList;

public class King extends Piece {
    public King(Position position, Constants.PieceColor color) {
        super(position, color);
    }

    @Override
    public char getMarker() {
        return 'K';
    }

    @Override
    public ArrayList<Move> getPossibleMoves(Board board) {
        ArrayList<Move> possibleMoves = new ArrayList<>();
        Position currentPos = getPosition();
        int currentRow = currentPos.getRow();
        int currentCol = currentPos.getCol();

        checkAllMoves(board, possibleMoves, currentRow, currentCol, 1, 0); //Up;
        checkAllMoves(board, possibleMoves, currentRow, currentCol, -1, 0); //Down;
        checkAllMoves(board, possibleMoves, currentRow, currentCol, 0, -1); //Left;
        checkAllMoves(board, possibleMoves, currentRow, currentCol, 0, 1); //Right;
        checkAllMoves(board, possibleMoves, currentRow, currentCol, 1, 1);  //Up-right
        checkAllMoves(board, possibleMoves, currentRow, currentCol, 1, -1); //Up-left
        checkAllMoves(board, possibleMoves, currentRow, currentCol, -1, 1); //Down-right
        checkAllMoves(board, possibleMoves, currentRow, currentCol, -1, -1); //Down-left

        return possibleMoves;
    }

    public void checkAllMoves(Board board, ArrayList<Move> possibleMoves, int currentRow, int currentCol, int rowDirection, int colDirection) {
        int newRow = currentRow + rowDirection;
        int newCol = currentCol + colDirection;

        Position newPos = new Position(newRow, newCol);
        if (board.checkValidPosition(newPos)) {
            Piece piece = board.getPiece(newPos);
            if (piece == null || piece.getColor() != getColor()) {
                possibleMoves.add(new Move(getPosition(), newPos));
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        King other = (King) obj;
        return position.equals(other.position) && color == other.color;
    }
}
