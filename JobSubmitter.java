import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JobSubmitter extends User {

private List <Job> JobList;
private String subscriptionPlan;
private String paymentAccount;
private CloudController cloudController;
   

    public JobSubmitter(String userId, String email, String username, String name, String password, double balance, String paymentMethod,
    List<Job> JobList, String subscriptionPlan, String paymentAccount) {
        super(userId,email, username,name,password,balance,paymentMethod);
        this.JobList = JobList;
        this.subscriptionPlan = subscriptionPlan;
        this.paymentAccount = paymentAccount;

    }
    public void setCloudController(CloudController cloudController) {
         this.cloudController = cloudController; 
        }

    public List<Job> getJobList() {
        return JobList;
    }
    public void setJobList(List<Job> JobList) {
        this.JobList = JobList;
    }
    public String getSubscriptionPlan() {
        return subscriptionPlan;
    }
    public void setJobList(String subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }
    public String getPaymentAccount() {
        return paymentAccount;
    }
    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
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
            writer.write("Duration: " + job.getDuration() + "\n");
            writer.write("Arrival Time: " + job.getArrivalTime() + "\n");
            writer.write("Computational Power Needed: " + job.getComputationalPowerNeeded() + "\n");
            writer.write("Status: " + job.getStatus() + "\n");
            writer.write("------------------------\n");
            writer.close();

        } catch (IOException ex) {
            System.err.println("Error saving job information: " + ex.getMessage());
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