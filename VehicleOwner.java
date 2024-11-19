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

    public void registerVehicle(Vehicle v) { // NEEDS TO BE IMPLEMENTED and stored in a file
        vehicleList.add(new Vehicle());
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