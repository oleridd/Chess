package Main;

import Utility.*;
import java.util.ArrayList;
import PieceTypes.*;

public class Tile {

    private final Board board;
    private final Pos pos;     // Each tile has a constant pos
    private Piece curPiece;    // The piece that occupies this tile


    Tile(Board board, Pos pos) {
        this.board = board;
        this.pos = pos;
        this.curPiece = new Empty(board, this); // Empty by default
    }


    /// Tile board relation methods ///

    public ArrayList<Piece> getInReach(Team team) {
        // Checks wheather the tile can be reached by a piece on the given team
        // This is mainly used to handle check and checkmate
        ArrayList<Piece> pieces = new ArrayList<Piece>();

        // Handling rows:
        ArrayList<String> lstRow = new ArrayList<String>() { {
            add("Rook");
            add("Queen");
        } };
        pieces.addAll(searchSequence(board.getRow(getPos().y), lstRow, team));
        pieces.addAll(searchSequence(board.getColumn(getPos().x), lstRow, team));

        // Handling columns:
        ArrayList<String> lstSkew = new ArrayList<String>() { {
            add("Bishop");
            add("Queen");
        } };
        pieces.addAll(searchSequence(board.getLeftSkew(getPos()), lstSkew, team));
        pieces.addAll(searchSequence(board.getRightSkew(getPos()), lstSkew, team));

        // Knights:
        for (Pos pos : Knight.getKnightPoses(getPos()   )) {
            Piece piece = board.getTile(pos).getPiece();
            if (piece.getTeam() == team && piece.getName() == "Knight") {
                pieces.add(piece);
            }
        }

        // Pawns:
        int dir = -team.getDir();
        if ( getPos().x > 0) {
            Piece piece = board.getPiece( new Pos(getPos(), -1, dir) );
            if (piece.getTeam() == team && piece.getName() == "Pawn") {
                pieces.add(piece);
            }
        }
        if ( getPos().x < 7) {
            Piece piece = board.getPiece( new Pos(getPos(), 1, dir) );
            if (piece.getTeam() == team && piece.getName() == "Pawn") {
                pieces.add(piece);
            }
        }

        return pieces;
    }
    

    public int findSelf(ArrayList<Tile> arr) {
        // Finds self in a given array and returns the index. Returns -1 if self was not found.
        for (int i = 0; i < arr.size(); ++i) {
            if (this == arr.get(i)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Piece> searchSequence(ArrayList<Tile> seq, ArrayList<String> pieceNameList, Team team) {
        // Given a sequence of tiles, searches from current tiles position until it finds a piece
        // If found piece is in the team, adds it to the return array
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        int ind = findSelf(seq);
        for (int i = ind-1; i >= 0; --i) {
            Piece piece = seq.get(i).getPiece();
            if (!(piece.isEmpty())) {
                if (piece.getTeam() == team && pieceNameList.contains(piece.getName())) { pieces.add(piece); }
                break;
            }
        }
        for (int i = ind+1; i < seq.size(); ++i) {
            Piece piece = seq.get(i).getPiece();
            if (!(piece.isEmpty())) {
                if (piece.getTeam() == team && pieceNameList.contains(piece.getName())) { pieces.add(piece); }
                break;
            }
        }
        return pieces;
    }


    /// Setters and getters ///
    public Pos getPos() { return pos; }
    public void setPiece(Piece piece) {
        this.curPiece = piece;
        piece.setCurTile(this);
    }
    public Piece getPiece() { return curPiece; }
    public boolean hasPiece() { return !curPiece.isEmpty(); }
    
}
