package src.main.java;

import javax.swing.*;
import java.awt.*;

public class POI extends JButton {
    private final boolean builtin;
    private final String name;
    private final String description;
    private int layerID;
    private double relative_x;
    private double relative_y;
    private JLabel parent;

    public POI(boolean builtin, String name, String description, double relative_x, double relative_y, JLabel parent) {
        super("POI");
        this.builtin = builtin;
        this.name = name;
        this.description = description;
        this.relative_x = relative_x;
        this.relative_y = relative_y;
        this.parent = parent;
        this.setBounds((int) (relative_x * parent.getParent().getSize().getWidth()), (int) (relative_y * parent.getParent().getSize().getHeight()), 24, 24);
        parent.add(this);
        this.setVisible(true);
    }

    public void updatePosition(){
        this.setBounds((int) (relative_x * parent.getParent().getSize().getWidth()), (int) (relative_y * parent.getParent().getSize().getHeight()), 24, 24);
    }

    public void setLayerID(int layerID) {
        this.layerID = layerID;
    }

}
