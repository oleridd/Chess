package PieceTypes;

import java.util.ArrayList;
import Utility.*;
import Main.*;

public class Empty extends Piece {
// An empty piece, used to fill the board.
    
    public Empty() { super("", "Empty", Game.getNoTeam()); }

    public Empty(Board board, Tile tile) {
        super("", "Empty", Game.getNoTeam(), board, tile);
    }

    public ArrayList<Pos> getMoves() {
        return new ArrayList<Pos>();
    }

    public void MoveTo(Pos coordinates) throws RuntimeException {
        throw new RuntimeException("Piece - Empty constructor: You cannot move an empty piece.");
    }

    public int CanMoveHere(Pos coordinates) throws RuntimeException {
        throw new RuntimeException("Piece - Empty constructor: You cannot move an empty piece.");
    }

    public void kill() {
        throw new RuntimeException("Piece - Empty constructior: Cannot kill an empty piece");
    }

}
