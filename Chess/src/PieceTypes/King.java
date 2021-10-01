package PieceTypes;

import java.util.ArrayList;
import Utility.*;
import Main.*;

public class King extends Piece {

    public King(String ID, Team team, Board board, Tile tile) {
        super(ID, "King", team, board, tile);
        team.setKing(this);
    }

    public ArrayList<Pos> getMoves() {
        ArrayList<Pos> poses = new ArrayList<Pos>();

        for (Tile t : getBoard().getSurrounding(getPos())) {
            if (canMoveHere(t.getPos()) != 0) {
                t.getPos().print();
                if (t.getInReach(getOppositeTeam()).isEmpty()) {
                    poses.add(t.getPos());
                }
            }
        }

        return poses;
    }

}
