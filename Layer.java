import java.util.ArrayList;

public class Layer {
    private String name;
    private ArrayList<POI> POIList;
    private int id; // id = 0 is user created POIs

    public Layer(String name, int id) {
        this.name = name;
        this.POIList = new ArrayList<POI>();
        this.id = id;
    }

    public void hideLayer(){

    }

    public void showLayer(){

    }

    public void addPOI(POI poi){
        POIList.add(poi);
        poi.setLayerID(id);
    }

    public void removePOI(){

    }
}
