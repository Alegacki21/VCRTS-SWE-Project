import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
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

    //Methods

    //Jobsubmitter submits new job
     public void submitJob( String jobSubmitterID, double storageNeeded, double computationalPowerNeeded, Duration estimatedDuration, String reason) { 
        Job newJob = new Job( jobSubmitterID, storageNeeded, computationalPowerNeeded, estimatedDuration, reason);
        if(cloudController!=null) {
         cloudController.getJobQueue().add(newJob); 
         this.JobList.add(newJob);
         try (BufferedWriter writer = new BufferedWriter(new FileWriter("jobDetails.txt", true))) {
            writer.write("Job ID: " + newJob.getJobID() + ", Job Submitter ID: " + jobSubmitterID + ", Storage Needed: " + storageNeeded + ", Computational Power Needed: " + computationalPowerNeeded + ", Estimated Duration: " + estimatedDuration + "\n"); 
            System.out.println("Successfully wrote job details on text file");
            } catch (IOException e) { 
                e.printStackTrace(); } 
        }
    }
    public void cancelJob() {

    }
    public void checkStatus() {

    }
    public void viewHistory() {

    }

}