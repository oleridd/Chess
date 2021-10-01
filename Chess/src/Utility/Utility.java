package Utility;

import Main.Game;
import Main.Tile;
import PieceTypes.Piece;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Utility {

    public static ArrayList<Rectangle> selectedNodes;

    public static void hideSelected() {
        // Hides the rectangles in selectedNodes
        for (Rectangle r : selectedNodes) {
            r.setVisible(false);
        }
    }


    /// Printing of ArrayLists ///

    public static void printPosList(ArrayList<Pos> arr) {
        // Prints out all the poses in the given ArrayList of poses
        for (Pos i : arr) {
            i.print();
        }
    }

    public static void printPieceList(ArrayList<Piece> arr) {
        // Prints out all the poses in the given ArrayList of pieces
        for (Piece i : arr) {
            System.out.print("In reach: ");
            i.getPos().print();
        }
    }

    public static void printTileList(ArrayList<Tile> arr) {
        // Prints out all the poses in the given ArrayList of tiles
        for (Tile i : arr) {
            i.getPos().print();
        }
    }

    public static void showSelected(ArrayList<Pos> selected) {
        selectedNodes = new ArrayList<Rectangle>();
        for (Pos t : selected) {
            Rectangle rect = new Rectangle();
            rect.setX(t.getScreenX());
            rect.setY(t.getScreenY());
            rect.setScaleX(100);
            rect.setScaleY(100);
            rect.setFill(Color.RED);
            rect.setOpacity(0.3);
            Game.getController().getAnchor().getChildren().add(rect);

            selectedNodes.add(rect);
        }
    }

}
