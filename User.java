import java.util.ArrayList;

/**
 * The class that represents a CUAMPES-ACGISANT user. It allows a user to have login information, a list of POIs,
 * and a list of favourite POIs.
 *
 * @author Paul Francis Jarabek Moore
 *
 * @version 1.0
 *
 * @see POI
 */
public class User {
    /** The user's username*/
    private String username;
    /** The user's password*/
    private String password;
    /** A list of all POIs created by the user*/
    private ArrayList<POI> pointsOfInterest;
    /** A list of all POIs the user added to their favourites*/
    private ArrayList<POI> favourites;

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
     * @param location The location of the POI on the map.
     * @param name The desired name of the POI.
     * @param description A short description of the POI.
     *
     * @see POI
     */
    public void createPOI(String name,int[] location, String description){
        this.pointsOfInterest.add(new POI(false, name, description, location, 0));
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
}
