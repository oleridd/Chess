package Utility;

import Main.Game;
import java.util.ArrayList;

public class Pos {

    public int x;
    public int y;

    /// Various constructors ///

    public Pos(int x, int y) { setPos(x, y); }

    public Pos(double X, double Y) { // Input here is coordinates on screen
        int x = (int)((X - Game.display.getStage().getX()-7)/100);
        int y = (int)((Y - Game.display.getStage().getY()-30)/100);
        setPos(x, y);
    }

    public Pos(double X, double Y, boolean roundDown) {
        // Used for highlighting. When the mouse is moved,
        // the position must be slightly rounded down.
        X = (int)(X - Game.display.getStage().getX()-7);
        Y = (int)(Y - Game.display.getStage().getY()-30);
        if (X%100 == 0.) X -= 50;
        if (Y%100 == 0.) Y -= 50;
        int x = (int)(X/100);
        int y = (int)(Y/100);
        setPos(x, y);
    }

    public Pos(Pos pos, int x_incr, int y_incr) {
        // Takes in existing position and increments it
        setPos(
            pos.x + x_incr,
            pos.y + y_incr
            );
    }


    /// Other ///

    public boolean identicalTo(Pos otherPos) {
        // Checks wheather otherPos has the same coordinates as this pos
        return ((x == otherPos.x) && (y == otherPos.y));
    }

    public boolean isEqualIn(ArrayList<Pos> arr) {
        for (Pos p : arr) {
            if (identicalTo(p)) { return true; }
        }
        return false;
    }

    public void print() {
        System.out.print(x + ",  ");
        System.out.println(y);
    }


    /// Setters and getters ///

    private void setPos(int x, int y) {
        // Used by constructors
        if (x > 7 || y > 7) {
            throw new IndexOutOfBoundsException(String.format("Pos constructor: (%d, %d) is out of bounds", x, y));
        }
        this.x = x;
        this.y = y;
    }

    // Converting back to screen coordinates:
    public double getScreenX() { return (double) ((x * 100)); }
    public double getScreenY() { return (double) ((y * 100)); }

}