package Main;

import GUI.*;
import PieceTypes.*;
import Utility.*;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;


public class Game extends Application {

    public static Display display; // Main display object
    private static Piece selected; // Main selected piece
    private static ArrayList<Pos> selectedMoves; // Moves of selected piece
    private static Board gameBoard;
    private static Controller controller;

    /// Main teams ///
    private static Team blackTeam;
    private static Team whiteTeam;
    private static Team noTeam;

    /// Game status ///
    private static Team turn;      // The piece whose turn it is
    private static boolean active; // Is the game running


    public static void main(String[] args) throws Exception {

        try {
            initialize_game(); // Game
            launch(args);      // GUI
        } catch (Exception e) { e.printStackTrace();; }
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        display = new Display(stage);
        display.draw();
        System.out.println("Display initialized");
        
    }


    public static void initialize_game() throws Exception {
        // Initializes runtime
        blackTeam = new Team("Black", 1);
        whiteTeam = new Team("White", -1);
        noTeam = new Team("", 0);
        setTurn(getWhiteTeam());
        setIsActive(true);
        gameBoard = new Board();
        selectNone();
    }

    public static void setSelected(Pos pos) {
        selected = gameBoard.getPiece(pos);
        if (getHasSelected()) { selectedMoves = selected.getMoves(); }
        System.out.println("Now selected: " + selected.getName());
    }

    public static void selectNone() {
        selected = new Empty();
    }


    /// Turns and check handling ///
    public static void toggleTurn() {
        if (getActive()) {
            if (turn == getBlackTeam()) { setTurn(getWhiteTeam()); }
            else if (turn == getWhiteTeam()) { setTurn(getBlackTeam()); }
        }
    }
    
    public static void setTurn(Team turnIn) {
        turn = turnIn;
        System.out.println("It is " + turn.getName() + "'s turn");
    }
    public static Team getTurn() { return turn; }

    public static void updateCheck() {
        // Updates wheather any king is in check
        if (!(getWhiteTeam().getKing().getCurTile().getInReach(getBlackTeam()).isEmpty())) {
            whiteTeam.setCheck(true);
            System.out.println("White is in check");
        } else {
            whiteTeam.setCheck(false);
        }
        if (!(getBlackTeam().getKing().getCurTile().getInReach(getWhiteTeam()).isEmpty())) {
            blackTeam.setCheck(true);
            System.out.println("Black is in check");
        } else {
            blackTeam.setCheck(false);
        }
    }

    public static void updateCheckMate() {
        // Updates wheather any team is in checkmate
        if (whiteTeam.getCheck() && whiteTeam.getKing().getMoves().isEmpty()) {
            setActive(false);
            System.out.println("Check mate. Black has won");
        }
        if (blackTeam.getCheck() && blackTeam.getKing().getMoves().isEmpty()) {
            setActive(false);
            System.out.println("Check mate. White has won");
        }
    }
    

    /// Selected setters and getters ///
    public static Piece getSelected() { return selected; }
    public static boolean getHasSelected() { return !selected.isEmpty(); }
    public static ArrayList<Pos> getSelectedMoves() { return selectedMoves; }

    /// Controller setters and getters ///
    public static void setController(Controller controllerIn) { controller = controllerIn; }
    public static Controller getController() { return controller; }

    /// Board and teams ///
    public static Board getBoard() { return gameBoard; }
    public static Team getBlackTeam() { return blackTeam; }
    public static Team getWhiteTeam() { return whiteTeam; }
    public static Team getNoTeam() { return noTeam; }

    /// Game status ///
    public static void setIsActive(boolean isActive) { active = isActive; }
    public static boolean isActive() { return active; }
    public static void setActive(boolean activeIn) { active = activeIn; }
    public static boolean getActive() { return active; }

}
