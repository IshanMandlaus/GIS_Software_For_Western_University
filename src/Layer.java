import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//create constants for the layer names, CLASSROOM=0, etc.

public class Layer {
    private final String name;
    private final ArrayList<POI> POIList;
    private JLabel parent;
    long floorID, buildingID, layerID; // id = 0 is user created POIs
    private User currUser;

    public Layer(String name, long buildingID, long floorID, long layerID, JLabel parent, User currUser) throws IOException, ParseException {
        this.name = name;
        this.POIList = new ArrayList<POI>();
        this.parent = parent;
        this.layerID = layerID;
        this.floorID = floorID;
        this.buildingID = buildingID;
        this.currUser = currUser;
        readJSON(buildingID,floorID); //now the layer has associated POIs
    }

    public void hideLayer(){
        for (POI p : POIList) p.setVisible(false);
    }

    public void showLayer(){
        for (POI p : POIList) p.setVisible(true);
    }

    public void addPOI(POI poi) throws IOException, ParseException {
        POIList.add(poi);
        JSONObject newPOI = new JSONObject();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("jsonfiles/POI.json"));
        JSONArray poiInJSON = (JSONArray) obj;
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
        newPOI.put("favUsers", new ArrayList<String>());
        FileWriter file = new FileWriter("jsonfiles/POI.json");
        try {
            poiInJSON.add(newPOI);
            file.write(poiInJSON.toJSONString());
            file.flush();
        }
        catch (Exception e) {
            ;
        }
    }

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
                POI newPOI = new POI(builtin, name, rmNum, description, buildingID, floorID, layerID, relative_x, relative_y, parent, creatingUsr, favUsers, this);
                POIList.add(newPOI);
            }
        }
    }
    //getter for entire POI list
    public ArrayList<POI> getPOIList() {
        return POIList;
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

    public User getCurrUser() {
        return currUser;
    }
}
