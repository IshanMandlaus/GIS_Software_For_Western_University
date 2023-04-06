/**
 * A class representing a building that contains multiple floors, each of which contains multiple layers of Points of Interest (POIs).
 */
import org.json.simple.parser.ParseException;
import javax.swing.*;
import java.io.IOException;

public class Building {

    /**
     * An array of floors that make up the building.
     */
    private final Floor[] floors;

    /**
     * The name of the building.
     */
    private final String buildingName;

    /**
     * The ID of the building.
     */
    private final int id;

    /**
     * The label that displays the map of the building.
     */
    private JLabel map;

    /**
     * Constructs a new Building object.
     *
     * @param buildingName  the name of the building
     * @param numFloors     the number of floors in the building
     * @param id            the ID of the building
     * @param map           the label that displays the map of the building
     * @param currUser      the current user of the application
     * @throws IOException          if there is an error reading from the file system
     * @throws ParseException       if there is an error parsing the JSON data
     */
    public Building(String buildingName, int numFloors, int id, JLabel map, User currUser) throws IOException, ParseException {
        this.buildingName = buildingName;
        this.id = id;
        this.floors = new Floor[numFloors];
        this.map = map;

        // loop through each floor and create a new floor object for each
        for (int i = 0; i < numFloors; i++) {
            floors[i] = new Floor(new Layer[]{
                    new Layer(id, i, 0, map, currUser), // User-Created
                    new Layer(id, i, 1, map, currUser), // Classrooms
                    new Layer(id, i, 2, map, currUser), // Washrooms
                    new Layer(id, i, 3, map, currUser), // Restaurants
                    new Layer(id, i, 4, map, currUser), // Entries/Exits
                    new Layer(id, i, 5, map, currUser), // Computer/Gen Labs
                    new Layer(id, i, 6, map, currUser), // Collaboration Rooms
            }, i, id);
        }
    }

    /**
     * Returns the floor at the specified index.
     *
     * @param floorNumber  the index of the floor to retrieve
     * @return             the floor at the specified index
     */
    public Floor getFloor(int floorNumber) {
        return this.floors[floorNumber];
    }

    /**
     * Returns an array of all floors in the building.
     *
     * @return  an array of all floors in the building
     */
    public Floor[] getFloors() {
        return floors;
    }

    /**
     * Returns the name of the building.
     *
     * @return  the name of the building
     */
    public String getBuildingName() {
        return buildingName;
    }
}
