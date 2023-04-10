import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
class FloorTest {

    @Test
        public void getLayer() throws IOException, ParseException {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer1 = new Layer(0,1,3,map,new User("saad","1"));
        Layer testLayer2 = new Layer(0,1,4,map,new User("saad","1"));
        Layer[] testLayers = new Layer[2];
        testLayers[0] = testLayer1;
        testLayers[1] = testLayer2;
        Floor testFloor = new Floor(testLayers,1,0);
        Long fID = testFloor.getLayer(0).getLayerID();
        assertEquals(fID, 3);
    }

    @Test
    public void getFloorNumber() throws IOException, ParseException {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer1 = new Layer(0,1,3,map,new User("saad","1"));
        Layer testLayer2 = new Layer(0,1,4,map,new User("saad","1"));
        Layer[] testLayers = new Layer[2];
        testLayers[0] = testLayer1;
        testLayers[1] = testLayer2;
        Floor testFloor = new Floor(testLayers,1,0);
        int fNum = testFloor.getFloorNumber();
        int expected = 1;
        assertEquals(expected, fNum);
    }

    @Test
    public void getLayers() throws IOException, ParseException {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer1 = new Layer(0,1,3,map,new User("saad","1"));
        Layer testLayer2 = new Layer(0,1,4,map,new User("saad","1"));
        Layer[] testLayers = new Layer[2];
        testLayers[0] = testLayer1;
        testLayers[1] = testLayer2;
        Floor testFloor = new Floor(testLayers,1,0);
        Layer[] fLayers = testFloor.getLayers();
        Long lID = fLayers[0].getLayerID();
        Long expected = 3L;
        assertEquals(expected, lID);
    }
}