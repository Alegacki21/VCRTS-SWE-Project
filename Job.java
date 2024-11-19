public class Job {
    private String jobID;
    private String jobSubmitterID;
    private String status;
    private Duration jobRunTime;
    private int assignedVehicles;
    private double computationalPowerNeeded;
    private double storageNeeded;
    private Duration estimatedDuration;
    private int arrivalTime;
    private int completionTime;

    public Job(String jobID, String jobSubmitterID, String status, Duration jobRunTime, int assignedVehicles,
               double computationalPowerNeeded, double storageNeeded, Duration estimatedDuration, int arrivalTime, int duration) {
        this.jobID = jobID;
        this.jobSubmitterID = jobSubmitterID;
        this.status = status;
        this.jobRunTime = jobRunTime;
        this.assignedVehicles = assignedVehicles;
        this.computationalPowerNeeded = computationalPowerNeeded;
        this.storageNeeded = storageNeeded;
        this.estimatedDuration = estimatedDuration;
        this.arrivalTime = arrivalTime;
        this.status = "Pending";
    }

    // Getters and setters
    public String getJobId() { return jobId; }
    public int getDuration() { return duration; }
    public int getArrivalTime() { return arrivalTime; }
    public int getCompletionTime() { return completionTime; }
    public void setCompletionTime(int completionTime) { this.completionTime = completionTime; }
    public double getComputationalPowerNeeded() { return computationalPowerNeeded; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public void setDuration(int duration) {
        this.duration = duration;
        this.reason = reason;
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