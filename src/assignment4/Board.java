package assignment4;

import java.util.ArrayList;
import java.util.Arrays;

import static assignment4.Constants.*;


public class Board {
    private Piece[][] matrix;
    private ArrayList<Piece> pieces;

    public Piece[][] getUnderlyingMatrix() {
        return this.matrix;
    }

    public Board(){
        this.matrix = new Piece[7][7];
        this.pieces = new ArrayList<Piece>();
    }

    public Board(int size){
        this.matrix = new Piece[size][size];
    }

    public Board(Piece[][] board) {
        int size = board.length;
        matrix = new Piece[size][size];
        pieces = new ArrayList<>();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] != null) {
                    Piece piece = board[row][col];
                    matrix[row][col] = piece;
                    pieces.add(piece);
                }
            }
        }
    }

    public ArrayList<Piece> getPieces(PieceColor color) {
        ArrayList<Piece> filteredPieces = new ArrayList<>();
        for (Piece piece : pieces) {
            if (piece.getColor() == color) {
                filteredPieces.add(piece);
            }
        }
        return filteredPieces;
    }

    public boolean checkValidPosition(Position position){
        int rows = position.getRow();
        int cols = position.getCol();
        int matrixSize = matrix.length;
        return rows >=0 && cols >=0 && rows < matrixSize && cols < matrixSize;
    }

    public Piece getPiece(Position position){
        if (checkValidPosition(position) && matrix[position.getRow()][position.getCol()] != null){
            return matrix[position.getRow()][position.getCol()];
        }
        return null;
    }

    public void addPiece(Piece piece){
        Position position = piece.getPosition();
        if (checkValidPosition(piece.getPosition())){
            matrix[position.getRow()][position.getCol()] = piece;
            pieces.add(piece);
        }
    }

    public void removePiece(Position position){
        if (checkValidPosition(position)){
            Piece piece = matrix[position.getRow()][position.getCol()];
            if (piece != null){
                matrix[position.getRow()][position.getCol()] = null;
                pieces.remove(piece);
            }
        }
    }

    public void updatePosition(Position oldPos, Position newPos) {
        int oldRow = oldPos.getRow();
        int oldCol = oldPos.getCol();
        Piece currentPiece = matrix[oldRow][oldCol];

        if (currentPiece instanceof Tank) {
            Tank tank = (Tank) currentPiece;
            tank.setCooldown(tank.getCooldown() - 1);
        }

        Piece targetPiece = matrix[newPos.getRow()][newPos.getCol()];

        if (targetPiece != null) {
            if (targetPiece.getColor() != currentPiece.getColor()) {
                removePiece(newPos);
                matrix[newPos.getRow()][newPos.getCol()] = null;
            } else {
                return;
            }
        }

        currentPiece.setPosition(newPos);
        matrix[oldRow][oldCol] = null;
        matrix[newPos.getRow()][newPos.getCol()] = currentPiece;

        if (verifyPromotion(currentPiece)) {
            promote(currentPiece);
        }
    }

    public int getPieceCount(PieceColor pieceColor){
        int counter = 0;
        for (Piece piece : pieces) {
            if (piece.color == pieceColor)
                counter++;
        }
        return counter;
    }

    public boolean verifyPromotion(Piece piece){
        int row = piece.getPosition().getRow();
        char pieceColor = getColorMarker(piece.getColor());
        if(!(piece instanceof Pawn))
            return false;
        if(pieceColor == 'W') {
            return row == 0;
        }
        else return row == this.matrix.length - 1;
    }

    public void promote(Piece piece) {
        if (!(piece instanceof Pawn)) {
            throw new IllegalArgumentException("Only pawns can be promoted.");
        }

        Position position = piece.getPosition();
        int row = position.getRow();
        int col = position.getCol();
        PieceColor color = piece.getColor();

        if ((color == PieceColor.White && row == 0) || (color == PieceColor.Black && row == getBoardSize() - 1)) {
            Tank promotedPiece = new Tank(position, color);
            removePiece(position);
            matrix[row][col] = promotedPiece;
            pieces.add(promotedPiece);
        } else {
            throw new IllegalArgumentException("Promotion is not valid for the given pawn.");
        }
    }

    public int getBoardSize(){
        return matrix.length;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Board)) {
            return false;
        }
        Board other = (Board) obj;
        Piece[][] otherMatrix = other.getUnderlyingMatrix();

        if (!Arrays.deepEquals(matrix, otherMatrix)) {
            return false;
        }

        return true;
    }


    public boolean checkValidTankMove(int currentRow, int currentCol, int newRow, int newCol) {
        if (Math.abs(newRow - currentRow) <= 1 && Math.abs(newCol - currentCol) <= 1) {
            if (newRow == currentRow || newCol == currentCol) {
                return true;
            }
        }
        return false;
    }

    public boolean checkValidTankCooldownEat(int currentRow, int currentCol, int newRow, int newCol) {
        if (Math.abs(newRow - currentRow) == 2 && Math.abs(newCol - currentCol) == 2) {
            int eatRow = (currentRow + newRow) / 2;
            int eatCol = (currentCol + newCol) / 2;
            Piece piece = getPiece(new Position(eatRow, eatCol));
            return piece != null;
        }
        return false;
    }

    public boolean checkValidTankEat(int currentRow, int currentCol, int newRow, int newCol) {
        if (Math.abs(newRow - currentRow) == 1 && Math.abs(newCol - currentCol) == 1) {
            int eatRow = (currentRow + newRow) / 2;
            int eatCol = (currentCol + newCol) / 2;
            Piece piece = getPiece(new Position(eatRow, eatCol));
            return piece != null;
        }
        return false;
    }






}

