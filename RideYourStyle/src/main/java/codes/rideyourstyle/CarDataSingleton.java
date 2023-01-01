package codes.rideyourstyle;

public class CarDataSingleton {
    private static final CarDataSingleton instance = new CarDataSingleton();
    private String vehicle;
    private String pass_name;
    private String name1;
    private String name2;
    CarDataSingleton(){}
    public static CarDataSingleton getInstance(){
        return instance;
    }

    public String getPass_name() {
        return pass_name;
    }

    public void setPass_name(String pass_name) {
        this.pass_name = pass_name;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String car1) {
        this.vehicle = car1;
    }

}