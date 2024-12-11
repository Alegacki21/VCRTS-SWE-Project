import java.sql.*;
import javax.swing.JOptionPane;

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
    public void updateCompletionTimeFIFO() { 
        String fetchJobsSql = "SELECT jobID, timestamp, jobDuration FROM Job ORDER BY timestamp ASC"; 
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD); 
        PreparedStatement fetchPstmt = conn.prepareStatement(fetchJobsSql); 
        ResultSet rs = fetchPstmt.executeQuery()) { 
            int totalProcessingTime = 0; // Accumulator for total processing time 
            StringBuilder completionTimesMessage = new StringBuilder(); 
            completionTimesMessage.append("Completion Times for All Jobs:\n\n"); 
            while (rs.next()) { 
                String jobId = rs.getString("jobID"); 
                Timestamp timestamp = rs.getTimestamp("timestamp"); 
                Time jobDuration = rs.getTime("jobDuration"); // Convert jobDuration to minutes 
                int durationMinutes = jobDuration.toLocalTime().toSecondOfDay() / 60; 
                // Calculate completion time as integer (total processing time so far + duration) 
                totalProcessingTime += durationMinutes; 
                // Update the job with the calculated completion time 
                updateJobCompletionTime(jobId, totalProcessingTime); 
                // Append job completion time to the message 
                completionTimesMessage.append("Job ID: ").append(jobId) .append(" - Completion Time: ").append(totalProcessingTime).append(" minutes\n"); 
            } // Show the completion times in a popup dialog 
            JOptionPane.showMessageDialog(null, completionTimesMessage.toString(), "Completion Times", JOptionPane.INFORMATION_MESSAGE); 
        } catch (SQLException e) { 
            e.printStackTrace(); System.out.println("Error updating completion times for all jobs."); 
        } 
    }
    
    private void updateJobCompletionTime(String jobId, int totalProcessingTime) {
        String sql = "UPDATE Job SET completionTime = ? WHERE jobID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, totalProcessingTime);
            pstmt.setString(2, jobId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating completion time for jobID: " + jobId);
        }
    }
    


    private void updateCompletionTime(String jobId) { 
        // SQL to calculate and update completionTime as TIMESTAMP 
        String sql = "UPDATE Job SET completionTime = ADDTIME(timestamp, jobDuration) WHERE jobID = ?"; 
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD); 
        PreparedStatement pstmt = conn.prepareStatement(sql)) { 
           // System.out.println("Running SQL update: " + sql + " with jobID: " + jobId); 
            pstmt.setString(1, jobId); 
            int affectedRows = pstmt.executeUpdate(); 
            if (affectedRows > 0) { 
                System.out.println("Completion time updated successfully for jobID: " + jobId); 
                String verifySql = "SELECT timestamp, jobDuration, completionTime " + "FROM Job WHERE jobID = ?"; 
                try (PreparedStatement verifyPstmt = conn.prepareStatement(verifySql)) { 
                    verifyPstmt.setString(1, jobId); 
                    try (ResultSet rs = verifyPstmt.executeQuery()) { 
                        if (rs.next()) { 
                            Timestamp timestamp = rs.getTimestamp("timestamp"); 
                            String jobDuration = rs.getString("jobDuration"); 
                            Timestamp completionTime = rs.getTimestamp("completionTime"); 
                         //   System.out.println("Verification: timestamp=" + timestamp + ", jobDuration=" + jobDuration + ", completionTime=" + completionTime); 
                        } else { 
                            System.out.println("No data found for jobID: " + jobId); 
                    } 
                } 
            } 
        } else { 
            System.out.println("No rows updated. Check if jobID exists: " + jobId); 
        } 
    } catch (SQLException e) { 
        e.printStackTrace(); 
    System.out.println("Error updating completion time for jobID: " + jobId);
    }
}
public void updateAllCompletionTimes() { 
    String fetchJobsSql = "SELECT jobID FROM Job"; 
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD); 
    PreparedStatement fetchPstmt = conn.prepareStatement(fetchJobsSql); 
    ResultSet rs = fetchPstmt.executeQuery()) { 
        while (rs.next()) { 
            String jobId = rs.getString("jobID"); 
            updateCompletionTime(jobId); } // Fetch and display updated completion times 
            String fetchCompletionTimesSql = "SELECT jobID, completionTime FROM Job"; 
            try (PreparedStatement pstmt = conn.prepareStatement(fetchCompletionTimesSql); 
            ResultSet completionTimesRs = pstmt.executeQuery()) { 
                StringBuilder message = new StringBuilder(); 
                message.append("Completion Times for All Jobs:\n\n"); 
                while (completionTimesRs.next()) { 
                    String jobId = completionTimesRs.getString("jobID"); 
                    Timestamp completionTime = completionTimesRs.getTimestamp("completionTime"); 
                    message.append("Job ID: ").append(jobId).append(" - Completion Time: ").append(completionTime).append("\n"); } 
                    // Show the completion times in a popup dialog 
                    JOptionPane.showMessageDialog(null, message.toString(), "Completion Times", JOptionPane.INFORMATION_MESSAGE); 
                } 
            } catch (SQLException e) { 
                e.printStackTrace(); 
                System.out.println("Error updating completion times for all jobs."); 
        } 
}

    
    public static void main(String[] args) {
        String inputUsername = "Lee Everett"; // Example username
        String inputPassword = "password123"; // Example password
        Authentication auth =  new Authentication();
        boolean isAuthenticated = auth.authenticateVehicleOwner(inputUsername, inputPassword);
        auth.updateCompletionTime("4");
        if (isAuthenticated) {
            System.out.println("Authentication successful!");
        } else {
            System.out.println("Authentication failed. Please check your username and password.");
        }
        String username = "Lamar"; String email = "jane.doe@example.com"; String password = "securepassword123"; 
        String subPlan = "Premium"; String address = "456 Market St"; String state = "CA"; String country = "USA"; 
        String phoneNumber = "0987654321"; // Register the job submitter 
    //     boolean success = auth.registerJobSubmitter(username, email, password, address, state, country, phoneNumber);
    //       if (success) { 
    //         System.out.println("Vehicle owner registered successfully."); 
    //     } else { 
    //         System.out.println("Failed to register vehicle owner.");
    // }
}
}
