package Utility;

import Main.Game;

public class Test {
    
    public static void test_func_1() {
        System.out.println("getRow():");
        Utility.printTileList(Game.getBoard().getRow(6));
        System.out.println("getColumn():");
        Utility.printTileList(Game.getBoard().getColumn(2));
    }

    public static void test_func_2() {
        try {
            Pos pos = new Pos(5, 5);
            System.out.println("getLeftSkew():");
            Utility.printTileList(Game.getBoard().getLeftSkew(pos));
            System.out.println("getRightSkew():");
            Utility.printTileList(Game.getBoard().getRightSkew(pos));
        } catch(Exception e) { e.printStackTrace(); }
    }

}
