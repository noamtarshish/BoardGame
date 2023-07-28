package assignment4;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(Position position, Constants.PieceColor color) {
        super(position, color);
    }

    @Override
    public char getMarker() {
        return 'B';
    }
    @Override
    public ArrayList<Move> getPossibleMoves(Board board) {
        ArrayList<Move> possibleMoves = new ArrayList<>();
        Position currentPos = getPosition();
        int currentRow = currentPos.getRow();
        int currentCol = currentPos.getCol();

        checkDiagonalMoves(board, possibleMoves, currentRow, currentCol, 1, 1);  // Up-right
        checkDiagonalMoves(board, possibleMoves, currentRow, currentCol, 1, -1); // Up-left
        checkDiagonalMoves(board, possibleMoves, currentRow, currentCol, -1, 1); // Down-right
        checkDiagonalMoves(board, possibleMoves, currentRow, currentCol, -1, -1); // Down-left

        return possibleMoves;
    }

    public void checkDiagonalMoves(Board board, ArrayList<Move> possibleMoves, int currentRow, int currentCol,
                                    int rowDirection, int colDirection) {
        int newRow = currentRow + rowDirection;
        int newCol = currentCol + colDirection;

        while (board.checkValidPosition(new Position(newRow, newCol))) {
            Position newPos = new Position(newRow, newCol);
            Piece piece = board.getPiece(newPos);

            if (piece == null) {
                possibleMoves.add(new Move(getPosition(), newPos));
            } else if (piece.getColor() != getColor()) {
                possibleMoves.add(new Move(getPosition(), newPos));
                break;
            } else {
                break;
            }

            newRow += rowDirection;
            newCol += colDirection;
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
        Bishop other = (Bishop) obj;
        return position.equals(other.position) && color == other.color;
    }
}
