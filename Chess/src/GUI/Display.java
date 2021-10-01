package GUI;

import Main.*;
import PieceTypes.Piece;

import java.io.IOException;
import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Display {

    private Stage stage;

    public Display(Stage stage) throws IOException {
        initDisplay(stage);
        this.stage = stage;

    }

    public void initDisplay(Stage stage) throws IOException {
        // Initializes JavaFX display

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);

        Image icon = new Image("GUI/Toby.png");
        stage.getIcons().add(icon);
        
        stage.setTitle("Chess");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void draw() {
        // Iterates through the board and updates screen data based on the game state
        ArrayList<Team> teams = new ArrayList<Team>();
        teams.add(Game.getBlackTeam());
        teams.add(Game.getWhiteTeam());

        for (Team t : teams) {
            for (Piece p : t.getPieces()) {
                p.getImage().setX(p.getPos().getScreenX());
                p.getImage().setY(p.getPos().getScreenY());
            }
        }
    }

    public Stage getStage() { return stage; }

}