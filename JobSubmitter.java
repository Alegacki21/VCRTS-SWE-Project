import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JobSubmitter extends User {

private List <Job> JobList;
private String subscriptionPlan;
private String paymentAccount;

    public JobSubmitter(String userId, String email, String username, String name, String password, double balance, String paymentMethod,
    List<Job> JobList, String subscriptionPlan, String paymentAccount) {
        super(userId,email, username,name,password,balance,paymentMethod);
        this.JobList = JobList;
        this.subscriptionPlan = subscriptionPlan;
        this.paymentAccount = paymentAccount;

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