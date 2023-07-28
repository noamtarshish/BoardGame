package assignment4;

public class TankEatMove extends Move {
    public TankEatMove(Position startPos, Position endPos) {
        super(startPos, endPos);
    }

//    @Override
//    public void updateBoard(Board board){
//        Piece[][] matrix = board.getUnderlyingMatrix();
//        Tank currPiece = (Tank)matrix[getStartPos().getRow()][getStartPos().getCol()];
//        currPiece.setCooldown(2);
//        board.removePiece(endPos);
//        matrix[endPos.getRow()][endPos.getCol()] = null;
//    }

    @Override
    public void updateBoard(Board board) {
        Piece piece = board.getPiece(startPos);
        Tank newTankCheck = (Tank) piece;
        newTankCheck.setCooldown(2);
        board.removePiece(endPos);
    }
}
