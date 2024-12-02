import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class VehicleOwner extends User {
private List<Vehicle> vehicleList;
private String paymentAccount;

    //Constructor

    public VehicleOwner(String userId, String email, String username, String name, String password, double balance, 
    String paymentMethod, List<Vehicle> vehicleList, String paymentAccount) {
        super(userId, name, email, "Owner",  // userType is "Owner"
              "",  // address
              balance, 
              paymentMethod,  // using paymentMethod as phoneNumber
              password);
        this.vehicleList = vehicleList;
        this.paymentAccount = paymentAccount;
    }

    //Getters and Setters

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }
    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
    public String getPaymentAccount() {
        return paymentAccount;
    }
    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    //Methods
    public static JDialog vehicleServerResponse(JPanel ownerPanel) { 
        JOptionPane optionPane = new JOptionPane( "Please wait for the server response...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null ); 
        JDialog dialog = optionPane.createDialog(ownerPanel, "Processing"); 
      //  dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Ensures the dialog does not close until explicitly disposed 
        dialog.setVisible(true); 
        return dialog; 
}


    public void registerVehicle(Vehicle vehicle) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // First add to vehicleList in memory
            vehicleList.add(vehicle);
            
            // Then insert into database
            String sql = "INSERT INTO Vehicle (VIN, ownerID, make, model, year, compPower, storageCapacity) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
                        
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, vehicle.getVIN());
                pstmt.setString(2, this.getUserId());
                pstmt.setString(3, vehicle.getMake());
                pstmt.setString(4, vehicle.getModel());
                pstmt.setInt(5, vehicle.getYear());
                pstmt.setDouble(6, vehicle.getComputationalPower());
                pstmt.setDouble(7, vehicle.getStorageCapacity());
                
                pstmt.executeUpdate();
                System.out.println("Vehicle registered in database successfully");
            }
        } catch (SQLException ex) {
            System.err.println("Database error registering vehicle: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void specifyResources() {
        //need to implement
    }
    public void adjustResources() {
        //need to implement
    }
    public void viewIncome() {
        //need to implement
    }

}