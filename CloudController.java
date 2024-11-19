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
import java.io.*;
import java.net.*;

public class CloudController {
    private static CloudController instance;
    private List<Vehicle> vehicleList = new ArrayList<>();
    private Queue<Job> jobQueue = new LinkedList<>();
    private ServerSocket serverSocket;
    private boolean acceptData = false;

    // Private constructor to ensure singleton pattern 
    private CloudController() {}

    public static CloudController getInstance() {
        if (instance == null) {
            instance = new CloudController();
        }
        return instance;
    }

    public CloudController(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void startServer() {
        new Thread(() -> {
            try {
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void handleClient(Socket clientSocket) {
        new Thread(() -> {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String data = in.readLine();
                System.out.println("Received data: " + data);

                // Show a dialog to accept or reject the data
                int result = JOptionPane.showConfirmDialog(null, "Accept data: " + data, "Authorize Data", JOptionPane.YES_NO_OPTION);
                acceptData = (result == JOptionPane.YES_OPTION);

                if (acceptData) {
                    // Store the data
                    storeData(data);
                    out.println("Request accepted");
                } else {
                    out.println("Request rejected");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void storeData(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("authorized_data.txt", true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static void main(String[] args) throws IOException {
        CloudController server = new CloudController(12345);
        server.startServer();

        // Existing code...
        try {
            File jobsFile = new File("jobs/submitted_jobs.txt");

            if (!jobsFile.exists()) {
                System.out.println("File does not exist!");
                return;
            }

            List<String> lines = Files.readAllLines(jobsFile.toPath());
            List<String> updatedLines = new ArrayList<>();

            int totalTime = 0;  // Running total of completion times
            Job currentJob = null;

            for (String line : lines) {
                if (line.startsWith("Job ID:")) {
                    currentJob = new Job(
                        line.split(":")[1].trim(),  // Job ID
                        0,  // Duration will be set when we find it
                        0   // Arrival time not needed for this calculation
                    );
                    updatedLines.add(line);
                } else if (line.startsWith("Duration:")) {
                    if (currentJob != null) {
                        // Existing logic...
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}