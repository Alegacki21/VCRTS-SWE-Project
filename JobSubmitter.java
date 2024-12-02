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
        try (Connection conn = DatabaseConnection.getConnection()) {
            // First add to submittedJobs in memory
            submittedJobs.add(job);
            
            // Then insert into database
            String sql = "INSERT INTO Job (jobID, clientID, jobDuration, jobDeadline, purpose, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                        
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, job.getJobId());
                pstmt.setString(2, this.getUserId());
                pstmt.setInt(3, job.getDuration());
                pstmt.setDate(4, java.sql.Date.valueOf(job.getDeadline()));
                pstmt.setString(5, job.getPurpose());
                pstmt.setString(6, job.getStatus());
                
                pstmt.executeUpdate();
                System.out.println("Job submitted to database successfully");
            }
        } catch (SQLException ex) {
            System.err.println("Database error submitting job: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public static void jobServerResponse(JPanel clientPanel) {
        JOptionPane optionPane = new JOptionPane("Please wait for the server response...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        JDialog dialog = optionPane.createDialog(clientPanel, "Processing"); 
        //  dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); //COMMENTED OUT FOR TESTING PURPOSES
        dialog.setVisible(true);
    }
    public void cancelJob() {

    }

    public void checkStatus() {

    }
    public void viewHistory() {

    }

}