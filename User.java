public class User {
    
private String userId;
private String email;
private String username;
private String name;
private String password;
private double balance;
private String paymentMethod;

    //Constructor

    public User(String userId, String email, String username, String name, String password, double balance, String paymentMethod) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.paymentMethod = paymentMethod;
    }

    //Getters and Setters

    public String getUserId() { 
        return userId; 
    } 
    public String getEmail() { 
        return email; 
    } 
    public void setEmail(String email) {
        this.email = email; 
    } 
    public String getUsername() {
        return username; 
    } 
    public void setUsername(String username) {
        this.username = username; 
    }
    public String getName() {
        return name; 
    } 
    public void setName(String name) {
        this.name = name; 
    } 
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password; 
   } 
    public double getBalance() {
        return balance; 
    } 
    public void setBalance(double balance) {
        this.balance = balance; 
    }
    public String getPaymentMethod() {
        return paymentMethod; 
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod; 
    }

    //Methods

    public void updateProfile() {

    }
    public void changePassword(String oldPassword, String newPassword) {

    }
    public void addFunds(double amount) {

    }
    public void withdrawFunds(double amount) {

    }
    public void viewBalance() {

    }
    public void login() {

    }
    public void logout() {

    }
}
