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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

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
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Get all pending jobs ordered by submission time
            String sql = "SELECT jobID, jobDuration FROM Job WHERE status = 'Pending' ORDER BY submissionTime";
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                
                int totalTime = 0;
                
                while (rs.next()) {
                    String jobId = rs.getString("jobID");
                    int duration = rs.getInt("jobDuration");
                    totalTime += duration;
                    
                    // Update job with estimated completion time
                    String updateSql = "UPDATE Job SET estimatedCompletionTime = ?, status = 'Scheduled' WHERE jobID = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, totalTime);
                        updateStmt.setString(2, jobId);
                        updateStmt.executeUpdate();
                    }
                }
                
                JOptionPane.showMessageDialog(null, 
                    "Job completion times have been calculated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                    
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error calculating completion times: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void assignJob(Job job) {
        List<Vehicle> availableVehicleList = vehicleList.stream()
            .filter(Vehicle::isAvailable)
            .collect(Collectors.toList());
            
        if (!availableVehicleList.isEmpty()) {
            Vehicle vehicle = availableVehicleList.get(0);
            vehicle.startJob(job);
            vehicle.setAvailable(false);
            job.setStatus("Running");
        }
    }
    public static void handleCollectedValues(String ownerId, String vehicleInfo, String residencyTime, String computationalPower, String notes) { 
        System.out.println("Owner ID: " + ownerId); 
        System.out.println("Vehicle Info: " + vehicleInfo); 
        System.out.println("Residency Time: " + residencyTime); 
        System.out.println("Computational Power: " + computationalPower); 
        System.out.println("Notes: " + notes); 
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
        //Implement this later
    }
    public void scheduleJobs() {
        //Implement this later
    }
    public void monitorResources() { // Dont know if we need this
        //Implement this later
    }
    public void handleVehicleDeparture(Vehicle v) {
        //Implement this later
    }
    public void generateReports() {
        //Implement this later

    }
    public void assignCheckpoint(Vehicle v) {
        //Implement this later

    }
    public void chargeUser(double amount) {
       //System.out.println(getUserId() + amount);
       //Implement this later
    }
    public void payOwner(double amount) {
        //System.out.println(getUserId() + amount);
        //Implement this later
    }
    public void returnReceipt(int transactioID) {
        //Implement this later
    }
    public void allocateResources(Job j) {
        //Implement this later
    }
    public void redistributeJob(Job j) {
        //Implement this later
    }
    public void monitorVehicles() {
        //Implement this later
    }

    public void submitJob(Job job) {
        job.setStatus("Pending");
        jobQueue.offer(job);
        writeJobToFile(job);
    }

    private void writeJobToFile(Job job) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jobs/submitted_jobs.txt", true))) {
            writer.write("JobID: " + job.getJobId() + "\n");
            writer.write("Status: Pending\n");
            // Add other job details as needed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean processJobSubmission(Job job, JobSubmitter client) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // First show popup to accept/reject
            int response = JOptionPane.showOptionDialog(null,
                "New Job Submission Request:\n" +
                "Client ID: " + client.getUserId() + "\n" +
                "Job Duration: " + job.getDuration() + "\n" +
                "Purpose: " + job.getPurpose(),
                "Job Submission Request",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Accept", "Reject"},
                "Accept");

            if (response == JOptionPane.YES_OPTION) {
                String sql = "INSERT INTO Job (jobID, clientID, jobDuration, jobDeadline, purpose, status) VALUES (?, ?, ?, ?, ?, 'Pending')";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, job.getJobId());
                    pstmt.setString(2, client.getUserId());
                    pstmt.setInt(3, job.getDuration());
                    pstmt.setDate(4, java.sql.Date.valueOf(job.getDeadline()));
                    pstmt.setString(5, job.getPurpose());
                    pstmt.executeUpdate();
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean processVehicleRegistration(Vehicle vehicle, VehicleOwner owner) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            int response = JOptionPane.showOptionDialog(null,
                "New Vehicle Registration Request:\n" +
                "Owner ID: " + owner.getUserId() + "\n" +
                "VIN: " + vehicle.getVIN() + "\n" +
                "Computational Power: " + vehicle.getComputationalPower(),
                "Vehicle Registration Request",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Accept", "Reject"},
                "Accept");

            if (response == JOptionPane.YES_OPTION) {
                String sql = "INSERT INTO Vehicle (VIN, ownerID, make, model, year, compPower, storageCapacity, status) VALUES (?, ?, ?, ?, ?, ?, ?, 'Available')";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, vehicle.getVIN());
                    pstmt.setString(2, owner.getUserId());
                    pstmt.setString(3, vehicle.getMake());
                    pstmt.setString(4, vehicle.getModel());
                    pstmt.setInt(5, vehicle.getYear());
                    pstmt.setDouble(6, vehicle.getComputationalPower());
                    pstmt.setDouble(7, vehicle.getStorageCapacity());
                    pstmt.executeUpdate();
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
