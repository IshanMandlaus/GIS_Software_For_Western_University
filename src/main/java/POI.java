import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class representing a layer of Points of Interest (POIs) on a floor.
 *
 * @author Paul Francis Jarabek Moore
 * @author Saad Mahmood
 *
 * @version 1.0
 *
 * @see Floor
 * @see POI
 */



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
    /**
     * An array of users that have favourited this POI.
     */
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
            case CLASSROOM -> "src/Icons/classroom.png";
            case WASHROOM -> "src/Icons/washroom.png";
            case RESTAURANT -> "src/Icons/restaurant.png";
            case EXIT -> "src/Icons/exit.png";
            case COMPUTER_LAB -> "src/Icons/computer-lab.png";
            case COLLAB -> "src/Icons/collab.png";
            default -> "src/Icons/user.png";
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
    public POI( String name){
        this.name = name;
    }

    /**
     * Updates the position of the POI on the floor.
     */
    public void updatePosition(){
        this.setBounds((int) (relative_x * parent.getParent().getSize().getWidth()), (int) (relative_y * parent.getParent().getSize().getHeight()), 24, 24);
    }

    /**
     * Returns the layer that this POI is contained in.
     * @return the layer that this POI is contained in.
     */
    public Layer getContainingLayer() {
        return containingLayer;
    }

    /**
     * Returns the builtin.
     * @return the builtin.
     */
    public Boolean getBuiltin() {
        return builtin;
    }

    /**
     * Returns the name of the POI.
     * @return the name of the POI.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the room number of the POI.
     * @return the room number of the POI.
     */
    public Long getRoomNumber() {
        return roomNumber;
    }

    /**
     * Returns the description of the POI.
     * @return the description of the POI.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the building ID of the POI.
     * @return the building ID of the POI.
     */
    public Long getBuildingID() {
        return buildingID;
    }

    /**
     * Returns the floor ID of the POI.
     * @return the floor ID of the POI.
     */
    public Long getFloorID() {
        return floorID;
    }

    /**
     * Returns the layer ID of the POI.
     * @return the layer ID of the POI.
     */
    public Long getLayerID() {
        return layerID;
    }

    /**
     * Returns the relative x position of the POI.
     * @return the relative x position of the POI.
     */
    public Double getRelativeX() {
        return relative_x;
    }

    /**
     * Returns the relative y position of the POI.
     * @return the relative y position of the POI.
     */
    public Double getRelativeY() {
        return relative_y;
    }

    /**
     * Returns the parent JLabel of the POI.
     * @return the parent JLabel of the POI.
     */
    public JLabel getParent() {
        return parent;
    }

    /**
     * Returns the creating user of the POI.
     * @return the creating user of the POI.
     */
    public String getCreatingUsr() {
        return creatingUsr;
    }

    /**
     * Returns the favourite users of the POI.
     * @return the favourite users of the POI.
     */
    public ArrayList<String> getFavUsers() {
        return favUsers;
    }
}