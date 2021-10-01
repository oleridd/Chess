package PieceTypes;

import java.util.ArrayList;
import Utility.*;
import Main.*;

public class Bishop extends Piece {

    public Bishop(String ID, Team team, Board board, Tile tile) {
        super(ID, "Bishop", team, board, tile);
    }

    public ArrayList<Pos> getMoves() {
        // Gets skewed axes and limits it to the parts where this is isolated
        ArrayList<Pos> poses = findSelfIsolated(getBoard().getLeftSkew(getPos()));
        poses.addAll( findSelfIsolated(getBoard().getRightSkew(getPos())) );

        return poses;
    }

}
