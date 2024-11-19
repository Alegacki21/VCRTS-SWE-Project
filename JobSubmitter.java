import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JobSubmitter extends User {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private List<Job> JobList = new ArrayList<>();
    private String subscriptionPlan;
    private String paymentAccount;
    private CloudController cloudController;

    public JobSubmitter(String serverAddress, int port, String userId, String email, String username, String name, String password, double balance, String paymentMethod,
    List<Job> JobList, String subscriptionPlan, String paymentAccount) throws IOException {
        super(userId, email, username, name, password, balance, paymentMethod);
        this.JobList = JobList;
        this.subscriptionPlan = subscriptionPlan;
        this.paymentAccount = paymentAccount;
        socket = new Socket(serverAddress, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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

    // Methods

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
            String timestamp = LocalDateTime.now().toString();
            String jobData = "Job ID: " + job.getJobId() + "\n" +
                             "Duration: " + job.getDuration() + "\n" +
                             "Timestamp: " + timestamp;

            // Send job data to the server
            out.println(jobData);

            // Receive server response
            String response = in.readLine();
            System.out.println("Server response: " + response);

            if ("Request accepted".equals(response)) {
                // Store job data locally if accepted
                File jobFile = new File("jobs/" + job.getJobId() + ".txt");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(jobFile))) {
                    writer.write(jobData);
                }
            } else {
                System.out.println("Job submission rejected by server.");
            }

        } catch (IOException e) {
            e.printStackTrace();
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

    public static void main(String[] args) {
        try {
            JobSubmitter jobSubmitter = new JobSubmitter("localhost", 12345, "userId", "email", "username", "name", "password", 0.0, "paymentMethod", new ArrayList<>(), "subscriptionPlan", "paymentAccount");
            Job job = new Job("1", 120, 0); // Example job
            jobSubmitter.submitJob(job);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
