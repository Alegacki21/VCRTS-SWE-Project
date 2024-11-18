import java.util.ArrayList;
import java.util.List;

public class Server {
    private Double storageCapacity;
    private Double usedStorage;
    private List<Job> completedJobs;

    //Constructor
    
    public Server() {
        this.completedJobs = new ArrayList<>();
    }

    //Getters and Setters

    public Double getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(Double storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public Double getUsedStorage() {
        return usedStorage;
    }

    public void setUsedStorage(Double usedStorage) {
        this.usedStorage = usedStorage;
    }

    public List<Job> getCompletedJobs() {
        return completedJobs;
    }

    public void setCompletedJobs(List<Job> completedJobs) {
        this.completedJobs = completedJobs;
    }

    //Methods

    public void addCompletedJob(Job job) {
        // Needs to be implemented
    }

    public void eraseData() {
        // Needs to be implemented
    }

    public void acceptData(Job job) {
        // Needs to be implemented
    }

    public void rejectData(Job job) {
        // Needs to be implemented
    }

    public void sendMessageToClient(String message, User user) {
        // Needs to be implemented
    }
}