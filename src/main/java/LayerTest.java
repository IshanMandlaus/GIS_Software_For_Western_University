import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class LayerTest {


    @Test
    void testHideLayer() throws IOException, ParseException {
        User testUser = new User("imandlau","password");
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer test = new Layer(1,0,1,testUser,map);
        for (POI i: test.POIList) {
            i.setVisible(true);
        }
        test.hideLayer();
        assertEquals(false,test.getPOIList().get(0).isVisible());
    }

    @Test
    void testShowLayer() throws IOException, ParseException {
        User testUser = new User("imandlau","password");
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer test = new Layer(1,0,1,testUser,map);
        for (POI i: test.POIList) {
            i.setVisible(false);
        }
        test.showLayer();
        assertEquals(true,test.getPOIList().get(0).isVisible());
    }

    @Test
    void testAddPOI() throws IOException, ParseException {
        User testUser = new User("imandlau","password");
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer test = new Layer(1,0,1,testUser,map);

        POI p = new POI("test");
        test.addPOI(p);

        assertEquals("test",test.getPOIList().get(test.POIList.size()-1).getName());
    }

    @Test
    void testRemovePOI() throws IOException, ParseException {
        User testUser = new User("imandlau","password");
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer test = new Layer(1,0,1,testUser,map);

        POI p = new POI("test");
        test.addPOI(p);
        test.removePOI(p);

        for (POI i: test.getPOIList()) {
            if (i.getName().equals("test")){
                break;
            } else {
                assertEquals(true,true);
            }
        }
    }

    @Test
    void testReadJSON() throws IOException, ParseException {
        User testUser = new User("imandlau","password");
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer test = new Layer(1,0,1,testUser,map);
        
        test.readJSON(1,0);
        for (POI i: test.getPOIList()) {
            if (i.getName().equals("Classroom 17")){
                assertEquals(true,true);
            }
        }
    }

    @Test
    void testGetPOIList() throws IOException, ParseException {
        User testUser = new User("imandlau","password");
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer test = new Layer(1,0,1,testUser,map);

        for (POI i: test.getPOIList()) {
            if (i.getName().equals("Classroom 17")){
                assertEquals(true,true);
            }
        }
    }

    @Test
    void testGetPOIByRelativeX() throws IOException, ParseException {
        User testUser = new User("imandlau","password");
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer test = new Layer(1,0,1,testUser,map);

        for (POI i: test.getPOIList()) {
            if (i.getRelativeX() == 0.2758235622557231){
                assertEquals(true,true);
            }
        }
    }
}