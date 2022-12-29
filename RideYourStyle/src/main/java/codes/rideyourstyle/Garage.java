package codes.rideyourstyle;

import javafx.collections.ObservableList;


public class Garage {
    static Vehicle car1=null;
    static Vehicle car2=null;
    static Vehicle car3=null;
    static Vehicle car4=null;
    static Vehicle car5=null;

    public static void setCars(ObservableList<Vehicle> cars) {
        Garage.cars = cars;
    }

    static ObservableList<Vehicle> cars;

    public static ObservableList<Vehicle> getCars() {
        return cars;
    }
}
