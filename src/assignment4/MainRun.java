package assignment4;

import static assignment4.Constants.*;

public class MainRun {

    public static void boardMain() {
        Board b = new Board();
        System.out.println("Board size: " + b.getBoardSize());
        b = new Board(5);
        System.out.println("Board size: " + b.getBoardSize());
        Piece[][] matrix = new Piece[3][3];
        b = new Board(matrix);
        System.out.println("Board size: " + b.getBoardSize());
        Position p1 = new Position(0, 0);
        Position p2 = new Position(1, 0);
        b.addPiece(new Pawn(p1, BLACK));
        System.out.println("Piece at (0,0): " + b.getPiece(p1));
        System.out.println("Piece at (1,0): " + b.getPiece(p2));
        b.updatePosition(p1, p2);
        System.out.println("Piece at (0,0): " + b.getPiece(p1));
        System.out.println("Piece at (1,0): " + b.getPiece(p2));
        b.addPiece(new Pawn(p1, WHITE));
        System.out.println("White pieces: " + b.getPieces(WHITE));
        System.out.println("Black pieces: " + b.getPieces(BLACK));
        b.removePiece(p1);
        b.removePiece(p2);
        System.out.println("# of black pieces: " + b.getPieceCount(BLACK));
        Pawn promotable = new Pawn(new Position(0, 0), WHITE);
        b.addPiece(promotable);
        System.out.println(b.verifyPromotion(promotable));
        b.promote(promotable);
        System.out.println(b.equals(new Board()));
    }

    public static void moveMain() {
        Position p1 = new Position(0, 0);
        Position p2 = new Position(1, 1);
        Position p3 = new Position(1, 1);
        Board b = new Board();
        b.addPiece(new Pawn(p1, BLACK));
        Move m = new Move(p1, p2);
        System.out.println("Move start position: " + m.getStartPos());
        System.out.println("Move end position: " + m.getEndPos());
        m.updateBoard(b);
        System.out.println("Piece at (0,0): " + b.getPiece(p1));
        System.out.println("Piece at (1,1): " + b.getPiece(p2));
        System.out.println(m.equals(new Move(p1, p2)));
        System.out.println(m.equals(new Move(p1, p3)));
    }

    public static void pawnMain() {
        // While each piece type needs to be implemented accordingly, we will test the general methods on pawn.
        // Be encouraged to add your own tests!
        Piece piece = new Pawn(new Position(1, 1), WHITE);
        System.out.println("Pawn marker: " + piece.getMarker());
        System.out.println("Pawn color: " + piece.getColor());
        System.out.println("Pawn position: " + piece.getPosition());
        piece.setPosition(new Position(2, 2));
        System.out.println("Pawn new position: " + piece.getPosition());
        System.out.println("Pawn string form: " + piece);
        Board b = new Board();
        b.addPiece(piece);
        System.out.println("# of possible moves: " + piece.getPossibleMoves(b).size());
    }

    public static void tankMain() {
        Tank t = new Tank(new Position(1, 1), BLACK);
        System.out.println("Tank initial cooldown: " + t.getCooldown());
        t.setCooldown(1);
        System.out.println("Tank cooldown after change: " + t.getCooldown());
    }

    public static void main(String[] args) {
        System.out.println("---Board Testing---");
        boardMain();
        System.out.println("---Move Testing---");
        moveMain();
        System.out.println("---Piece (Pawn) Testing---");
        pawnMain();
        System.out.println("---Tank Testing---");
        tankMain();
    }
}