import java.util.*;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.nio.file.Files;

public class CloudController {
    private static CloudController instance;
    private List<Vehicle> vehicleList = new ArrayList<>();
    private Queue<Job> jobQueue = new LinkedList<>();
    
    // Private constructor to ensure singleton pattern 
    private CloudController() {}
    
    public static CloudController getInstance() {
        if (instance == null) {
            instance = new CloudController();
        }
        return instance;
    }

    public void calculateCompletionTime() {
        // Sort jobs based on arrival time
        List<Job> jobList = new ArrayList<>(jobQueue);
        jobList.sort(Comparator.comparingInt(Job::getArrivalTime));

        int currentTime = 0;
        for (Job job : jobList) {
            if (currentTime < job.getArrivalTime()) {
                currentTime = job.getArrivalTime();
            }
            currentTime += job.getDuration();
            job.setCompletionTime(currentTime);
        }

        // Print the completion times
        for (Job job : jobList) {
            System.out.println("Job with arrival time " + job.getArrivalTime() + " has completion time " + job.getCompletionTime());
        }
    }

    public static void showPopup(JPanel parentFrame, String ID, String vehicleInformation, String time, String power,  String requestDetails) { 
        String message = "UserID  " +ID +"\n" + "Wants to register:  " + vehicleInformation + "\n" +"Approximate Residency Time:  "
         + time + "\n" + "Available Computational Power:  "+ power + "\n" + "Notes:  " + requestDetails; 
        int response = JOptionPane.showOptionDialog(null, message, "User Request", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Accept", "Reject"}, "Accept"); 
            if (response == JOptionPane.YES_OPTION) { // Handle acceptance logic here 
                System.out.println("Request Accepted"); 
            } 
            else if (response == JOptionPane.NO_OPTION) { 
                System.out.println("Request Rejected");
               
             }
    }
    public void authenticateUser(User u ) {

    }
    public void scheduleJobs() {

    }
    public void monitorResources() { // Dont know if we need this

    }
    public void handleVehicleDeparture(Vehicle v) {

    }
    public void generateReports() {

    }
    public void assignCheckpoint(Vehicle v) {

    }
    public void chargeUser(double amount) {
        System.out.println(getUserId() + amount);
    }
    public void payOwner(double amount) {
        System.out.println(getUserId() + amount);
    }
    public void returnReceipt(int transactioID) {

    }
    public void allocateResources(Job j) {

    }
    public void redistributeJob(Job j) {

    }
    public void monitorVehicles() {

    }
}