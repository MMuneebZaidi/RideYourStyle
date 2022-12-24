package codes.rideyourstyle;

public abstract class Info {
    String Name;
    String Username;
    String Email;
    String Password;
    String PhoneNumber;
    int Age;
    String CNIC;
    String City;

    public Info(String name, String username, String email, String password, String phoneNumber, int age, String CNIC, String city) {
        Name = name;
        Username = username;
        Email = email;
        Password = password;
        PhoneNumber = phoneNumber;
        Age = age;
        this.CNIC = CNIC;
        City = city;
    }
}
