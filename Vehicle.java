import java.time.*;

public class Vehicle {

    private String name;
    private String vehicleID;
    private VehicleOwner owner;
    private String model;
    private int year;
    private String make;
    private String VIN;
    private double computationalPower;
    private double storageCapacity;
    private String status;
    private String location;
    private LocalTime arrivalTime; // Broke residency Time into arrival time and departuretime
    private LocalTime departureTime;
    private boolean isAvailable;
    
    public Vehicle() {
        name = "Toyota Corolla";
        
    }
    public Vehicle(String name, String vehicleID, VehicleOwner owner, String model, int year, String make, String vIN,
            double computationalPower, double storageCapacity, String status, String location, LocalTime arrivalTime,
            LocalTime departureTime, boolean isAvailable) {
        this.name = name;
        this.vehicleID = vehicleID;
        this.owner = owner;
        this.model = model;
        this.year = year;
        this.make = make;
        VIN = vIN;
        this.computationalPower = computationalPower;
        this.storageCapacity = storageCapacity;
        this.status = status;
        this.location = location;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.isAvailable = isAvailable;
    }

    //Getters and Setters
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getVehicleID() {
        return vehicleID;
    }
    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }
    public VehicleOwner getOwner() {
        return owner;
    }
    public void setOwner(VehicleOwner owner) {
        this.owner = owner;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getVIN() {
        return VIN;
    }
    public void setVIN(String vIN) {
        VIN = vIN;
    }
    public double getComputationalPower() {
        return computationalPower;
    }
    public void setComputationalPower(double computationalPower) {
        this.computationalPower = computationalPower;
    }
    public double getStorageCapacity() {
        return storageCapacity;
    }
    public void setStorageCapacity(double storageCapacity) {
        this.storageCapacity = storageCapacity;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public LocalTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    //Methods
       
    public void startJob(Job j) {
    
    }
    public void stopJob(Job j) {
    
    }
    public void checkpoint() {
    
    }
    public void clearData() {
    
    }
    
    }