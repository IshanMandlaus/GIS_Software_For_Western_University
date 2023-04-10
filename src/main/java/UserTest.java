import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the User class
 * @author Muhammad Ansari
 * @version 1.0
 */

class UserTest {

    /**
     * Tests the addToFavourites method in the User class
     */
    @Test
    public void testAddToFavourites() {
        /** Create a new map */
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);

        /** Create a new user and a new POI */
        User user = new User("mansar33", "password");
        POI pointOfInterest = new POI(true, "Test", 123, "Test description", 3, 4, 6, 0.5, 0.5, map, null, null, null);

        /** Add the POI to the user's favourites */
        user.addToFavourites(pointOfInterest);

        /** Check that the POI has been added to the user's favourites */
        Assertions.assertEquals(1, user.favourites.size());
        Assertions.assertEquals(pointOfInterest, user.favourites.get(0));
    }

    /**
     * Tests the createPOI method in the User class
     */
    @Test
    public void testCreatePOI() {
        /** Create a new map */
        User user = new User("mansar33", "password");
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);

        /** Create a new POI */
        POI pointOfInterest = new POI(true, "Test", 123, "Test description", 3, 4, 6, 0.5, 0.5, map, null, null, null);
        user.createPOI(pointOfInterest);

        /** Check that the POI has been added to the user's POIs */
        Assertions.assertEquals(1, user.pointsOfInterest.size());
        Assertions.assertEquals(pointOfInterest, user.pointsOfInterest.get(0));
    }

    /**
     * Tests the deletePOI method in the User class
     */
    @Test
    public void testDeletePOI() {
        /** Create a new map */
        User user = new User("mansar33", "password");
        JLabel map = new JLabel();
        JPanel mapContainer = new JPanel();
        map.setBounds(0, 0, 1920, 1080);
        mapContainer.add(map, 0);

        /** Create a new POI */
        POI poi = new POI(true, "Test", 123, "Test description", 3, 4, 6, 0.5, 0.5, map, null, null, null);

        /** Add the POI to the user's POIs and then delete it */
        user.createPOI(poi);
        user.deletePOI(poi);

        /** Check that the POI has been deleted from the user's POIs */
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
        char[] validPassword = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        String invalidUser = "mansar34";
        char[] invalidPassword = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd', 's'};

        boolean correct = User.isValidCredentials(validUser, validPassword);
        boolean incorrect = User.isValidCredentials(invalidUser, invalidPassword);

        /**
         * The following line of code is used to test the isValidCredentials method
         * The assertTrue and assertFalse methods are used to test if the method returns the correct boolean value
         */
        Assertions.assertAll(
                () -> assertTrue(correct),
                () -> assertFalse(incorrect));

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

        /** Check that the username is correct */
        Assertions.assertEquals("mansar33", username);
    }
}