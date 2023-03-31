import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.*;

public class Layer {
    private final String name;
    private final ArrayList<POI> POIList;
    private final int id; // id = 0 is user created POIs
    private JLabel parent;

    public Layer(String name, int id, JLabel parent) throws IOException, ParseException {
        this.name = name;
        this.POIList = new ArrayList<POI>();
        this.id = id;
        this.parent = parent;
        readJSON(); //now the layer has associated POIs

    }

    public void hideLayer(){
        for (POI p : POIList) p.setVisible(false);
    }

    public void showLayer(){
        for (POI p : POIList) p.setVisible(true);
    }

    public void addPOI(POI poi) throws IOException {
        POIList.add(poi);
        poi.setLayerID(id);
        JSONObject newPOI = new JSONObject();
        newPOI.put("builtin", poi.getBuiltin());
        newPOI.put("name", poi.getName());
        newPOI.put("room", poi.getRoom());
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

    public void readJSON() throws IOException, ParseException {
        //reads the JSON file and adds POIs to POIList if they have the same id as this layer
        JSONParser parser = new JSONParser();
        FileReader r = new FileReader("jsonfiles/POI.json");
        Object obj = parser.parse(r);
        JSONArray poiInJSON = (JSONArray) obj;
        //iterate over poiList and add POIs to POIList if they have the same layer id as this layer
        for (Object o : poiInJSON) {
            JSONObject poi = (JSONObject) o;
            long thisPOILayerID = (long) poi.get("layer");
            if (thisPOILayerID == id) {
                long buildingID = (long)  poi.get("building");
                String name = (String) poi.get("name");
                long floorID = (long)  poi.get("floor");
                long layerID = (long)  poi.get("layer");
                double relative_x = Double.parseDouble((String) poi.get("x"));
                double relative_y = Double.parseDouble((String) poi.get("y"));
                String description = (String) poi.get("description");
                boolean builtin = Boolean.parseBoolean((String) poi.get("builtin"));
                long rmNum = (long)  poi.get("room");
                POI newPOI = new POI(builtin, name, rmNum, description, buildingID, floorID, layerID, relative_x, relative_y, parent);
                POIList.add(newPOI);
            }
        }

    }
}
