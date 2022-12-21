package codes.rideyourstyle;

public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();
    private String car1;
    private String car2;
    DataSingleton(){}
    public static DataSingleton getInstance(){
        return instance;
    }

    public String getCar1() {
        return car1;
    }

    public void setCar1(String car1) {
        this.car1 = car1;
    }

    public String getCar2() {
        return car2;
    }

    public void setCar2(String car2) {
        this.car2 = car2;
    }
}
