package assignment4;

import java.util.ArrayList;

public class Tank extends Piece {
    private int cooldown;

    public Tank(Position position, Constants.PieceColor color) {
        super(position, color);
        this.cooldown = 0;
    }

    @Override
    public char getMarker() {
        return 'T';
    }

//    @Override
//    public ArrayList<Move> getPossibleMoves(Board board) {
//        ArrayList<Move> possibleMoves = new ArrayList<>();
//        Position currentPos = getPosition();
//        int currentRow = currentPos.getRow();
//        int currentCol = currentPos.getCol();
//
//        for (int rowDirection = -1; rowDirection <= 1; rowDirection++) {
//            for (int colDirection = -1; colDirection <= 1; colDirection++) {
//                if (rowDirection == 0 && colDirection == 0) {
//                    continue;
//                }
//                checkTankMove(board, possibleMoves, currentRow, currentCol, rowDirection, colDirection);
//            }
//        }
//
//        return possibleMoves;
//    }
//
//    public void checkTankMove(Board board, ArrayList<Move> possibleMoves, int currentRow, int currentCol,
//                              int rowDirection, int colDirection) {
//        int newRow = currentRow + rowDirection;
//        int newCol = currentCol + colDirection;
//
//        Position newPos = new Position(newRow, newCol);
//        if (board.checkValidPosition(newPos)) {
//            Piece piece = board.getPiece(newPos);
//            if (piece == null || piece.getColor() != getColor()) {
//                if (board.checkValidTankMove(currentRow, currentCol, newRow, newCol)) {
//                    if (piece == null) {
//                        possibleMoves.add(new Move(getPosition(), newPos));
//                    } else {
//                        int cooldown = getCooldown();
//                        if (cooldown == 0 && board.checkValidTankEat(currentRow, currentCol, newRow, newCol)) {
//                            possibleMoves.add(new TankEatMove(getPosition(), newPos));
//                        }
//                    }
//                }
//            }
//        }
//    }

//    @Override
//    public ArrayList<Move> getPossibleMoves(Board board) {
//        ArrayList<Move> possibleMoves = new ArrayList<>();
//        Piece[][] matrix = board.getUnderlyingMatrix();
//        int currRow = this.getPosition().getRow();
//        int currCol = this.getPosition().getCol();
//
//        int[] rowDirections = {-1, -1, -1, 0, 0, 1, 1, 1};
//        int[] colDirections = {-1, 0, 1, -1, 1, -1, 0, 1};
//
//        for (int i = 0; i < rowDirections.length; i++) {
//            int newRow = currRow + rowDirections[i];
//            int newCol = currCol + colDirections[i];
//            Position newPos = new Position(newRow,newCol);
//
//            if (!board.checkValidPosition(newPos))
//                continue;
//
//            Piece piece = matrix[newRow][newCol];
//
//            if (piece == null) {
//                int farRow = newRow + rowDirections[i];
//                int farCol = newCol + colDirections[i];
//                Position farPos = new Position(farRow,farCol);
//
//                if (!board.checkValidPosition(farPos))
//                    continue;
//
//                Piece farPiece = matrix[farRow][farCol];
//
//                if (farPiece != null && getColorMarker(getColor()) != getColorMarker(farPiece.getColor())) {
//                    possibleMoves.add(new TankEatMove(this.getPosition(), new Position(farRow, farCol)));
//                }
//            } else if (getCooldown() == 0 && getColorMarker(this.getColor()) != getColorMarker(piece.getColor())) {
//                possibleMoves.add(new TankEatMove(this.getPosition(), new Position(newRow, newCol)));
//            }
//        }
//
//        // Additional check for movement close to borders
//        if (currRow == 0 || currRow == board.getBoardSize() - 1 || currCol == 0 || currCol == board.getBoardSize() - 1) {
//            for (int i = 0; i < rowDirections.length; i++) {
//                int newRow = currRow + rowDirections[i];
//                int newCol = currCol + colDirections[i];
//                Position newPos = new Position(newRow,newCol);
//
//                if (board.checkValidPosition(newPos) && matrix[newRow][newCol] == null) {
//                    possibleMoves.add(new Move(this.getPosition(), new Position(newRow, newCol)));
//                }
//            }
//        }
//
//        return possibleMoves;
//    }


    @Override
    public ArrayList<Move> getPossibleMoves(Board board) {
        int col = getPosition().getCol();
        int row = getPosition().getRow();
        ArrayList<Move> possibleMoves = new ArrayList<>();

        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (isValidPosition(board, newRow, newCol)) {
                Position newPos = new Position(newRow, newCol);
                Piece piece = board.getPiece(newPos);
                if (piece == null) {
                    possibleMoves.add(new Move(getPosition(), newPos));
                }
            }
        }

        if (cooldown == 0) {
            int[][] sDirections = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
            for (int[] direction : sDirections) {
                for (int i = 1; i <= 2; i++) {
                    int newRow = row + direction[0] * i;
                    int newCol = col + direction[1] * i;
                    if (isValidPosition(board, newRow, newCol)) {
                        Position newPos = new Position(newRow, newCol);
                        Piece piece = board.getPiece(newPos);
                        if (piece != null && piece.getColor() != this.color) {
                            possibleMoves.add(new TankEatMove(getPosition(), newPos));
                            break;
                        }
                    }
                }
            }
        }

        return possibleMoves;
    }

    private boolean isValidPosition(Board board, int row, int col) {
        return row >= 0 && row < board.getBoardSize() && col >= 0 && col < board.getBoardSize();
    }



    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tank other = (Tank) obj;
        return position.equals(other.position) && color == other.color && cooldown == other.cooldown;
    }

}

//
