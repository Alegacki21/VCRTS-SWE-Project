public class Job {
    private String jobId;
    private String userId;
    private int duration;
    private int arrivalTime;
    private int completionTime;
    private double computationalPowerNeeded;
    private String purpose;
    private String status;
    private String jobDeadline;

    public Job(String jobId, String userId, int duration, String jobDeadline, int arrivalTime, String purpose) {
        this.jobId = jobId;
        this.userId = userId;
        this.duration = duration;
        this.arrivalTime = arrivalTime;
        this.purpose = purpose;
        this.status = "Pending";
    }

    // Getters and setters
    public String getJobId() { return jobId; }
    public String getUserId() { return userId; }
    public int getDuration() { return duration; }
    public String getJobDeadline() { return jobDeadline; }
    public int getArrivalTime() { return arrivalTime; }
    public int getCompletionTime() { return completionTime; }
    public void setCompletionTime(int completionTime) { this.completionTime = completionTime; }
    public double getComputationalPowerNeeded() { return computationalPowerNeeded; }
    public String getPurpose() { return purpose; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public void setDuration(int duration) {
        this.duration = duration;
    }
}