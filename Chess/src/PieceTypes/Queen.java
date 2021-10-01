package PieceTypes;

import java.util.ArrayList;
import Utility.*;
import Main.*;

public class Queen extends Piece {

    public Queen(String ID, Team team, Board board, Tile tile) {
        super(ID, "Queen", team, board, tile);
    }

    public ArrayList<Pos> getMoves() {
        ArrayList<Pos> poses = findSelfIsolated(getBoard().getRow(getPos().y));
        poses.addAll( findSelfIsolated(getBoard().getColumn(getPos().x)) );
        poses.addAll( findSelfIsolated(getBoard().getLeftSkew(getPos())) );
        poses.addAll( findSelfIsolated(getBoard().getRightSkew(getPos())) );

        return poses;
    }

}
