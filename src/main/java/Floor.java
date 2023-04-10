
/**
 * This class represents a floor in a building. It is a collection of layers.
 * @version 1.0
 */

public class Floor {
    /**array of floors, floor is a collection of layers, a layer is a collection of POIs*/
    private final Layer[] layers;
    private final int floorNumber;
    int buildingID;
    public Floor(Layer[] layers, int floorNum, int buildingID) {
        this.layers = layers;
        this.floorNumber = floorNum;
        this.buildingID = buildingID;
    }

    /**returns the layer at the specified index*/
    public Layer getLayer(int layerNumber){
        return this.layers[layerNumber];
    }

    /**returns the number of layers in the floor*/
    public int getFloorNumber(){
        return this.floorNumber;
    }

    /**returns the number of layers in the floor*/
    public Layer[] getLayers() {
        return layers;
    }
}
