package codes.rideyourstyle;

public class CarDataSingleton {
    private static final CarDataSingleton instance = new CarDataSingleton();
    private String vehicle;

    CarDataSingleton(){}
    public static CarDataSingleton getInstance(){
        return instance;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String car1) {
        this.vehicle = car1;
    }

}
