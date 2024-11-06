public class Job {
    private String jobId;
    private int duration;
    private int arrivalTime;
    private int completionTime;
    private double computationalPowerNeeded;
    private String status;

    public Job(String jobId, int duration, int arrivalTime) {
        this.jobId = jobId;
        this.duration = duration;
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
    }
}