public class Floor {
    //array of floors, floor is a collection of layers, a layer is a collection of POIs
    private final Layer[] layers;
    private final int floorNumber;
    int buildingID;
    public Floor(Layer[] layers, int floorNum, int buildingID) {
        this.layers = layers;
        this.floorNumber = floorNum;
        this.buildingID = buildingID;
    }
    public Layer getLayer(int layerNumber){
        return this.layers[layerNumber];
    }
    public int getFloorNumber(){
        return this.floorNumber;
    }

}
