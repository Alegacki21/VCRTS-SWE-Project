import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    private String userId;
    private String name;
    private String email;
    private String userType;
    private String address;
    private double balance;
    private String phoneNumber;
    private String password;

    public User(String userId, String name, String email, String userType, 
                String address, double balance, String phoneNumber, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.address = address;
        this.balance = balance;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    // Getters and Setters
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getUserType() { return userType; }
    public String getAddress() { return address; }
    public double getBalance() { return balance; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPassword() { return password; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
    public void setBalance(double balance) { this.balance = balance; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setPassword(String password) { this.password = password; }

    // Database operations
    public boolean updateProfile() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE User SET name = ?, email = ?, address = ?, phoneNumber = ? WHERE userID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, address);
                pstmt.setString(4, phoneNumber);
                pstmt.setString(5, userId);
                return pstmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE User SET password = ? WHERE userID = ? AND password = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, newPassword);
                pstmt.setString(2, userId);
                pstmt.setString(3, oldPassword);
                if (pstmt.executeUpdate() > 0) {
                    this.password = newPassword;
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateBalance(double amount, boolean isDeposit) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE User SET balance = balance " + (isDeposit ? "+" : "-") + " ? WHERE userID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDouble(1, Math.abs(amount));
                pstmt.setString(2, userId);
                if (pstmt.executeUpdate() > 0) {
                    this.balance = isDeposit ? this.balance + amount : this.balance - amount;
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addFunds(double amount) {
        updateBalance(amount, true);
    }

    public void withdrawFunds(double amount) {
        updateBalance(amount, false);
    }
}
