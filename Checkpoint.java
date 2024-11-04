import java.time.*;

public class Checkpoint {
    String imageID;
    String vehicleID = getVehicleID();
    
    public String getVehicleID() {
        return vehicleID;
    }
    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }
    LocalDateTime time = LocalDateTime.now();

    
}

