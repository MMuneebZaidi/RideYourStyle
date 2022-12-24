package codes.rideyourstyle;

import java.sql.Blob;

public class Cheverolet extends Vehicle{

    public Cheverolet(String name, String engine, String transmissionType, String enginePower, String topSpeed, String acceleration, String mileage, String fuelType, String bodyType, String price, String rating, String seatingCapacity, String convertible, String doors, String wheelSize, String fuelCapacity, String stock, Blob image) {
        super(name, engine, transmissionType, enginePower, topSpeed, acceleration, mileage, fuelType, bodyType, price, rating, seatingCapacity, convertible, doors, wheelSize, fuelCapacity, stock, image);
    }
}
