import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class POI extends JButton{
    private boolean builtin;
    private String name;
    private long roomNumber;
    private String description;
    private double relative_x;
    private double relative_y;
    private JLabel parent;
    private Layer containingLayer;
    private ImageIcon icon;
    private String creatingUsr;
    //private string array of users
    private ArrayList<String> favUsers;
    private static final int USER_CREATED = 0;
    private static final int CLASSROOM = 1;
    private static final int WASHROOM = 2;
    private static final int RESTAURANT = 3;
    private static final int EXIT = 4;
    private static final int COMPUTER_LAB = 5;
    private static final int COLLAB = 6;


    long floorID, buildingID, layerID;

    public POI(boolean builtin, String name, long roomNumber, String description, long buildingID, long floorID,
               long layerID, double relative_x, double relative_y, JLabel parent, String creatingUsr, ArrayList<String> favUsers, Layer layer) {
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
        this.creatingUsr = creatingUsr;
        this.favUsers= favUsers;
        this.containingLayer = layer;

        this.setBounds((int) (relative_x * parent.getParent().getSize().getWidth()), (int) (relative_y * parent.getParent().getSize().getHeight()), 24, 24);
        parent.add(this);
        String imagePath = switch ((int) layerID) {
            case CLASSROOM -> "Icons/classroom.png";
            case WASHROOM -> "Icons/washroom.png";
            case RESTAURANT -> "Icons/restaurant.png";
            case EXIT -> "Icons/exit.png";
            case COMPUTER_LAB -> "Icons/computer-lab.png";
            case COLLAB -> "Icons/collab.png";
            default -> "Icons/user.png";
        };

        icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        this.setIcon(icon);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                Object[] possibilities;
                if (!builtin || layer.getCurrUser().getUsername().equals("saad")) {
                     possibilities = new Object[]{"None", "Add To Favourites", "Remove From Favourites", "Delete"};
                }
                else{
                    possibilities = new Object[]{"None", "Add To Favourites", "Remove From Favourites"};
                }
                String s = "None";
                s = (String)JOptionPane.showInputDialog(
                        parent,
                        "Room " + roomNumber + "\n" + description,
                        name,
                        JOptionPane.PLAIN_MESSAGE,
                        icon,
                        possibilities,
                        "None");
                switch (s) {
                    case "Delete" -> {
                        try {
                            layer.removePOI(layer.getPOI(relative_x));
                            setVisible(false);
                        } catch (IOException | ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case "Add To Favourites" -> {
                        favUsers.add(layer.getCurrUser().getUsername());
                        try {
                            layer.removePOI(POI.this);
                        } catch (IOException | ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            layer.addPOI(POI.this);
                        } catch (IOException | ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case "Remove From Favourites" -> {
                        favUsers.remove(layer.getCurrUser().getUsername());
                        try {
                            layer.removePOI(POI.this);
                        } catch (IOException | ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            layer.addPOI(POI.this);
                        } catch (IOException | ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });
    }

    public void updatePosition(){
        this.setBounds((int) (relative_x * parent.getParent().getSize().getWidth()), (int) (relative_y * parent.getParent().getSize().getHeight()), 24, 24);
    }
    public Layer getContainingLayer() {
        return containingLayer;
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
    public String getCreatingUsr() {
        return creatingUsr;
    }
    public ArrayList<String> getFavUsers() {
        return favUsers;
    }
}