package PieceTypes;

import java.util.ArrayList;
import java.util.Arrays;
import Utility.*;
import Main.*;

public class Knight extends Piece {

    public Knight(String ID, Team team, Board board, Tile tile) {
        super(ID, "Knight", team, board, tile);
    }

    public ArrayList<Pos> getMoves() {
        Pos p = getPos();
        ArrayList<Pos> possPoses = getKnightPoses(p);
        ArrayList<Pos> poses = new ArrayList<Pos>();
        for (Pos pos : possPoses) {
            if (canMoveHere(pos) != 0) { poses.add(pos); }
        }
        return poses;
    }

    public static ArrayList<Pos> getKnightPoses(Pos p) {
        // Gets the possible moving positions of a knight
        // Static because it is also used by the Tile class
        ArrayList<Pos> poses = new ArrayList<Pos>();

        ArrayList<Integer> x_incr = new ArrayList<Integer>();
        x_incr.addAll(Arrays.asList(-1, 1, 2, 2, 1, -1, -2, -2));
        ArrayList<Integer> y_incr = new ArrayList<Integer>();
        y_incr.addAll(Arrays.asList(-2, -2, -1, 1, 2, 2, 1, -1));

        for (int i = 0; i < 8; ++i) {
            if (p.x+x_incr.get(i) >= 0 && p.x+x_incr.get(i) < 8
             && p.y+y_incr.get(i) >= 0 && p.y+y_incr.get(i) < 8) {
                Pos pos = new Pos(p, x_incr.get(i), y_incr.get(i));
                poses.add(pos);
                
            }
        }

        return poses;
    }

}
