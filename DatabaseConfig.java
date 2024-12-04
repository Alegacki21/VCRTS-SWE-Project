import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {
    private static DatabaseConfig instance;
    private String url;
    private String username;
    private String password;

    private DatabaseConfig() {
        loadEnvironmentVariables();
    }

    public static DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    private void loadEnvironmentVariables() {
        try {
            Properties props = new Properties();
            FileInputStream input = new FileInputStream(".env");
            props.load(input);
            input.close();

            url = props.getProperty("DB_URL");
            username = props.getProperty("DB_USER");
            password = props.getProperty("DB_PASSWORD");
        } catch (IOException e) {
            System.err.println("Error loading .env file: " + e.getMessage());
            // Fallback to default values if .env file is not found
            url = "jdbc:mysql://localhost:3306/vcrts";
            username = "root";
            password = "";
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
} 