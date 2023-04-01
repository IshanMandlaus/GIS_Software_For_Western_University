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
    private double relative_x;
    private double relative_y;
    private JLabel parent;
    private ImageIcon icon;

    long floorID, buildingID, layerID;

    public POI(boolean builtin, String name, long roomNumber, String description, long buildingID, long floorID, long layerID, double relative_x, double relative_y, JLabel parent) {
        super("");
        this.builtin = builtin;
        this.name = name;
        this.roomNumber = roomNumber;
        this.description = description;
        this.buildingID = buildingID;
        this.floorID = floorID;
        this.layerID = layerID;
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
                        "Room " + roomNumber + "\n" + description,
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
    public void setLayerID(long layerID) {
        this.layerID = layerID;
    }

    public Boolean getBuiltin() {
        return builtin;
    }
    public String getName() {
        return name;
    }
    public Long getRoomNumber() {
        return roomNumber;
    }
    public String getDescription() {
        return description;
    }
    public Long getBuildingID() {
        return buildingID;
    }
    public Long getFloorID() {
        return floorID;
    }
    public Long getLayerID() {
        return layerID;
    }
    public Double getRelativeX() {
        return relative_x;
    }
    public Double getRelativeY() {
        return relative_y;
    }
    public JLabel getParent() {
        return parent;
    }
}
