package PieceTypes;

import java.util.ArrayList;
import Utility.*;
import Main.*;

public class Pawn extends Piece {

    public Pawn(String ID, Team team, Board board, Tile tile) {
        super(ID, "Pawn", team, board, tile);
    }

    public ArrayList<Pos> getMoves() {
        // Pawns can never reach the end of the board, then they get converted to queens
        ArrayList<Pos> poses = new ArrayList<Pos>();
        Pos pos = getPos();

        // Exactly three possible moves
        Pos pos1 = new Pos(pos, 0, getTeam().getDir()); // One straight
        if (canMoveHere(pos1) == 1) { poses.add(pos1); }

        if (pos.x != 7) {
            Pos pos2 = new Pos(pos, 1, getTeam().getDir()); // Skewed right
            if (canMoveHere(pos2) == 2) { poses.add(pos2); }
        }

        if (pos.x != 0) {
            Pos pos3 = new Pos(pos, -1, getTeam().getDir()); // Skewed left
            if (canMoveHere(pos3) == 2) { poses.add(pos3); }
        }

        if (!(getHasMoved())) { poses.add(new Pos(pos, 0, 2*getTeam().getDir())); }

        return poses;
    }

}
