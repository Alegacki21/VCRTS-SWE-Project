import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {
    private static final String DB_URL = System.getenv("url");
    private static final String DB_USERNAME = System.getenv("sqlusername");
    private static final String DB_PASSWORD =   System.getenv("password");
    // String url = "jdbc:mysql://localhost:3306/vcrts";
    //     String sqlUsername = "bryan";
    //     String password = "password";
        public Authentication() {

        }
        public boolean registerVehicleOwner(String username, String email, String password, String address, String state, String country, String phoneNumber) { 
            String sql = "INSERT INTO VehicleOwner (username, email, password, address, state, country, phoneNumber) " + 
            "VALUES (?, ?, ?, ?, ?, ?, ?)"; 
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD); 
            PreparedStatement statement = connection.prepareStatement(sql)) { 
                statement.setString(1, username); 
                statement.setString(2, email); 
                statement.setString(3, password); 
                statement.setString(4, address); 
                statement.setString(5, state); 
                statement.setString(6, country); 
                statement.setString(7, phoneNumber); 
                statement.executeUpdate(); 
                return true; } catch (SQLException e) { 
                    e.printStackTrace(); 
                    return false; 
                }
            }
        // Method to register a new Job Submitter
        public boolean registerJobSubmitter(String username, String email, String password,
                                     String address, String state, String country, String phoneNumber) {
    String sql = "INSERT INTO JobSubmitter (username, email, password, subPlan, address, state, country, phoneNumber) " +
                 "VALUES (?, ?, ?, 'monthly', ?, ?, ?, ?)";
    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, username);
        statement.setString(2, email);
        statement.setString(3, password);
        statement.setString(4, address);
        statement.setString(5, state);
        statement.setString(6, country);
        statement.setString(7, phoneNumber);
        statement.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    public boolean authenticateVehicleOwner(String inputUsername, String inputPassword) {
        boolean isAuthenticated = false;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT username, password FROM VehicleOwner WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, inputUsername);
            preparedStatement.setString(2, inputPassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // User found
                isAuthenticated = true;
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAuthenticated;
    }

    public boolean authenticateClient(String inputUsername, String inputPassword ) {
        boolean isAuthenticated = false;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT username, password FROM jobsubmitter WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, inputUsername);
            preparedStatement.setString(2, inputPassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // User found
                isAuthenticated = true;
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAuthenticated;
    }
    public boolean authenticateCloudController(String inputUsername, String inputPassword ) {
        boolean isAuthenticated = false;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT username, password FROM cloudcontroller WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, inputUsername);
            preparedStatement.setString(2, inputPassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // User found
                isAuthenticated = true;
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAuthenticated;
    }
    
    public static void main(String[] args) {
        String inputUsername = "Lee Everett"; // Example username
        String inputPassword = "password123"; // Example password
        Authentication auth =  new Authentication();
        boolean isAuthenticated = auth.authenticateVehicleOwner(inputUsername, inputPassword);

        if (isAuthenticated) {
            System.out.println("Authentication successful!");
        } else {
            System.out.println("Authentication failed. Please check your username and password.");
        }
        String username = "Lamar"; String email = "jane.doe@example.com"; String password = "securepassword123"; 
        String subPlan = "Premium"; String address = "456 Market St"; String state = "CA"; String country = "USA"; 
        String phoneNumber = "0987654321"; // Register the job submitter 
        boolean success = auth.registerJobSubmitter(username, email, password, address, state, country, phoneNumber);
          if (success) { 
            System.out.println("Vehicle owner registered successfully."); 
        } else { 
            System.out.println("Failed to register vehicle owner.");
    }
}
}
