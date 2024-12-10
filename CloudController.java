<<<<<<< HEAD
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
=======
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
>>>>>>> 3b734e0 (Add completion time and DB functionality to ClientGUI and ServerGUI)
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CloudController {
    private static CloudController instance;
    private List<Vehicle> vehicleList = new ArrayList<>();
    private Queue<Job> jobQueue = new LinkedList<>();
<<<<<<< HEAD

    // Private constructor to ensure singleton pattern
    private CloudController() {
    }

=======
    private Authentication auth = new Authentication();
    
    // Private constructor to ensure singleton pattern 
    private CloudController() {}
    
>>>>>>> 3b734e0 (Add completion time and DB functionality to ClientGUI and ServerGUI)
    public static CloudController getInstance() {
        if (instance == null) {
            instance = new CloudController();
        }
        return instance;
    }

    public void calculateCompletionTime() {
        try {
            File jobsFile = new File("jobs/submitted_jobs.txt");

            if (!jobsFile.exists()) {
                System.out.println("File does not exist!");
                return;
            }

            List<String> lines = Files.readAllLines(jobsFile.toPath());
            List<String> updatedLines = new ArrayList<>();

            int totalTime = 0; // Running total of completion times
            Job currentJob = null;

            for (String line : lines) {
                if (line.startsWith("Job ID:")) {
                    currentJob = new Job(
                            line.split(":")[1].trim(), // Job ID
                            "userID", // Placeholder for userID
                            0, // Duration will be set when we find it
                            "jobDeadline", // Placeholder for jobDeadline
                            0, // Placeholder for purpose
                            "Pending" // Initial status
                    );
                    updatedLines.add(line);
                } else if (line.startsWith("Duration:")) {
                    if (currentJob != null) {
                        int duration = Integer.parseInt(line.split(":")[1].trim().split(" ")[0]);
                        currentJob.setDuration(duration);

                        // Calculate completion time based on FIFO
                        totalTime += duration;
<<<<<<< HEAD

=======
                        currentJob.setCompletionTime(totalTime);
                        
>>>>>>> 3b734e0 (Add completion time and DB functionality to ClientGUI and ServerGUI)
                        updatedLines.add(line);
                        updatedLines.add("Estimated Completion Time: " + totalTime + " minutes");
                        updatedLines.add("Status: Scheduled");

                        // Update the completion time in the database
                        updateCompletionTime(currentJob.getJobId(), totalTime);
                    }
                } else if (!line.startsWith("Estimated") && !line.startsWith("Status:")) {
                    updatedLines.add(line);
                }

                if (line.equals("------------------------")) {
                    currentJob = null;
                    updatedLines.add(line);
                }
            }

            Files.write(jobsFile.toPath(), updatedLines);

            JOptionPane.showMessageDialog(null,
                    "Job completion times have been calculated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error calculating completion times: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCompletionTime(String jobId, int completionTime) {
        String sql = "UPDATE Job SET completionTime = ? WHERE jobID = ?";
        try (Connection conn = DriverManager.getConnection(auth.url, auth.sqlUsername, auth.password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setTime(1, Time.valueOf(String.format("%02d:%02d:00", completionTime / 60, completionTime % 60)));
            pstmt.setString(2, jobId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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

    public static void handleCollectedValues(String ownerId, String vehicleInfo, String residencyTime,
            String computationalPower, String notes) {
        System.out.println("Owner ID: " + ownerId);
        System.out.println("Vehicle Info: " + vehicleInfo);
        System.out.println("Residency Time: " + residencyTime);
        System.out.println("Computational Power: " + computationalPower);
        System.out.println("Notes: " + notes);
    }

    public static void showPopup(JPanel parentFrame, String ID, String vehicleInformation, String time, String power,
            String requestDetails) {
        String message = "UserID  " + ID + "\n" + "Wants to register:  " + vehicleInformation + "\n"
                + "Approximate Residency Time:  "
                + time + "\n" + "Available Computational Power:  " + power + "\n" + "Notes:  " + requestDetails;
        int response = JOptionPane.showOptionDialog(null, message, "User Request", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, new String[] { "Accept", "Reject" }, "Accept");
        if (response == JOptionPane.YES_OPTION) { // Handle acceptance logic here
            System.out.println("Request Accepted");
        } else if (response == JOptionPane.NO_OPTION) {
            System.out.println("Request Rejected");

        }
    }

    public void submitJob(Job job) {
        job.setStatus("Pending");
        jobQueue.offer(job);
        insertJobToDatabase(job);
    }

    private void insertJobToDatabase(Job job) {
        String sql = "INSERT INTO Job (jobID, userID, jobDuration, jobDeadline, purpose, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, job.getJobId());
            pstmt.setString(2, job.getUserId());
            pstmt.setInt(3, job.getDuration());
            pstmt.setDate(4, java.sql.Date.valueOf(job.getJobDeadline()));
            pstmt.setString(5, job.getPurpose());
            pstmt.setString(6, job.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateJobStatus(Job job) {
        String sql = "UPDATE Job SET status = ? WHERE jobID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, job.getStatus());
            pstmt.setString(2, job.getJobId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertVehicleOwner(VehicleOwner owner) {
        String sql = "INSERT INTO VehicleOwner (ownerID, fullName, email, username, password, balance, paymentMethod, paymentAccount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, owner.getUserId());
            pstmt.setString(2, owner.getName());
            pstmt.setString(3, owner.getEmail());
            pstmt.setString(4, owner.getUsername());
            pstmt.setString(5, owner.getPassword());
            pstmt.setDouble(6, owner.getBalance());
            pstmt.setString(7, owner.getPaymentMethod());
            pstmt.setString(8, owner.getPaymentAccount());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertJobSubmitter(JobSubmitter submitter) {
        String sql = "INSERT INTO JobSubmitter (clientID, fullName, email, username, password, balance, paymentMethod, subscriptionPlan, paymentAccount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, submitter.getUserId());
            pstmt.setString(2, submitter.getName());
            pstmt.setString(3, submitter.getEmail());
            pstmt.setString(4, submitter.getUsername());
            pstmt.setString(5, submitter.getPassword());
            pstmt.setDouble(6, submitter.getBalance());
            pstmt.setString(7, submitter.getPaymentMethod());
            pstmt.setString(8, submitter.getSubscriptionPlan());
            pstmt.setString(9, submitter.getPaymentAccount());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO Vehicle (VIN, ownerID, model, make, year, computationalPower, storageCapacity, status, location, arrivalTime, departureTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vehicle.getVIN());
            pstmt.setString(2, vehicle.getOwner().getUserId());
            pstmt.setString(3, vehicle.getModel());
            pstmt.setString(4, vehicle.getMake());
            pstmt.setInt(5, vehicle.getYear());
            pstmt.setDouble(6, vehicle.getComputationalPower());
            pstmt.setDouble(7, vehicle.getStorageCapacity());
            pstmt.setString(8, vehicle.getStatus());
            pstmt.setString(9, vehicle.getLocation());
            pstmt.setTime(10, java.sql.Time.valueOf(vehicle.getArrivalTime()));
            pstmt.setTime(11, java.sql.Time.valueOf(vehicle.getDepartureTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void authenticateUser(User u) {
        // Implement this later
    }

    public void scheduleJobs() {
        // Implement this later
    }

    public void monitorResources() { // Dont know if we need this
        // Implement this later
    }

    public void handleVehicleDeparture(Vehicle v) {
        // Implement this later
    }

    public void generateReports() {
        // Implement this later

    }

    public void assignCheckpoint(Vehicle v) {
        // Implement this later

    }

    public void chargeUser(double amount) {
        // System.out.println(getUserId() + amount);
        // Implement this later
    }

    public void payOwner(double amount) {
        // System.out.println(getUserId() + amount);
        // Implement this later
    }

    public void returnReceipt(int transactioID) {
        // Implement this later
    }

    public void allocateResources(Job j) {
        // Implement this later
    }

    public void redistributeJob(Job j) {
        // Implement this later
    }

    public void monitorVehicles() {
        // Implement this later
    }
}
