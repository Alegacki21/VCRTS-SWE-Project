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


    public void submitJob() { // NEEDS TO BE IMPLEMENTED and info stored in file

    }
    public void cancelJob() {

    }
    public void checkStatus() {

    }
    public void viewHistory() {

    }

}