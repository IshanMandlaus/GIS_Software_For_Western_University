/**

 This class represents a layer (group of similar POIs)
 Each layer has a list of POIs, building ID, floor ID, layer ID, a reference to its parent JLabel, and a current user.
 This class has the following functionalities:
    hiding/showing all the POIs in the layer
    adding/removing a POI to/from the layer and saving the changes in the POI.json file
    reading the POI.json file and adding the POIs that belong to this layer to the POIList
    returning the POIList and retrieving a POI by its relative_x coordinate
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Layer {

    /**
     * An ArrayList of POIs that belong to this layer.
     */
    private final ArrayList<POI> POIList;
    /**
     * A reference to the parent (container) JLabel of this layer.
     */
    private final JLabel parent;
    /**
     * The ID of the building and floor that this layer belongs to, and it's ID.
     * layerID represents which type of POIs belong to it.
     */
    long floorID, buildingID, layerID;
    /**
     * The current user who is logged in.
     */
    private final User currUser;

    /**
     * Constructs a Layer object with the given buildingID, floorID, layerID, parent JLabel, and current user.
     * Reads the appropriate POIs from the POI.json file and adds them to the POIList.
     *
     * @param buildingID the ID of the building that this layer belongs to
     * @param floorID the ID of the floor that this layer belongs to
     * @param layerID the ID of this layer
     * @param parent the parent JLabel of this layer
     * @param currUser the current user who is logged in
     * @throws IOException if an I/O error occurs while reading the POI.json file
     * @throws ParseException if the POI.json file contains invalid JSON syntax
     */
    public Layer(long buildingID, long floorID, long layerID, JLabel parent, User currUser) throws IOException, ParseException {
        this.POIList = new ArrayList<POI>();
        this.parent = parent;
        this.layerID = layerID;
        this.floorID = floorID;
        this.buildingID = buildingID;
        this.currUser = currUser;
        readJSON(buildingID,floorID); // Read appropriate POIs from POI.json into this Layer
    }

    /**
     * Hides all the POIs in this layer by setting their visibility to false.
     */
    public void hideLayer(){
        for (POI p : POIList) p.setVisible(false);
    }

    /**
     * Shows all the POIs in this layer by setting their visibility to true.
     */
    public void showLayer(){
        for (POI p : POIList) p.setVisible(true);
    }

    /**
     * Adds a new POI to the list of POIs and writes it to the POI.json file.
     * @param poi the POI to add
     * @throws IOException if there is an error reading or writing to the POI.json file
     * @throws ParseException if there is an error parsing the JSON file
     */
    public void addPOI(POI poi) throws IOException, ParseException {
        POIList.add(poi);
        JSONObject newPOI = new JSONObject();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("jsonfiles/POI.json"));
        JSONArray poiInJSON = (JSONArray) obj;

        // Populate the new JSON object with the properties of the POI object
        newPOI.put("builtin", poi.getBuiltin());
        newPOI.put("name", poi.getName());
        newPOI.put("roomNumber", poi.getRoomNumber());
        newPOI.put("description", poi.getDescription());
        newPOI.put("buildingID", poi.getBuildingID());
        newPOI.put("floorID", poi.getFloorID());
        newPOI.put("layerID", poi.getLayerID());
        newPOI.put("relative_x", poi.getRelativeX());
        newPOI.put("relative_y", poi.getRelativeY());
        newPOI.put("creatingUsr", currUser.getUsername());
        newPOI.put("favUsers", poi.getFavUsers());

        FileWriter file = new FileWriter("jsonfiles/POI.json");
        try {
            poiInJSON.add(newPOI); // Add the new POI object to the JSON array
            file.write(poiInJSON.toJSONString()); // Write the updated JSON array to the file
            file.flush();
        }
        catch (Exception ignored) {
            ;
        }
    }

    /**
     * Removes a point of interest from the POI list and the corresponding JSON file.
     * @param poi the point of interest to be removed.
     * @throws IOException if there is an I/O error while reading or writing the JSON file.
     * @throws ParseException if there is an error while parsing the JSON file.
     */
    public void removePOI(POI poi) throws IOException, ParseException, IOException {
        //search for POI in POIList
        //remove POI from POIList
        POIList.remove(poi);

        //remove POI from JSON file
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("jsonfiles/POI.json"));
        JSONArray poiInJSON = (JSONArray) obj;
        for (Object o : poiInJSON) {
            JSONObject poiObj = (JSONObject) o;
            if ((double)poiObj.get("relative_x") == poi.getRelativeX()) {
                poiInJSON.remove(poiObj);
                break;
            }
        }
        FileWriter file = new FileWriter("jsonfiles/POI.json");
        file.write(poiInJSON.toJSONString());
        file.flush();
    }

    /**
     * Reads the JSON file and adds POIs to POIList if they have the same id as this layer.
     * @param bID The id of the building the POI belongs to.
     * @param fID The id of the floor the POI belongs to.
     * @throws IOException If there is an error reading the JSON file.
     * @throws ParseException If there is an error parsing the JSON file.
     */
    public void readJSON(long bID, long fID) throws IOException, ParseException {
        //reads the JSON file and adds POIs to POIList if they have the same id as this layer
        JSONParser parser = new JSONParser();
        FileReader r = new FileReader("jsonfiles/POI.json");
        Object obj = parser.parse(r);
        JSONArray poiInJSON = (JSONArray) obj;
        //iterate over poiList and add POIs to POIList if they have the same layer id as this layer
        for (Object o : poiInJSON) {
            JSONObject poi = (JSONObject) o;
            long thisPOILayerID = (long) poi.get("layerID");
            //if the POI has the same layer id as this layer AND the POI has the same name floor and building
            if (thisPOILayerID == layerID && ((long) poi.get("buildingID")) == bID && ((long) poi.get("floorID")) == fID && ((boolean) poi.get("builtin") || currUser.getUsername().equals(poi.get("creatingUsr")))) {
                long buildingID = (long)  poi.get("buildingID");
                String name = (String) poi.get("name");
                long floorID = (long)  poi.get("floorID");
                long layerID = (long)  poi.get("layerID");
                double relative_x = (double) poi.get("relative_x");
                double relative_y = (double) poi.get("relative_y");
                String description = (String) poi.get("description");
                boolean builtin = (Boolean) poi.get("builtin");
                long rmNum = (long)  poi.get("roomNumber");
                String creatingUsr = (String) poi.get("creatingUsr");
                ArrayList<String> favUsers = (ArrayList<String>) poi.get("favUsers");

                // Create a new POI Object, and add it to this layer
                POI newPOI = new POI(builtin, name, rmNum, description, buildingID, floorID, layerID, relative_x, relative_y, parent, creatingUsr, favUsers, this);
                POIList.add(newPOI);
            }
        }
    }

    /**
     * A method to get the list of POIs in this layer
     * @return An ArrayList of POIs representing the POIs in this layer
     */
    public ArrayList<POI> getPOIList() {
        return POIList;
    }

    /**
     * A method to get a POI in this layer by its relative_x value
     * @param relative_x The relative_x value of the POI to retrieve
     * @return The POI with the specified relative_x value, or null if no such POI exists in this layer
     */
    public POI getPOI(double relative_x){
        for (POI p : POIList){
            if (p.getRelativeX() == relative_x)
                {return p;}
        }
        return null;
    }

    /**
     * A method to get the current user
     * @return The User object representing the current user
     */
    public User getCurrUser() {
        return currUser;
    }
}
