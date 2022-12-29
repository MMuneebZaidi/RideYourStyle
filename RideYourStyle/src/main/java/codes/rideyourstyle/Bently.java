package codes.rideyourstyle;

import java.sql.Blob;

public class Bently extends Vehicle {
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getEngine() {
        return super.getEngine();
    }

    @Override
    public void setEngine(String engine) {
        super.setEngine(engine);
    }

    @Override
    public String getTransmissionType() {
        return super.getTransmissionType();
    }

    @Override
    public void setTransmissionType(String transmissionType) {
        super.setTransmissionType(transmissionType);
    }

    @Override
    public String getEnginePower() {
        return super.getEnginePower();
    }

    @Override
    public void setEnginePower(String enginePower) {
        super.setEnginePower(enginePower);
    }

    @Override
    public String getTopSpeed() {
        return super.getTopSpeed();
    }

    @Override
    public void setTopSpeed(String topSpeed) {
        super.setTopSpeed(topSpeed);
    }

    @Override
    public String getAcceleration() {
        return super.getAcceleration();
    }

    @Override
    public void setAcceleration(String acceleration) {
        super.setAcceleration(acceleration);
    }

    @Override
    public String getMileage() {
        return super.getMileage();
    }

    @Override
    public void setMileage(String mileage) {
        super.setMileage(mileage);
    }

    @Override
    public String getFuelType() {
        return super.getFuelType();
    }

    @Override
    public void setFuelType(String fuelType) {
        super.setFuelType(fuelType);
    }

    @Override
    public String getBodyType() {
        return super.getBodyType();
    }

    @Override
    public void setBodyType(String bodyType) {
        super.setBodyType(bodyType);
    }

    @Override
    public String getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(String price) {
        super.setPrice(price);
    }

    @Override
    public String getRating() {
        return super.getRating();
    }

    @Override
    public void setRating(String rating) {
        super.setRating(rating);
    }

    @Override
    public String getSeatingCapacity() {
        return super.getSeatingCapacity();
    }

    @Override
    public void setSeatingCapacity(String seatingCapacity) {
        super.setSeatingCapacity(seatingCapacity);
    }

    @Override
    public String getConvertible() {
        return super.getConvertible();
    }

    @Override
    public void setConvertible(String convertible) {
        super.setConvertible(convertible);
    }

    @Override
    public String getDoors() {
        return super.getDoors();
    }

    @Override
    public void setDoors(String doors) {
        super.setDoors(doors);
    }

    @Override
    public String getWheelSize() {
        return super.getWheelSize();
    }

    @Override
    public void setWheelSize(String wheelSize) {
        super.setWheelSize(wheelSize);
    }

    @Override
    public String getFuelCapacity() {
        return super.getFuelCapacity();
    }

    @Override
    public void setFuelCapacity(String fuelCapacity) {
        super.setFuelCapacity(fuelCapacity);
    }

    @Override
    public String getStock() {
        return super.getStock();
    }

    @Override
    public void setStock(String stock) {
        super.setStock(stock);
    }

    @Override
    public Blob getImage() {
        return super.getImage();
    }

    @Override
    public void setImage(Blob image) {
        super.setImage(image);
    }

    public Bently(String name, String engine, String transmissionType, String enginePower, String topSpeed, String acceleration, String mileage, String fuelType, String bodyType, String price, String rating, String seatingCapacity, String convertible, String doors, String wheelSize, String fuelCapacity, String stock, Blob image) {
        super(name, engine, transmissionType, enginePower, topSpeed, acceleration, mileage, fuelType, bodyType, price, rating, seatingCapacity, convertible, doors, wheelSize, fuelCapacity, stock, image);
    }
}
