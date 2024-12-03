import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JobSubmitter extends User {

    private ArrayList<Job> submittedJobs;

    public JobSubmitter(String userId, String name, String email, String userType, 
                       String address, double balance, String phoneNumber, 
                       ArrayList<Job> submittedJobs, String password) {
        super(userId, name, email, userType, address, balance, phoneNumber, password);
        this.submittedJobs = submittedJobs;
    }

    public ArrayList<Job> getSubmittedJobs() {
        return submittedJobs;
    }

    public void submitJob(Job job) {
        // Add job to list
        JobList.add(job);
        
        try {
            // Create jobs directory if it doesn't exist
            File directory = new File("jobs");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Add timestamp
            String timestamp = java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Write to file using FileWriter
            FileWriter writer = new FileWriter("jobs/submitted_jobs.txt", true);
            writer.write("Timestamp: " + timestamp + "\n");
            writer.write("Client ID: " + this.getUserId() + "\n");
            writer.write("Job ID: " + job.getJobId() + "\n");
            writer.write("Duration: " + job.getDuration() + " minutes" + "\n");
            writer.write("Computational Power Needed: " + job.getComputationalPowerNeeded() + "\n");
            writer.write("Status: " + job.getStatus() + "\n");
            writer.write("------------------------\n");
            writer.close();

        } catch (IOException ex) {
            System.err.println("Error saving job information: " + ex.getMessage());
        }
    }
    public void cancelJob() {

    }

    public void checkStatus() {

    }
    public void viewHistory() {

    }

}