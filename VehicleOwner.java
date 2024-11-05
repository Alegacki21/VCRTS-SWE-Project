import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
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

    //Add vehicle to List - vehicleList
    //Param - Vehicle object

    public void registerVehicle(Vehicle v) {
        vehicleList.add(v);
        saveVehicle(v);
    }

    //Write vehicle info to file 
    //Param - vehicle object

    private void saveVehicle(Vehicle vehicle) {
        String fileName = "registered_vehicles.txt"; 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) { // Append mode
            writer.write(formatVehicleData(vehicle));
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving vehicle to file: " + e.getMessage());
        }
    }
    private String formatVehicleData(Vehicle vehicle) {
        return String.join(",",
                vehicle.getName(),
                vehicle.getVehicleID(),
                vehicle.getModel(),
                Integer.toString(vehicle.getYear()),
                vehicle.getMake(),
                vehicle.getVIN(),
                Double.toString(vehicle.getComputationalPower()),
                Double.toString(vehicle.getStorageCapacity()),
                vehicle.getStatus(),
                vehicle.getLocation(),
                vehicle.getArrivalTime().toString(),
                vehicle.getDepartureTime().toString(),
                Boolean.toString(vehicle.isAvailable())
        );
    }
    public void specifyResources() {

    }
    public void adjustResources() {

    }
    public void viewIncome() {
        
    }

}