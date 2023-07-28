package assignment4;

import java.util.ArrayList;

import static assignment4.Constants.*;

public abstract class Piece {

    protected Position position;
    protected Constants.PieceColor color;

    public Piece(Position position, PieceColor color){
        this.position=position;
        this.color=color;
    }

    public abstract char getMarker();
    public abstract ArrayList<Move> getPossibleMoves(Board board);
    public void setPosition(Position position){this.position = position;}
    public PieceColor getColor(){return color;}
    public Position getPosition(){return position;}

    public String toString(){return getMarker()+"("+ position.getRow()+","+ position.getCol()+")"+color.toString().charAt(0);}

}
