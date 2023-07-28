package assignment4;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Position position, Constants.PieceColor color) {
        super(position, color);
    }

    @Override
    public char getMarker() {
        return 'N';
    }

    @Override
    public ArrayList<Move> getPossibleMoves(Board board) {
        ArrayList<Move> possibleMoves = new ArrayList<>();
        Position currentPos = getPosition();
        int currentRow = currentPos.getRow();
        int currentCol = currentPos.getCol();


        checkAllMoves(board, possibleMoves, currentRow, currentCol, 2, 1);  //Up-right
        checkAllMoves(board, possibleMoves, currentRow, currentCol, 2, -1); //Up-left
        checkAllMoves(board, possibleMoves, currentRow, currentCol, -2, 1); //Down-right
        checkAllMoves(board, possibleMoves, currentRow, currentCol, -2, -1); //Down-left
        checkAllMoves(board, possibleMoves, currentRow, currentCol, 1, 2); //Right-up;
        checkAllMoves(board, possibleMoves, currentRow, currentCol, -1, 2); //Right-down;
        checkAllMoves(board, possibleMoves, currentRow, currentCol, 1, -2); //Left-up;
        checkAllMoves(board, possibleMoves, currentRow, currentCol, -1, -2); //Left-down;

        return possibleMoves;
    }

    public void checkAllMoves(Board board, ArrayList<Move> possibleMoves, int currentRow, int currentCol, int rowDirection, int colDirection) {
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
        Knight other = (Knight) obj;
        return position.equals(other.position) && color == other.color;
    }
}



