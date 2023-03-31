package src.main.java;

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
                    new Layer("Classrooms", 1,map),
                    new Layer("Washrooms", 2,map),
                    new Layer("Restaurants", 3,map),
                    new Layer("Entries/Exit", 4,map),
                    new Layer("Collaboration Rooms", 5,map),
                    new Layer("User Created", 6,map)
            },i);
        }
    }
}
