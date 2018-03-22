package cn.waynechu;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Frame frame = new AlgoFrame("myFrame", 500, 500);
        });
    }
}
