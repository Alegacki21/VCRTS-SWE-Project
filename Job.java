import java.time.Duration;

public class Job {
    public static int jobIdCounter =0;
    private String jobID;
    private String jobSubmitterID;
    private String status;
    private Duration jobRunTime;
    private int assignedVehicles;
    private double computationalPowerNeeded;
    private double storageNeeded;
    private Duration estimatedDuration;
    private int arrivalTime;
    private int duration;
    private int completionTime;
    private String reason;

    public Job(String jobSubmitterID, double storageNeeded, double computationalPowerNeeded, Duration estimatedDuration, String reason) {
        this.jobID = generateJobID();
        this.jobSubmitterID = jobSubmitterID;
        this.storageNeeded = storageNeeded;
        this.computationalPowerNeeded = computationalPowerNeeded;
        this.estimatedDuration = estimatedDuration;
        this.reason = reason;
        this.status = "Pending"; 
        this.jobRunTime = Duration.ofMinutes(0); 
        this.assignedVehicles = 0; 
        this.arrivalTime = 0; 
        this.duration = 0;
        this.completionTime = 0;
    
    }
    public Job( String jobSubmitterID, String status, Duration jobRunTime, int assignedVehicles,
               double computationalPowerNeeded, double storageNeeded, Duration estimatedDuration, int arrivalTime, int duration, String reason) {
        this.jobID = generateJobID();
        this.jobSubmitterID = jobSubmitterID;
        this.status = status;
        this.jobRunTime = jobRunTime;
        this.assignedVehicles = assignedVehicles;
        this.computationalPowerNeeded = computationalPowerNeeded;
        this.storageNeeded = storageNeeded;
        this.estimatedDuration = estimatedDuration;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.reason = reason;
    }
    private static String generateJobID() {
        return "JOB" + (++jobIdCounter);
   }
    //Getters and Setters
    
    public String getJobID() {
        return jobID;
    }

    public String getJobSubmitterID() {
        return jobSubmitterID;
    }

    public String getStatus() {
        return status;
    }

    public Duration getJobRunTime() {
        return jobRunTime;
    }

    public int getAssignedVehicles() {
        return assignedVehicles;
    }

    public double getComputationalPowerNeeded() {
        return computationalPowerNeeded;
    }

    public double getStorageNeeded() {
        return storageNeeded;
    }

    public Duration getEstimatedDuration() {
        return estimatedDuration;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getDuration() {
        return duration;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public void execute() {
        
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public void createCheckpoint() {
       
    }

    public void restartFromCheckpoint(Checkpoint checkpoint) {
       
    }
}