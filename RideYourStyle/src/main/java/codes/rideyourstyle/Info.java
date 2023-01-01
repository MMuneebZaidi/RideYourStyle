package codes.rideyourstyle;

public abstract class Info {
    int id;
    String Name;
    String Username;
    String Email;
    String Password;
    String PhoneNumber;
    int Age;
    String CNIC;
    String City;
    public Info(int id, String name, String username, String email, String password, String phoneNumber, int age, String CNIC, String city) {
        this.id = id;
        Name = name;
        Username = username;
        Email = email;
        Password = password;
        PhoneNumber = phoneNumber;
        Age = age;
        this.CNIC = CNIC;
        City = city;
    }
    Info(int id){
        this.id = id;
    }
}
