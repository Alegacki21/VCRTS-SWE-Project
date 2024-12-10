import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {
    String url = "jdbc:mysql://localhost:3306/vcrts";
        String sqlUsername = "root";
        String password = "doyoubelieveinlove";
        public Authentication() {

        }
    public boolean authenticateVehicleOwner(String inputUsername, String inputPassword) {
        boolean isAuthenticated = false;

        try (Connection connection = DriverManager.getConnection(url, sqlUsername, password)) {
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
        try (Connection connection = DriverManager.getConnection(url, sqlUsername, password)) {
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
        try (Connection connection = DriverManager.getConnection(url, sqlUsername, password)) {
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
    }
}
