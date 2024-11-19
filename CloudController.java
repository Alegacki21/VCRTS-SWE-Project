import java.util.*;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
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
        System.out.println("Calculate method called");
        try {
            File jobsFile = new File("jobs/submitted_jobs.txt");
            System.out.println("Looking for file at: " + jobsFile.getAbsolutePath());
            
            if (!jobsFile.exists()) {
                System.out.println("File does not exist!");
                return;
            }
            
            List<String> lines = Files.readAllLines(jobsFile.toPath());
            System.out.println("Read " + lines.size() + " lines");
            
            // Print each line for debugging
            System.out.println("File contents:");
            for (String line : lines) {
                System.out.println("Line: " + line);
            }
            
            // Check if all jobs are already scheduled
            boolean allScheduled = true;
            boolean hasJobs = false;
            
            for (String line : lines) {
                if (line.startsWith("Job ID:")) {
                    hasJobs = true;
                    System.out.println("Found job: " + line);
                }
                if (line.startsWith("Status:") && !line.contains("Scheduled")) {
                    allScheduled = false;
                    System.out.println("Found unscheduled job");
                }
            }
            
            System.out.println("Has jobs: " + hasJobs);
            System.out.println("All scheduled: " + allScheduled);
            
            if (!hasJobs) {
                JOptionPane.showMessageDialog(null, 
                    "No jobs found to calculate.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            if (allScheduled) {
                JOptionPane.showMessageDialog(null, 
                    "All jobs have already been scheduled and calculated.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            List<String> updatedLines = new ArrayList<>();
            int totalWaitTime = 0;
            boolean processingJob = false;
            
            for (String line : lines) {
                if (line.startsWith("Job ID:")) {
                    processingJob = true;
                    updatedLines.add(line);
                    System.out.println("Processing job: " + line);
                    int processingTime = 5;
                    int completionTime = totalWaitTime + processingTime;
                    totalWaitTime = completionTime;
                    continue;
                }
                
                if (line.equals("------------------------")) {
                    processingJob = false;
                    updatedLines.add("Estimated Completion Time: " + totalWaitTime + " minutes");
                    updatedLines.add("Status: Scheduled");
                    updatedLines.add(line);
                    continue;
                }
                
                if (!processingJob || (!line.startsWith("Estimated") && !line.startsWith("Status:"))) {
                    updatedLines.add(line);
                }
            }
            
            Files.write(jobsFile.toPath(), updatedLines);
            System.out.println("File updated with new times");
            
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
}
