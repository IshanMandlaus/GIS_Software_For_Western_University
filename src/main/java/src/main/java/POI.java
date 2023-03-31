package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class POI extends JButton {
    private boolean builtin;
    private String name;
    private long roomNumber;
    private String description;
    private long layerID;
    private long buildingID;
    private long floorID;
    private double relative_x;
    private double relative_y;
    private JLabel parent;
    private ImageIcon icon;

    public POI(boolean builtin, String name, long rmNum, String description, long buildingID, long floorID, long layerID, double relative_x, double relative_y, JLabel parent) {
        super("");
        this.builtin = builtin;
        this.name = name;
        this.roomNumber = rmNum;
        this.description = description;
        this.layerID = layerID;
        this.buildingID = buildingID;
        this.floorID = floorID;
        this.relative_x = relative_x;
        this.relative_y = relative_y;
        this.parent = parent;
        this.setBounds((int) (relative_x * parent.getParent().getSize().getWidth()), (int) (relative_y * parent.getParent().getSize().getHeight()), 24, 24);
        parent.add(this);
        icon = new ImageIcon(new ImageIcon("Icons/user.png").getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Object[] possibilities = {"Add To Favourites", "Delete"};
                String s = (String)JOptionPane.showInputDialog(
                        parent,
                        "Room " + rmNum + "\n" + description,
                        name,
                        JOptionPane.PLAIN_MESSAGE,
                        icon,
                        possibilities,
                        "Add To Favourites");
            }
        });
    }

    public void updatePosition(){
        this.setBounds((int) (relative_x * parent.getParent().getSize().getWidth()), (int) (relative_y * parent.getParent().getSize().getHeight()), 24, 24);
    }

    public void setLayerID(int layerID) {
        this.layerID = layerID;
    }

    public Object getBuiltin() {
        return builtin;
    }

    public Object getDescription() {
        return description;
    }

    public Object getBuildingID() {
        return buildingID;
    }

    public Object getFloorID() {
        return floorID;
    }

    public Object getLayerID() {
        return layerID;
    }

    public Object getRelativeX() {
        return relative_x;
    }

    public Object getRelativeY() {
        return relative_y;
    }
    public Object getRoom() {
        return roomNumber;
    }
}
