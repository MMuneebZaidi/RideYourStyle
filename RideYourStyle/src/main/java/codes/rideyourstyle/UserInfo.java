package codes.rideyourstyle;

public class UserInfo extends Info {
    Vehicle Purchased;
    public UserInfo(String name,String username, String email, String password, String phoneNumber, int age, String CNIC, String city) {
        super(name, username, email, password, phoneNumber, age, CNIC, city);
    }
    public UserInfo(String name,String username, String email, String password, String phoneNumber, int age, String CNIC, String city, Vehicle vehicle) {
        super(name, username, email, password, phoneNumber, age, CNIC, city);
        Purchased=vehicle;
    }
}