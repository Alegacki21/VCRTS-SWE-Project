import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class VehicleOwner extends User {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private List<Vehicle> vehicleList = new ArrayList<>();
    private String paymentAccount;

    // Constructor
    public VehicleOwner(String serverAddress, int port, String userId, String email, String username, String name, String password, double balance, 
    String paymentMethod, List<Vehicle> vehicleList, String paymentAccount) throws IOException {
        super(userId, email, username, name, password, balance, paymentMethod);
        this.vehicleList = vehicleList;
        this.paymentAccount = paymentAccount;
        socket = new Socket(serverAddress, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    // Getters and Setters
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

    // Methods
    public static void vehicleServerResponse(JPanel ownerPanel) {
        JOptionPane optionPane = new JOptionPane("Please wait for the server response...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        JDialog dialog = optionPane.createDialog(ownerPanel, "Processing");
        dialog.setVisible(true);
    }

    public void registerVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);

        try {
            File directory = new File("resources");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Add timestamp
            String timestamp = LocalDateTime.now().toString();
            String vehicleData = "Vehicle ID: " + vehicle.getVehicleID() + "\n" +
                                 "Model: " + vehicle.getModel() + "\n" +
                                 "Year: " + vehicle.getYear() + "\n" +
                                 "Owner: " + vehicle.getOwner() + "\n" +
                                 "Timestamp: " + timestamp;

            // Send vehicle data to the server
            out.println(vehicleData);

            // Receive server response
            String response = in.readLine();
            System.out.println("Server response: " + response);

            if ("Request accepted".equals(response)) {
                // Store vehicle data locally if accepted
                File vehicleFile = new File("resources/" + vehicle.getVehicleID() + ".txt");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(vehicleFile))) {
                    writer.write(vehicleData);
                }
            } else {
                System.out.println("Vehicle registration rejected by server.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void specifyResources() {
        // need to implement
    }

    public void adjustResources() {
        // need to implement
    }

    public void viewIncome() {
        // need to implement
    }

   // public static void main(String[] args) {
     //   try {
      //      VehicleOwner vehicleOwner = new VehicleOwner("localhost", 12345, "userId", "email", "username", "name", "password", 0.0, "paymentMethod", new ArrayList<>(), "paymentAccount");
        //    Vehicle vehicle = new Vehicle("1", "Model X", 2021, "John Doe"); // Example vehicle
          //  vehicleOwner.registerVehicle(vehicle);
       // } catch (IOException e) {
         //   e.printStackTrace();
       // }
    //}
}
