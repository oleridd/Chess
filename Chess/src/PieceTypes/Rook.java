package PieceTypes;

import java.util.ArrayList;
import Utility.*;
import Main.*;

public class Rook extends Piece {

    public Rook(String ID, Team team, Board board, Tile tile) {
        super(ID, "Rook", team, board, tile);
    }

    public ArrayList<Pos> getMoves() {
        ArrayList<Pos> poses = findSelfIsolated(getBoard().getRow(getPos().y));
        poses.addAll( findSelfIsolated(getBoard().getColumn(getPos().x)) );
        return poses;
    }




}
