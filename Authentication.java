import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Authentication {
    
    public static User login(String userId, String password, String userType) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM User WHERE userID = ? AND password = ? AND userType = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, userId);
                pstmt.setString(2, password);
                pstmt.setString(3, userType);
                
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    if (userType.equals("Owner")) {
                        return new VehicleOwner(
                            rs.getString("userID"),
                            rs.getString("email"),
                            rs.getString("name"),
                            rs.getString("name"),
                            rs.getString("password"),
                            rs.getDouble("balance"),
                            rs.getString("phoneNumber"),
                            new ArrayList<>(),
                            ""
                        );
                    } else if (userType.equals("Client")) {
                        return new JobSubmitter(
                            rs.getString("userID"),
                            rs.getString("name"),
                            rs.getString("email"),
                            userType,
                            rs.getString("address"),
                            rs.getDouble("balance"),
                            rs.getString("phoneNumber"),
                            new ArrayList<>(),
                            rs.getString("password")
                        );
                    }
                }
            }
        }
        return null;
    }
    
    public static boolean registerUser(User user) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO User (userID, name, email, userType, address, balance, phoneNumber, password) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user instanceof VehicleOwner ? "Owner" : "Client");
                //pstmt.setString(5, user.getAddress());
                pstmt.setDouble(6, user.getBalance());
               // pstmt.setString(7, user.getPhoneNumber());
                pstmt.setString(8, user.getPassword());
                
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
        }
    }
    
    public static boolean isUserIdAvailable(String userId) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM User WHERE userID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, userId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }
        }
        return false;
    }
}
