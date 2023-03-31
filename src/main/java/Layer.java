package main.java;

import java.util.ArrayList;

public class Layer {
    private final String name;
    private final ArrayList<POI> POIList;
    private final int id; // id = 0 is user created POIs

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
