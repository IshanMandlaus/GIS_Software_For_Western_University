import javax.swing.*;
import java.awt.*;

public class POI extends JButton {
    private boolean builtin;
    private String name;
    private String description;
    private int layerID;

    public POI(boolean builtin, String name, String description, Point location) {
        super("button");
        this.builtin = builtin;
        this.name = name;
        this.description = description;
        this.setLocation(location);
        this.setVisible(true);


    }

    public void setLayerID(int layerID) {
        this.layerID = layerID;
    }


    public static void main(String[] args) {
        System.out.println("JJJJ");
    }



}
