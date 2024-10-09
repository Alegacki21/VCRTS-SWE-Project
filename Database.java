import java.util.HashMap;

public class Database {

    // HashMap to store usernames and passwords
    protected static HashMap<String, String> users = new HashMap<>();
    static {
    users.put("abc", "123");
    users.put("Java", "password");
    }
}
