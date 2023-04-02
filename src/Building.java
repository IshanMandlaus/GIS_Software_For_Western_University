import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.IOException;

public class Building {
    //array of floors, floor is a collection of layers, a layer is a collection of POIs
    private final Floor[] floors;
    private final String buildingName;
    private final int id;
    private JLabel map;

    public Building(String buildingName, int numFloors, int id, JLabel map) throws IOException, ParseException {
        this.buildingName = buildingName;
        this.id = id;
        this.floors = new Floor[numFloors];
        this.map = map;
        //loop through f and create a new floor for each
        for (int i = 0; i < numFloors; i++) {
            floors[i] = new Floor(new Layer[]{
                    new Layer("Classrooms",id,i, 1,map),
                    new Layer("Washrooms", id,i,2,map),
                    new Layer("Restaurants", id,i,3,map),
                    new Layer("Entries/Exit", id,i,4,map),
                    new Layer("Collaboration Rooms", id,i,5,map),
                    new Layer("User Created", id,i,6,map)
            },i,id);
        }
    }
    public Floor getFloor(int floorNumber){
        return this.floors[floorNumber];
    }

    public Floor[] getFloors() {
        return floors;
    }

    public String getBuildingName() {
        return buildingName;
    }
}
