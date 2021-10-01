package Main;

import java.util.ArrayList;
import PieceTypes.*;
import Utility.*;

public class Board {
    
    private final int n;     // Amount of tiles in one direction
    private Tile[][] board;  // Contains one piece in all positions
    private Tile deadTile;   // This is where all the heroic pieces go when they die


    /// Setup ///

    public Board() throws Exception {
        n = 8; // Standard chess board
        this.board = new Tile[n][n];
        this.deadTile = new Tile(this, new Pos(0, 0));


        // Setting coordinates for each piece:
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                board[i][j] = new Tile(this, new Pos(i, j));
            }
        }

        StandardPieceSetup();

    }


    public void StandardPieceSetup() {
        // Setting piece objects to the initial positions
        board[0][0].setPiece(new Rook("b_rook_1", Game.getBlackTeam(), this, board[0][0])); 
        board[1][0].setPiece(new Knight("b_knight_1", Game.getBlackTeam(), this, board[1][0])); 
        board[2][0].setPiece(new Bishop("b_bishop_1", Game.getBlackTeam(), this, board[2][0])); 
        board[3][0].setPiece(new Queen("b_queen", Game.getBlackTeam(), this, board[3][0])); 
        board[4][0].setPiece(new King("b_king", Game.getBlackTeam(), this, board[4][0])); 
        board[5][0].setPiece(new Bishop("b_bishop_2", Game.getBlackTeam(), this, board[5][0])); 
        board[6][0].setPiece(new Knight("b_knight_2", Game.getBlackTeam(), this, board[6][0])); 
        board[7][0].setPiece(new Rook("b_rook_2", Game.getBlackTeam(), this, board[7][0])); 
        for (int i = 0; i < 8; ++i) {
            board[i][1].setPiece(new Pawn("b_pawn_"+(i+1), Game.getBlackTeam(), this, board[i][1])); 
        }

        board[0][7].setPiece(new Rook("w_rook_1", Game.getWhiteTeam(), this, board[7][7])); 
        board[1][7].setPiece(new Knight("w_knight_1", Game.getWhiteTeam(), this, board[1][7])); 
        board[2][7].setPiece(new Bishop("w_bishop_1", Game.getWhiteTeam(), this, board[2][7])); 
        board[3][7].setPiece(new Queen("w_queen", Game.getWhiteTeam(), this, board[3][7])); 
        board[4][7].setPiece(new King("w_king", Game.getWhiteTeam(), this, board[4][7])); 
        board[5][7].setPiece(new Bishop("w_bishop_2", Game.getWhiteTeam(), this, board[5][7])); 
        board[6][7].setPiece(new Knight("w_knight_2", Game.getWhiteTeam(), this, board[6][7])); 
        board[7][7].setPiece(new Rook("w_rook_2", Game.getWhiteTeam(), this, board[7][7])); 
        for (int i = 0; i < 8; ++i) {
            board[i][6].setPiece(new Pawn("w_pawn_"+(i+1), Game.getWhiteTeam(), this, board[i][6])); 
        }
        
    }


    /// Moving ///

    public void movePiece(Piece piece, Pos pos) {
    // Moving piece to pos. This will only be called for the possible moving locations of piece

        Pos pos0 = piece.getPos();
        if (pos0.identicalTo(pos)) {
            System.out.println("They are identical");
            return;
        }
        Piece otherPiece = this.getTile(pos).getPiece();

        // Friendly piece in pos:
        if (otherPiece.getTeam() == piece.getTeam()) {
            System.out.println("You cannot move here");
        }
        else { // The piece can move here
            setNone(pos0);
            setPiece(pos, piece); // Setting piece
            // The piece in pos is an enemy
            if (otherPiece.getTeam() == piece.getOppositeTeam()) {
                otherPiece.kill();
            }
        }
    }


    public void tryMove(Pos pos) {
        // If possible: moves selected piece to pos and updates selection and check
        if (!(Game.getHasSelected())) { throw new RuntimeException("Board - tryMove(): No piece is selected"); }
        Piece selected = Game.getSelected();
        if (pos.isEqualIn(Game.getSelectedMoves())) { // Moving piece
            Game.getBoard().movePiece(selected, pos);
            if (selected instanceof Pawn && selected.getHasMoved() == false) { selected.setHasMoved(true); }
            Game.selectNone(); // Resetting selected piece
            Game.updateCheck();
            Game.updateCheckMate();
            Game.toggleTurn();
        } else if (pos.identicalTo(selected.getPos())) { // Deselecting piece
            Game.selectNone();
        }
    }


    public ArrayList<Tile> getRow(int ind) {
        // Returns the row with the given index
        if (ind < 0 || ind > 7) {throw new IndexOutOfBoundsException("getRow(): index is out of bounds"); }
        ArrayList<Tile> row = new ArrayList<Tile>();
        for (int i = 0; i < 8; ++i) {
            row.add(board[i][ind]);
        }
        return row;
    }

    public ArrayList<Tile> getColumn(int ind) {
        // Returns the column with the given index
        if (ind < 0 || ind > 7) {throw new IndexOutOfBoundsException("getColumn(): index is out of bounds"); }
        ArrayList<Tile> column = new ArrayList<Tile>();
        for (int i = 0; i < 8; ++i) {
            column.add(board[ind][i]);
        }
        return column;
    }

    public ArrayList<Tile> getLeftSkew(Pos root) {
        // Returns the tiles that skew to the left (as seen upwards)
        ArrayList<Tile> skew = new ArrayList<Tile>();
        for (int i = Math.min(root.x, root.y); i >= 0; --i) { // Includes the root node
            skew.add(board[root.x-i][root.y-i]);
        }
        for (int i = 1; i <= Math.min(7-root.x, 7-root.y); ++i) {
            skew.add(board[root.x+i][root.y+i]);
        }
        return skew;
    }

    public ArrayList<Tile> getRightSkew(Pos root) {
        // Returns the tiles that skew to the right (as seen upwards)
        ArrayList<Tile> skew = new ArrayList<Tile>();
        for (int i = Math.min(root.x, 7-root.y); i >= 0 ; --i) { // Includes the root node
            skew.add(board[root.x-i][root.y+i]);
        }
        for (int i = 1; i <= Math.min(7-root.x, root.y); ++i) {
            skew.add(board[root.x+i][root.y-i]);
        }
        return skew;
    }


    public ArrayList<Tile> getSurrounding(Pos root) {
        // Returns the tiles that surround the given pos
        ArrayList<Tile> surrounding = new ArrayList<Tile>();
        for (int i = root.x-1; i <= root.x+1; ++i) {
            for (int j = root.y-1; j <= root.y+1; ++j) {
                if (i >= 0 && i < 8 && j >- 0 && j < 8) {
                    Pos pos = new Pos(i, j);
                    if (!(pos.equals(root))) {
                        surrounding.add(getTile(pos));
                    }
                }
            }
        }
        return surrounding;
    }


    /// Setters and getters ///
    public Tile getTile(Pos pos) {
        return board[pos.x][pos.y];
    }

    public Tile getDeadTile() { return deadTile; }

    public void setPiece(Pos pos, Piece piece) {
        board[pos.x][pos.y].setPiece(piece);
    }

    public void setNone(Pos pos) {
        board[pos.x][pos.y].setPiece(new Empty(this, board[pos.x][pos.y]));
    }
    
    public Piece getPiece(Pos pos) { // Returns the piece in the given position
        return board[pos.x][pos.y].getPiece();
    }

}
