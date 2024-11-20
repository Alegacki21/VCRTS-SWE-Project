import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        super(userId, email, username, name, password, balance, paymentMethod);
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
    public static void vehicleServerResponse(JPanel ownerPanel) {
        JOptionPane optionPane = new JOptionPane("Please wait for the server response...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
                    JDialog dialog = optionPane.createDialog(ownerPanel, "Processing"); 
                  //  dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); //COMMENTED OUT FOR TESTING PURPOSES
                    dialog.setVisible(true);
    }


    public void registerVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);
        
        try {
            File directory = new File("resources");
            if (!directory.exists()) {
                directory.mkdir();
            }

            String timestamp = java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            FileWriter writer = new FileWriter("resources/vehicle_resources.txt", true);
            writer.write("Timestamp: " + timestamp + "\n");
            writer.write("Owner ID: " + this.getUserId() + "\n");
            writer.write("VIN: " + vehicle.getVIN() + "\n");
            writer.write("Make: " + vehicle.getMake() + "\n");
            writer.write("Model: " + vehicle.getModel() + "\n");
            writer.write("Year: " + vehicle.getYear() + "\n");
            writer.write("Computational Power: " + vehicle.getComputationalPower() + "\n");
            writer.write("Storage Capacity: " + vehicle.getStorageCapacity() + "\n");
            writer.write("------------------------\n");
            writer.close();
            
            // Debug logging
            System.out.println("Writing vehicle to file:");
            System.out.println("Owner ID: " + this.getUserId());
            System.out.println("VIN: " + vehicle.getVIN());
        } catch (IOException ex) {
            System.err.println("Error saving vehicle information: " + ex.getMessage());
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