package src.main.java;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.*;
//create constants for the layer names, CLASSROOM=0, etc.

public class Layer {
    private final String name;
    private final ArrayList<POI> POIList;
    private final int CLASSROOM = 1;
    private final int WASHROOM = 2;
    private final int RES = 3;
    private final int RESTROOM = 4;
    private final int STAIRS = 5;
    private final int ELEVATOR = 6;
    private final int EXIT = 7;
    private final int OTHER = 8;
    private JLabel parent;
    long floorID, buildingID, layerID; // id = 0 is user created POIs

    public Layer(String name, long buildingID, long floorID, long layerID, JLabel parent) throws IOException, ParseException {
        this.name = name;
        this.POIList = new ArrayList<POI>();
        this.parent = parent;
        this.layerID = layerID;
        this.floorID = floorID;
        this.buildingID = buildingID;
        readJSON(buildingID,floorID); //now the layer has associated POIs
    }

    public void hideLayer(){
        for (POI p : POIList) p.setVisible(false);
    }

    public void showLayer(){
        for (POI p : POIList) p.setVisible(true);
    }

    public void addPOI(POI poi) throws IOException {
        POIList.add(poi);
        poi.setLayerID(layerID);
        JSONObject newPOI = new JSONObject();
        newPOI.put("builtin", poi.getBuiltin());
        newPOI.put("name", poi.getName());
        newPOI.put("room", poi.getRoomNumber());
        newPOI.put("description", poi.getDescription());
        newPOI.put("building", poi.getBuildingID());
        newPOI.put("floor", poi.getFloorID());
        newPOI.put("layer", poi.getLayerID());
        newPOI.put("x", poi.getRelativeX());
        newPOI.put("y", poi.getRelativeY());
        FileWriter file = new FileWriter("jsonfiles/POI.json");
        file.write(newPOI.toJSONString());
        file.flush();
    }

    public void removePOI(POI poi) throws IOException, ParseException, IOException {
        //search for POI in POIList
        //remove POI from POIList
        POIList.remove(poi);
        //remove POI from JSON file
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("jsonfiles/POI.json"));
        JSONArray poiInJSON = (JSONArray) obj;
        int index = 0;
        for (Object o : poiInJSON) {
            JSONObject poiObj = (JSONObject) o;
            if (poiObj.get("name") == poi.getName()) {
                poiInJSON.remove(index);
                break;
            }
            index++;
        }
        FileWriter file = new FileWriter("jsonfiles/POI.json");
        file.write(poiInJSON.toJSONString());
        file.flush();
    }

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
            if (thisPOILayerID == layerID && ((long) poi.get("buildingID")) == bID && ((long) poi.get("floorID")) == fID) {

/*                System.out.println("POI in this layer>>jsonPOI layerID = "+thisPOILayerID+" layer id: " + layerID);
                //print all the POI info
                System.out.println("Name: " + poi.get("name"));
                System.out.println("Room: " + poi.get("roomNumber"));
                System.out.println("Description: " + poi.get("description"));
                System.out.println("Building: " + poi.get("buildingID"));
                System.out.println("Floor: " + poi.get("floorID"));
                System.out.println("Layer: " + poi.get("layerID"));
                System.out.println("x: " + poi.get("relative_x"));
                System.out.println("y: " + poi.get("relative_y"));
                System.out.println("builtin: " + poi.get("builtin"));                   */

                long buildingID = (long)  poi.get("buildingID");
                String name = (String) poi.get("name");
                long floorID = (long)  poi.get("floorID");
                long layerID = (long)  poi.get("layerID");
                double relative_x = (double) poi.get("relative_x");
                double relative_y = (double) poi.get("relative_y");
                String description = (String) poi.get("description");
                boolean builtin = (Boolean) poi.get("builtin");
                long rmNum = (long)  poi.get("roomNumber");
                POI newPOI = new POI(builtin, name, rmNum, description, buildingID, floorID, layerID, relative_x, relative_y, parent);
                POIList.add(newPOI);
            }
        }
/*        System.out.println("finished loop for this layer object");
        System.out.println("POIList has following names: ");
        for (POI p : POIList) System.out.println(p.getName());              */

    }
    public POI getPOI(String name){
        for (POI p : POIList){
            if (p.getName().equals(name))
                {return p;} //returning null because it may not be adding layers or adding POIs into the layers or reading the JSON file
        }
        return null;
    }
    public long getLayerID() {
        return layerID;
    }
    public long getFloorID() {
        return floorID;
    }
}
