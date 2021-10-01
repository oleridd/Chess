package PieceTypes;

import Utility.*;
import Main.*;
import java.util.ArrayList;
import java.util.Objects;
import javafx.scene.image.ImageView;


/* This class will be the abstract of a piece including methods sa. getMoves() */
public abstract class Piece {

    private final String ID;
    private final String name;
    private final Team team;
    private Board board; // Should be final, but this makes initialization easier.
    private Tile curTile;
    private ImageView image;
    private boolean hasMoved;


    public Piece(String ID, String name, Team team) {
        this.ID = ID;
        this.name = name;
        this.team = team;
        team.addPiece(this); // Both team class and piece class must have access to each other
    }

    public Piece(String ID, String name, Team team, Board board, Tile tile) {
        this(ID, name, team);
        this.board = board;
        this.curTile = tile;
    }
    
    public abstract ArrayList<Pos> getMoves();    // Returns ArrayList with int arrays (2 entries) including coordinates on the board

    public void MoveTo(Pos pos) {
        // Moves the piece to the given position via the board move function
        this.getBoard().movePiece(this, pos);
    }

    public int canMoveHere(Pos pos) {
        // Checks if a piece can move to pos
        Team firstTeam = getTeam();
        Team secondTeam = board.getPiece(pos).getTeam();
        if (firstTeam == secondTeam) {
            return 0;     // Piece in pos is friendly, cannot move
        } else {
            if (secondTeam == Game.getNoTeam()) {
                return 1; //Piece in pos is empty
            } else {
                return 2; // Piece in pos is an enemy
            }
        }
    }

    public ArrayList<Pos> findSelfIsolated(ArrayList<Tile> arr) {
        // Finds the part of a list of tiles in which there are not friendly pieces in the way
        ArrayList<Pos> poses = new ArrayList<Pos>();
        Team thisTeam = getTeam();
        int arrSize = arr.size();

        int ind = getCurTile().findSelf(arr); // Getting the start index for search
        if (ind == -1) { throw new RuntimeException("Piece - findSelfIsolated(): self was not found in list"); }
        
        for (int i = ind+1; i < arrSize; ++i) {
            if ( !(arr.get(i).hasPiece()) ) {
                poses.add(arr.get(i).getPos());
            } else {
                if (!(arr.get(i).getPiece().getTeam().equals(thisTeam))) {
                    poses.add(arr.get(i).getPos()); }
                break;
            }
        }

        for (int i = ind-1; i >= 0; --i) {
            if ( !(arr.get(i).hasPiece()) ) {
                poses.add(arr.get(i).getPos());
            } else {
                if (!(arr.get(i).getPiece().getTeam().equals(thisTeam))) {
                    poses.add(arr.get(i).getPos()); }
                break;
            }
        }

        return poses;

    }


    public void kill() {
        // Removes the piece from the game
        getTeam().removeListPiece(this); // Removes piece from list of live pieces
        getTeam().addDeadPiece(this);    // Adds piece to list of dead pieces
        setCurTile(Game.getBoard().getDeadTile());
        setVisible(false);
        System.out.println("Killed " + ID);
    }


    /// Identity ///
    public String getID() { return ID; }
    public String getName() { return name; }
    public Team getTeam() { return team; }
    public Team getOppositeTeam() {
        if (name.equals("Empty")) { throw new RuntimeException("Cannot get opposite team of empty piece"); }
        if (team.equals(Game.getWhiteTeam())) { return Game.getBlackTeam(); }
        if (team.equals(Game.getBlackTeam())) { return Game.getWhiteTeam(); }
        throw new RuntimeException("Could not return team.");       
    }
    public void setHasMoved(boolean hasMoved) { this.hasMoved = hasMoved; }
    public boolean getHasMoved() { return hasMoved; }

    /// Locations ///
    public Board getBoard() { return board; }
    public void setCurTile(Tile curTile) { this.curTile = curTile; } // This must only be called in tile class!
    public Tile getCurTile() { return curTile; }
    public Pos getPos() { return curTile.getPos(); }
    public boolean isEmpty() {return name.equals("Empty"); }

    /// GUI /// 
    public void setImage(ImageView image) { this.image = image; }
    public ImageView getImage() {
        if (Objects.isNull(image)) {
            throw new RuntimeException(String.format("getImage: Piece with ID %s has no image", getID()));
        }
        return image;
    }
    public void setVisible(boolean vis) { image.setVisible(vis); }

}