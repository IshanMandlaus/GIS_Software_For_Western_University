import java.util.ArrayList;

public class Layer {
    private String name;
    private ArrayList<POI> POIList;
    private int id; // id = 0 is user created POIs

    public Layer(String name, ArrayList<POI> POIList, int id) {
        this.name = name;
        this.POIList = POIList;
        this.id = id;
    }

    public void hideLayer(){

    }

    public void showLayer(){

    }

    public void addPOI(){

    }

    public void removePOI(){

    }
}
