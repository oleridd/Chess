package Main;

import PieceTypes.*;
import java.util.ArrayList;


public class Team {
    
    private final String name; // Name of team, usually Black or White.
    private final int dir;     // Either 1 or -1
    private ArrayList<Piece> pieces; // Living pieces
    private ArrayList<Piece> dead;   // Dead pieces
    private King king;      // The king of each team is regularly accessed
    private boolean check;  // Wheather the king is in check

    public Team(String name, int dir) {
        this.name = name;
        this.dir = dir;
        pieces = new ArrayList<Piece>();
        dead = new ArrayList<Piece>();
    }


    /// Handling of arrays ///
    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public void removeListPiece(Piece piece) { pieces.remove(piece); }
    public void addDeadPiece(Piece piece) { dead.add(piece); }


    /// Team general ///
    public String getName() { return name; }
    public ArrayList<Piece> getPieces() { return pieces; }
    public int getDir() { return dir; }

    /// King and check ///
    public void setKing(King king) { this.king = king; }
    public King getKing() { return king; }
    public boolean getCheck() { return check; }
    public void setCheck(boolean check) { this.check = check; }

}
