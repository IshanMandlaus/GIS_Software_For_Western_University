import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    @Test
    public void testAddToFavourites() {
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);

        User user = new User("mansar33", "password");
        POI pointOfInterest = new POI(true, "Test", 123, "Test description", 3, 4, 6, 0.5, 0.5, map, null, null, null);
        user.addToFavourites(pointOfInterest);

        Assertions.assertEquals(1, user.favourites.size());
        Assertions.assertEquals(pointOfInterest, user.favourites.get(0));

    }

    @Test
    public void testCreatePOI() {
        User user = new User("mansar33", "password");
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);

        POI pointOfInterest = new POI(true, "Test", 123, "Test description", 3, 4, 6, 0.5, 0.5, map, null, null, null);
        user.createPOI(pointOfInterest);

        Assertions.assertEquals(1, user.pointsOfInterest.size());
        Assertions.assertEquals(pointOfInterest, user.pointsOfInterest.get(0));
    }

    @Test
    public void testDeletePOI() {
        User user = new User("mansar33", "password");
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);

        POI poi = new POI(true, "Test", 123, "Test description", 3, 4, 6, 0.5, 0.5, map, null, null, null);

        user.createPOI(poi);
        user.deletePOI(poi);

        Assertions.assertEquals(0, user.pointsOfInterest.size());
    }

    /**
     * Tests the isValidCredentials method in the User class
     * @throws IOException
     */
    @Test
    public void testIsValidCredentials() throws IOException {
        /**
         * The following two lines of code are used to test the isValidCredentials method
         * The first line creates a valid username and password
         * The second line creates an invalid username and password
         * The third and fourth lines of code are used to test the isValidCredentials method for an invalid user
         */
        String validUser = "mansar33";
        char[] validPass = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        String invalidUser = "invalid";
        char[] invalidPass = {'i', 'n', 'v', 'a', 'l', 'i', 'd'};

        boolean valid = User.isValidCredentials(validUser, validPass);
        boolean invalid = User.isValidCredentials(invalidUser, invalidPass);

        /**
         * The following line of code is used to test the isValidCredentials method
         * The assertTrue and assertFalse methods are used to test if the method returns the correct boolean value
         */
        Assertions.assertAll(
                () -> assertTrue(valid),
                () -> assertFalse(invalid));
    }

    /**
     * Tests the getUsername method in the User class
     */
    @Test
    public void testGetUsername() {
        /**
         * The following line of code is used to test the getUsername method
         * The assertEquals method is used to test if the method returns the correct username
         */
        User user = new User("mansar33", "password");
        String username = user.getUsername();

        Assertions.assertEquals("mansar33", username);
    }
}