import java.util.List;
public class VehicleOwner extends User {
private List<Vehicle> vehicleList;
private String paymentAccount;

    public VehicleOwner(String userId, String email, String username, String name, String password, double balance, 
    String paymentMethod, List<Vehicle> vehicleList, String paymentAccount) {
        super(userId, email, username, name, password, balance, paymentMethod);
        this.vehicleList = vehicleList;
        this.paymentAccount = paymentAccount;
    }

    public List getVehicleList() {
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

    public void registerVehicle(Vehicle v) { // NEEDS TO BE IMPLEMENTED and stored in a file

    }
    public void specifyResources() {

    }
    public void adjustResources() {

    }
    public void viewIncome() {
        
    }

}