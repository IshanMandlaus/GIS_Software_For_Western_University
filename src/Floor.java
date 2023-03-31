public class Floor {
    //array of floors, floor is a collection of layers, a layer is a collection of POIs
    private final Layer[] layers;
    private final int floorNumber;
    public Floor(Layer[] layers, int floorNumber) {
        this.layers = layers;
        this.floorNumber = floorNumber;
    }

}
