package GUI;

import Main.*;
import PieceTypes.*;
import Utility.*;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;


public class Controller {
    
    /// General objects ///
    @FXML AnchorPane anchor;
    @FXML Rectangle highlighter;
    @FXML Rectangle mover;
    @FXML Group PieceGroup;

    /// Game pieces ///
    // Black team
    @FXML ImageView b_rook_1;
    @FXML ImageView b_knight_1;
    @FXML ImageView b_bishop_1;
    @FXML ImageView b_queen;
    @FXML ImageView b_king;
    @FXML ImageView b_bishop_2;
    @FXML ImageView b_knight_2;
    @FXML ImageView b_rook_2;
    @FXML ImageView b_pawn_1;
    @FXML ImageView b_pawn_2;
    @FXML ImageView b_pawn_3;
    @FXML ImageView b_pawn_4;
    @FXML ImageView b_pawn_5;
    @FXML ImageView b_pawn_6;
    @FXML ImageView b_pawn_7;
    @FXML ImageView b_pawn_8;

    // White team
    @FXML ImageView w_rook_1;
    @FXML ImageView w_knight_1;
    @FXML ImageView w_bishop_1;
    @FXML ImageView w_queen;
    @FXML ImageView w_king;
    @FXML ImageView w_bishop_2;
    @FXML ImageView w_knight_2;
    @FXML ImageView w_rook_2;
    @FXML ImageView w_pawn_1;
    @FXML ImageView w_pawn_2;
    @FXML ImageView w_pawn_3;
    @FXML ImageView w_pawn_4;
    @FXML ImageView w_pawn_5;
    @FXML ImageView w_pawn_6;
    @FXML ImageView w_pawn_7;
    @FXML ImageView w_pawn_8;

    public void initialize() {
        // Sets images of all ImageView objects and assigns this controller to Game
        setStandardImageObjects();
        Game.setController(this);
        System.out.println("Controller initialized");
    }

    private void setStandardImageObjects() {
        // Adds images to ImageViews
        ArrayList<Piece> b_pieces = Game.getBlackTeam().getPieces();
        ArrayList<Piece> w_pieces = Game.getWhiteTeam().getPieces();

        b_pieces.get(0).setImage(b_rook_1);
        b_pieces.get(1).setImage(b_knight_1);
        b_pieces.get(2).setImage(b_bishop_1);
        b_pieces.get(3).setImage(b_queen);
        b_pieces.get(4).setImage(b_king);
        b_pieces.get(5).setImage(b_bishop_2);
        b_pieces.get(6).setImage(b_knight_2);
        b_pieces.get(7).setImage(b_rook_2);
        b_pieces.get(8).setImage(b_pawn_1);
        b_pieces.get(9).setImage(b_pawn_2);
        b_pieces.get(10).setImage(b_pawn_3);
        b_pieces.get(11).setImage(b_pawn_4);
        b_pieces.get(12).setImage(b_pawn_5);
        b_pieces.get(13).setImage(b_pawn_6);
        b_pieces.get(14).setImage(b_pawn_7);
        b_pieces.get(15).setImage(b_pawn_8);

        w_pieces.get(0).setImage(w_rook_1);
        w_pieces.get(1).setImage(w_knight_1);
        w_pieces.get(2).setImage(w_bishop_1);
        w_pieces.get(3).setImage(w_queen);
        w_pieces.get(4).setImage(w_king);
        w_pieces.get(5).setImage(w_bishop_2);
        w_pieces.get(6).setImage(w_knight_2);
        w_pieces.get(7).setImage(w_rook_2);
        w_pieces.get(8).setImage(w_pawn_1);
        w_pieces.get(9).setImage(w_pawn_2);
        w_pieces.get(10).setImage(w_pawn_3);
        w_pieces.get(11).setImage(w_pawn_4);
        w_pieces.get(12).setImage(w_pawn_5);
        w_pieces.get(13).setImage(w_pawn_6);
        w_pieces.get(14).setImage(w_pawn_7);
        w_pieces.get(15).setImage(w_pawn_8);

    }


    /// Events ///

    public void clickedEvent(MouseEvent event) {
        // Event called when the player clicks a tile

        Pos pos = new Pos(event.getScreenX(), event.getScreenY()); // Position of the event
        Piece piece = Game.getBoard().getPiece(pos);
        Team team = Game.getTurn(); // The team whose turn it is


        if (Game.getHasSelected()) { // A piece is selected and can be moved
            Game.getBoard().tryMove(pos);
            moveHighlighterEvent(event);
            try {
                Game.display.draw();
            } catch (Exception e) { e.printStackTrace(); }

        } else { // No piece is selected
            if (piece.getTeam() == team && Game.getActive()) {
                if (!(team.getCheck()) || Game.getBoard().getPiece(pos).getName() == "King") {
                    Game.setSelected(pos);
                } else {
                    System.out.println("The king is in check");
                }
            } else {
                if (Game.getActive()) {
                    System.out.println("It is " + team.getName() + "'s turn.");
                }
            }
        }

    }


    public void moveHighlighterEvent(MouseEvent event) {
        // Event called whenever the highlighter is moved over a new tile

        double X = event.getScreenX();
        double Y = event.getScreenY();
        Pos pos = new Pos(X, Y, true); // This is where the special constructor of pos is used

        mover.setVisible(false);
        if (!Game.getHasSelected()) {
            // Highlighter only moves if the game has no selected piece
            highlighter.setX(pos.getScreenX());
            highlighter.setY(pos.getScreenY());

        } else {
            // The mover is updated if the game has a selected piece
            mover.setVisible(true);
            if (pos.isEqualIn(Game.getSelectedMoves())) { // Can move here
                mover.setStroke(Color.RED);
            } else {                                      // Cannot move here
                mover.setStroke(Color.GRAY);
            }
            mover.setX(pos.getScreenX());
            mover.setY(pos.getScreenY());
        }

    }


    /// Setters and getters ///

    public AnchorPane getAnchor() { return anchor; }
    public Group getPieceGroup() { return PieceGroup; }

}
