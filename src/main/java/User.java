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
 *
 * @version 1.0
 *
 * @see
 */
public class User {
    /** The user's username*/
    private final String username;
    /** The user's password*/
    private final String password;
    /** A list of all POIs created by the user*/
    private final ArrayList<POI> pointsOfInterest;
    /** A list of all POIs the user added to their favourites*/
    private final ArrayList<POI> favourites;

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
     *
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

    public static boolean isValidCredentials(String username, char[] password) throws IOException {
        JSONParser parser = new JSONParser();
        boolean validUser = false;

        try {
            Object obj = parser.parse(new FileReader("src/jsonfiles/users.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray users = (JSONArray) jsonObject.get("users");

            Iterator<JSONObject> iterator = users.iterator();

            while (iterator.hasNext()) {
                JSONObject user = iterator.next();
                String uName = (String) user.get("username");
                JSONArray passwordArr = (JSONArray) user.get("password");
                char[] uPass = new char[passwordArr.size()];

                for (int i = 0; i < passwordArr.size(); i++) {
                    uPass[i] = ((String) passwordArr.get(i)).charAt(0);
                }

                if (username.equals(uName) && Arrays.equals(password, uPass)) {
                    validUser = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validUser;
    }
    //getters
    public String getUsername() {
        return username;
    }
}

