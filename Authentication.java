import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Authentication {

    private static final String DB_URL = System.getenv("url");
    private static final String DB_USERNAME = System.getenv("username");
    private static final String DB_PASSWORD =   System.getenv("password");

    // Method to register a new Vehicle Owner
    public boolean registerVehicleOwner(int ownerID, String fullName, String email, String password,
                                        String address, String state, String country, String phoneNumber) {
        String sql = "INSERT INTO VehicleOwner (ownerID, fullName, email, password, address, state, country, phoneNumber) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, ownerID);
            statement.setString(2, fullName);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, address);
            statement.setString(6, state);
            statement.setString(7, country);
            statement.setString(8, phoneNumber);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to register a new Job Submitter
    public boolean registerJobSubmitter(int clientID, String fullName, String email, String password,
                                        String subPlan, String address, String state, String country, String phoneNumber) {
        String sql = "INSERT INTO JobSubmitter (clientID, fullName, email, password, subPlan, address, state, country, phoneNumber) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, clientID);
            statement.setString(2, fullName);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, subPlan);
            statement.setString(6, address);
            statement.setString(7, state);
            statement.setString(8, country);
            statement.setString(9, phoneNumber);

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
