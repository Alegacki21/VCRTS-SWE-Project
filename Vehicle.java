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
    public Vehicle(VehicleBuilder builder) {
        this.name = builder.name;
        this.vehicleID = builder.vehicleID;
        this.owner = builder.owner;
        this.model = builder.model;
        this.year = builder.year;
        this.make = builder.make;
        this.VIN = builder.VIN;
        this.computationalPower = builder.computationalPower;
        this.storageCapacity = builder.storageCapacity;
        this.status = builder.status != null ? builder.status : "Available";
        this.location = builder.location;
        this.arrivalTime = builder.arrivalTime;
        this.departureTime = builder.departureTime;
        this.isAvailable = builder.isAvailable;
    }

    public static class VehicleBuilder {
        private String name;
        private String vehicleID;
        private VehicleOwner owner;
        private String model;
        private int year;
        private String make;
        private String VIN;
        private double computationalPower;
        private double storageCapacity;
        private String status = "Available";
        private String location;
        private LocalTime arrivalTime;
        private LocalTime departureTime;
        private boolean isAvailable = true;

        public VehicleBuilder(String VIN, String model, String make) {
            this.VIN = VIN;
            this.model = model;
            this.make = make;
        }

        public VehicleBuilder name(String name) { this.name = name; return this; }
        public VehicleBuilder vehicleID(String vehicleID) { this.vehicleID = vehicleID; return this; }
        public VehicleBuilder owner(VehicleOwner owner) { this.owner = owner; return this; }
        public VehicleBuilder year(int year) { this.year = year; return this; }
        public VehicleBuilder computationalPower(double power) { this.computationalPower = power; return this; }
        public VehicleBuilder storageCapacity(double capacity) { this.storageCapacity = capacity; return this; }
        public VehicleBuilder status(String status) { this.status = status; return this; }
        public VehicleBuilder location(String location) { this.location = location; return this; }
        public VehicleBuilder arrivalTime(LocalTime time) { this.arrivalTime = time; return this; }
        public VehicleBuilder departureTime(LocalTime time) { this.departureTime = time; return this; }
        public VehicleBuilder isAvailable(boolean available) { this.isAvailable = available; return this; }

        public Vehicle build() {
            return new Vehicle(this);
        }
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
       
    public void startJob(Job j) {
    
    }
    public void stopJob(Job j) {
    
    }
    public void checkpoint() {
    
    }
    public void clearData() {
    
    }
    
    }