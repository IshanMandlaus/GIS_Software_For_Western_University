import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

/**
 * @author Muhammad Ansari
 * @version 1.0
 */

public class UserData {

    public void jsonReader(String filePath) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject json = (JSONObject) obj;
            String username = (String) json.get("username");
            String password = (String) json.get("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    }
}
