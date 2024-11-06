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
    
    
    public static CloudController getInstance() {
        if (instance == null) {
            instance = new CloudController();
        }
        return instance;
    }
    
    public void calculateCompletionTime() {
        try {
            File jobsFile = new File("jobs/submitted_jobs.txt");
            List<String> lines = Files.readAllLines(jobsFile.toPath());
            List<String> updatedLines = new ArrayList<>();
            
            for (String line : lines) {
                updatedLines.add(line);
                if (line.startsWith("JobID:")) {
                    String jobId = line.substring(line.indexOf(":") + 2).trim();
                    int completionTime = new Random().nextInt(10) + 1;
                    updatedLines.add("Estimated Completion Time: " + completionTime + " minutes");
                    updatedLines.add("Status: In Progress");
                }
            }
            
            Files.write(jobsFile.toPath(), updatedLines);
            
        } catch (IOException e) {
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
