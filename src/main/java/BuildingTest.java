import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
class BuildingTest {
    @Test
    public void getFloor() throws IOException, ParseException {
        Building building = new Building("Middlesex College1", 5, 1);
        assertEquals(null, building.getFloor(0));
    }

    @Test
    public void getFloors() throws IOException, ParseException {
        Building building = new Building("Middlesex College1", 5, 1);
        assertEquals(5, building.getFloors().length);
    }

    @Test
    public void getBuildingName() throws IOException, ParseException {
        Building building = new Building("Middlesex College", 5, 1);
        assertEquals("Middlesex College", building.getBuildingName());
    }
}