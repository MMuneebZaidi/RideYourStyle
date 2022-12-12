package codes.rideyourstyle;
import java.sql.Blob;

public class Vehicle  {
    String name;
    String engine;
    String transmissionType;
    String enginePower;
    String topSpeed;
    String acceleration;
    String mileage;
    String fuelType;
    String bodyType;
    String price;
    String rating;
    String seatingCapacity;
    String convertible;
    String doors;
    String wheelSize;
    String fuelCapacity;
    String stock;
    Blob image;
    public Vehicle(){

    }

    public Vehicle(String name, String engine, String transmissionType, String enginePower, String topSpeed, String acceleration, String mileage, String fuelType, String bodyType, String price, String rating, String seatingCapacity, String convertible, String doors, String wheelSize, String fuelCapacity, String stock, Blob image) {
        this.name = name;
        this.engine = engine;
        this.transmissionType = transmissionType;
        this.enginePower = enginePower;
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.bodyType = bodyType;
        this.price = price;
        this.rating = rating;
        this.seatingCapacity = seatingCapacity;
        this.convertible = convertible;
        this.doors = doors;
        this.wheelSize = wheelSize;
        this.fuelCapacity = fuelCapacity;
        this.stock = stock;
        this.image= image;
    }
}
