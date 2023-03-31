package src.main.java;

import javax.swing.*;
import java.awt.*;

public class POI extends JButton {
    private final boolean builtin;
    private final String name;
    private final String description;
    private int layerID;

    public POI(boolean builtin, String name, String description, Point location) {
        super("POI");
        this.builtin = builtin;
        this.name = name;
        this.description = description;
        this.setBounds(location.x, location.y, 25, 25);
        this.setVisible(true);


    }

    public void setLayerID(int layerID) {
        this.layerID = layerID;
    }


    public static void main(String[] args) {
        System.out.println("JJJJ");
    }



}
