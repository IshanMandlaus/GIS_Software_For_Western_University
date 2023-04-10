import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * The class that represents a CUAMPES-ACGISANT user. It allows a user to have login information, a list of POIs,
 * and a list of favourite POIs.
 *
 * @author Paul Francis Jarabek Moore
 * @author Muhammad Ansari
 *
 * @version 1.0
 *
 * @see POI
 */
public class User {
    /** The user's username*/
    private final String username;
    /** The user's password*/
    private final String password;
    /** A list of all POIs created by the user*/
    final ArrayList<POI> pointsOfInterest;
    /** A list of all POIs the user added to their favourites*/
    final ArrayList<POI> favourites;

    /**
     * Creates a new user with the given username and password.
     *
     * @param username The user's username.
     * @param password The user's password.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.pointsOfInterest = new ArrayList<POI>();
        this.favourites = new ArrayList<POI>();
    }

    /**
     * Adds a POI to this user's list of favourites.
     *
     * @param fav The POI to be added to favourites.
     *
     * @see POI
     */
    public void addToFavourites(POI fav){
        this.favourites.add(fav);
    }

    /**
     * Creates a new POI with the user's desired details.
     *
     * 
     * @param poi The POI to be created.
     * @see POI
     */
    public void createPOI(POI poi){
        this.pointsOfInterest.add(poi);
    }

    /**
     * Deletes a POI that the user has created
     *
     * @param p The POI to be deleted
     *
     * @see POI
     */
    public void deletePOI(POI p){
        this.pointsOfInterest.remove(p);
    }

    /**
     * Checks if the user's credentials are valid.
     *
     * @param username The username to be checked.
     * @param password The password to be checked.
     *
     * @return True if the credentials are valid, false otherwise.
     *
     * @throws IOException If the file cannot be read.
     */
    public static boolean isValidCredentials(String username, char[] password) throws IOException {
        /** Create the JSON parser */
        JSONParser parser = new JSONParser();  //JSON parser object to parse read file
        /** Create the boolean to check if the user is valid */
        boolean correctUser = false;

        /** Try to read the file */
        try {
            /** Parse the file */
            Object object = parser.parse(new FileReader("src/jsonfiles/users.json"));  //parse the file
            JSONObject jsonObject = (JSONObject) object;  //cast the object to a JSONObject
            JSONArray users = (JSONArray) jsonObject.get("users");  //get the array of users

            Iterator<JSONObject> iterator = users.iterator();  //iterate through the array of users

            /** Iterate through the array of users */
            while (iterator.hasNext()) {  //while there are still users to check
                JSONObject user = iterator.next();  //get the next user
                String userName = (String) user.get("username");  //get the username
                JSONArray arrPassword = (JSONArray) user.get("password");  //get the password
                char[] userPassword = new char[arrPassword.size()];  //create a char array to store the password

                /** Convert the password from a JSONArray to a char array */
                for (int i = 0; i < arrPassword.size(); i++) {  //iterate through the password array
                    userPassword[i] = ((String) arrPassword.get(i)).charAt(0);  //add the password to the char array
                }

                /** Check if the username and password match the user's credentials */
                if (username.equals(userName) && Arrays.equals(password, userPassword)) {  //if the username and password match
                    correctUser = true;  //set the boolean to true
                    break;  //break out of the loop
                }
            }

        /** Catch any exceptions */
        } catch (Exception e) {  //if the file cannot be read
            e.printStackTrace();  //print the stack trace
        }
        return correctUser;  //return the boolean
    }

    /**
     * Gets the user's username.
     *
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }
}

