package codes.rideyourstyle;

public class UserInfo extends Info {

    public UserInfo(int id,String name,String username, String email, String password, String phoneNumber, int age, String CNIC,String city) {
        super(id,name, username, email, password, phoneNumber, age, CNIC, city);
    }
    UserInfo(int id){
        super(id);
    }
}
