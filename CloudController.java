import java.util.*;
import java.time.Duration;

public class CloudController extends User {
private int redundancyLevel;
private List <Vehicle> vehicleList;
private Queue<Job> jobQueue;
//Removed resourcemanagement and billing
// private dashboard NOT IMPLEMENTED YET
// private authentication NOT IMPLEMENTED YET
private int availableVehicles;
private double availableCPUPower;

    public CloudController(String userId, String email, String username, String name, String password, double balance, String paymentMethod,
    int redundancyLevel,List <Vehicle> vehicleList, Queue<Job> jobQueue, int availableVehicles, double availableCPUPower) {
        super(userId,email,username,name,password,balance,paymentMethod);
        this.redundancyLevel = redundancyLevel;
        this.vehicleList = vehicleList;
        this.jobQueue = jobQueue;
        this.availableVehicles = availableVehicles;
        this.availableCPUPower = availableCPUPower;

    }
    public int getRedundancyLevel() {
        return redundancyLevel;
    }
    public void setRedundancyLevel(int redundancyLevel) {
        this.redundancyLevel = redundancyLevel;
    }
    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }
    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
    public Queue<Job> getJobQueue() {
        return jobQueue;
    }
    public void setJobQueue(Queue<Job> jobQueue) {
        this.jobQueue = jobQueue;
    }
    public int getAvailableVehicles() {
        return availableVehicles;
    }
    public void setAvailableVehicles(int availableVehicles) {
        this.availableVehicles = availableVehicles;
    }
    public double getAvailableCPUPower() {
        return availableCPUPower;
    }
    public void setAvailableCPUPower(double availableCPUPower) {
        this.availableCPUPower = availableCPUPower;
    }

    public void calculateCompletionTime() {
        // Sort jobs based on arrival time
        List<Job> jobList = new ArrayList<>(jobQueue);
        jobList.sort(Comparator.comparingInt(Job::getArrivalTime));

        int currentTime = 0;
        for (Job job : jobList) {
            if (currentTime < job.getArrivalTime()) {
                currentTime = job.getArrivalTime();
            }
            currentTime += job.getDuration();
            job.setCompletionTime(currentTime);
        }

        // Print the completion times
        for (Job job : jobList) {
            System.out.println("Job with arrival time " + job.getArrivalTime() + " has completion time " + job.getCompletionTime());
        }
    }

    public void assignJob(int numVehicles) {

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
        System.out.println(getUserId() + amount);
    }
    public void payOwner(double amount) {
        System.out.println(getUserId() + amount);
    }
    public void returnReceipt(int transactioID) {

    }
    public void allocateResources(Job j) {

    }
    public void redistributeJob(Job j) {

    }
    public void monitorVehicles() {

    }
}