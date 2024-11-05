import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.io.File;

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

    public void registerVehicle(Vehicle v) { // NEEDS TO BE IMPLEMENTED and stored in a file
        vehicleList.add(new Vehicle());
        writeVehicleToFile(v);
    }

    private void writeVehicleToFile(Vehicle v) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehicles.txt", true))) {
            writer.write("Vehicle ID: " + v.getVehicleID() + "\n");
            writer.write("Vehicle Model: " + v.getModel() + "\n");
            writer.write("Vehicle Year: " + v.getYear() + "\n");
            writer.write("Vehicle Owner: " + v.getOwner() + "\n");
            writer.write("-------------------------------\n");
            System.out.println("Vehicle information written to: " + new File("vehicles.txt").getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void specifyResources() {

    }
    public void adjustResources() {

    }
    public void viewIncome() {
        
    }

}
