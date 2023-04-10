import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;


class POITest {

    ArrayList<String> favUsers = new ArrayList<String>(1);

    POITest() throws IOException, ParseException {
        favUsers.add("saad");
    }

    @Test
    public void updatePosition() throws IOException, ParseException {
        //test if the POI updates its position correctly
        //if poi.updatePosition() works print out "Test passed"
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer = new Layer(0,1,3,map,new User("saad","1"));
        POI poi = new POI(true,"The Grad Club",19,"The restaurant in the basement of middlesex",1,0,3,0.49860413176996093,0.30539499036608864,map,"saad",favUsers,testLayer);

        boolean test = true;
        try{
            poi.updatePosition();
        }
        catch (Exception e){
            test = false;
        }
        assertEquals(true,test);
    }

    @Test
    public void getContainingLayer() throws IOException, ParseException {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer = new Layer(0,1,3,map,new User("saad","1"));
        POI poi = new POI(true,"The Grad Club",19,"The restaurant in the basement of middlesex",1,0,3,0.49860413176996093,0.30539499036608864,map,"saad",favUsers,testLayer);
        assertEquals(poi.getContainingLayer(), testLayer);
    }

    @Test
    public void getBuiltin() throws IOException, ParseException {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer = new Layer(0,1,3,map,new User("saad","1"));
        POI poi = new POI(true,"The Grad Club",19,"The restaurant in the basement of middlesex",1,0,3,0.49860413176996093,0.30539499036608864,map,"saad",favUsers,testLayer);
        assertEquals(poi.getBuiltin().booleanValue(), true);
    }

    @Test
    public void getName() throws IOException, ParseException {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer = new Layer(0,1,3,map,new User("saad","1"));
        POI poi = new POI(true,"The Grad Club",19,"The restaurant in the basement of middlesex",1,0,3,0.49860413176996093,0.30539499036608864,map,"saad",favUsers,testLayer);
        assertEquals(poi.getName(), "The Grad Club");
    }

    @Test
    public void getRoomNumber() throws IOException, ParseException {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer = new Layer(0,1,3,map,new User("saad","1"));
        POI poi = new POI(true,"The Grad Club",19,"The restaurant in the basement of middlesex",1,0,3,0.49860413176996093,0.30539499036608864,map,"saad",favUsers,testLayer);
        Long roomNum = poi.getRoomNumber();
        Long expected = 19L;
        assertEquals(roomNum, expected);
    }

    @Test
    public void getDescription() throws IOException, ParseException {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer = new Layer(0,1,3,map,new User("saad","1"));
        POI poi = new POI(true,"The Grad Club",19,"The restaurant in the basement of middlesex",1,0,3,0.49860413176996093,0.30539499036608864,map,"saad",favUsers,testLayer);
        assertEquals(poi.getDescription(), "The restaurant in the basement of middlesex");
    }

    @Test
    public void getBuildingID() throws IOException, ParseException {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer = new Layer(1,1,3,map,new User("saad","1"));
        POI poi = new POI(true,"The Grad Club",19,"The restaurant in the basement of middlesex",1,0,3,0.49860413176996093,0.30539499036608864,map,"saad",favUsers,testLayer);
        Long bID = poi.getBuildingID();
        Long expected = 1L;
        assertEquals(bID, expected);
    }

    @Test
    public void getFloorID() throws IOException, ParseException {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer = new Layer(0,1,3,map,new User("saad","1"));
        POI poi = new POI(true,"The Grad Club",19,"The restaurant in the basement of middlesex",1,0,3,0.49860413176996093,0.30539499036608864,map,"saad",favUsers,testLayer);
        Long fID = poi.getFloorID();
        Long expected = 0L;
        assertEquals(fID, expected);
    }

    @Test
    public void getLayerID() throws IOException, ParseException {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer = new Layer(0,1,3,map,new User("saad","1"));
        POI poi = new POI(true,"The Grad Club",19,"The restaurant in the basement of middlesex",1,0,3,0.49860413176996093,0.30539499036608864,map,"saad",favUsers,testLayer);
        Long lID = poi.getLayerID();
        Long expected = 3L;
        assertEquals(lID, expected);
    }

    @Test
    public void getRelativeX() throws IOException, ParseException {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer = new Layer(0,1,3,map,new User("saad","1"));
        POI poi = new POI(true,"The Grad Club",19,"The restaurant in the basement of middlesex",1,0,3,0.49860413176996093,0.30539499036608864,map,"saad",favUsers,testLayer);
        double x = poi.getRelativeX();
        double expected = 0.49860413176996093;
        assertEquals(x, expected);
    }

    @Test
    public void getRelativeY() throws IOException, ParseException {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer = new Layer(0,1,3,map,new User("saad","1"));
        POI poi = new POI(true,"The Grad Club",19,"The restaurant in the basement of middlesex",1,0,3,0.49860413176996093,0.30539499036608864,map,"saad",favUsers,testLayer);
        assertEquals(poi.getRelativeY(), 0.30539499036608864);
    }

    @Test
    public void getParent() throws IOException, ParseException {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer = new Layer(0,1,3,map,new User("saad","1"));
        POI poi = new POI(true,"The Grad Club",19,"The restaurant in the basement of middlesex",1,0,3,0.49860413176996093,0.30539499036608864,map,"saad",favUsers,testLayer);
        assertEquals(poi.getParent(), map);
    }

    @Test
    public void getCreatingUsr() throws IOException, ParseException {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer = new Layer(0,1,3,map,new User("saad","1"));
        POI poi = new POI(true,"The Grad Club",19,"The restaurant in the basement of middlesex",1,0,3,0.49860413176996093,0.30539499036608864,map,"saad",favUsers,testLayer);
        assertEquals(poi.getCreatingUsr(),"saad");
    }

    @Test
    public void getFavUsers() throws IOException, ParseException {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);
        Layer testLayer = new Layer(0,1,3,map,new User("saad","1"));
        POI poi = new POI(true,"The Grad Club",19,"The restaurant in the basement of middlesex",1,0,3,0.49860413176996093,0.30539499036608864,map,"saad",favUsers,testLayer);
        assertEquals(poi.getFavUsers(), favUsers);
    }
}