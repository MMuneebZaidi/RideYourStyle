package codes.rideyourstyle;

import java.sql.Blob;

public class BMW extends Vehicle{

    public BMW(String name, String engine, String transmissionType, String enginePower, String topSpeed, String acceleration, String mileage, String fuelType, String bodyType, String price, String rating, String seatingCapacity, String convertible, String doors, String wheelSize, String fuelCapacity, String stock, Blob image) {
        super(name, engine, transmissionType, enginePower, topSpeed, acceleration, mileage, fuelType, bodyType, price, rating, seatingCapacity, convertible, doors, wheelSize, fuelCapacity, stock, image);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(String enginePower) {
        this.enginePower = enginePower;
    }

    public String getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(String topSpeed) {
        this.topSpeed = topSpeed;
    }

    public String getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(String acceleration) {
        this.acceleration = acceleration;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(String seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public String getConvertible() {
        return convertible;
    }

    public void setConvertible(String convertible) {
        this.convertible = convertible;
    }

    public String getDoors() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public String getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(String wheelSize) {
        this.wheelSize = wheelSize;
    }

    public String getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(String fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
