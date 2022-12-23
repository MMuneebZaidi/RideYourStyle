package codes.rideyourstyle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSignUpController implements Initializable {
    @FXML
    private TextField Name;
    @FXML
    private TextField Username;
    @FXML
    private TextField Email;
    @FXML
    private PasswordField Password;
    @FXML
    private TextField Age;
    @FXML
    private TextField CNIC;
    @FXML
    private TextField City;
    @FXML
    private TextField Phone;


    private boolean checkEmail(String email){
        LoginDatabaseConnection db = new LoginDatabaseConnection();
        ObservableList<Info> test = db.retrieveDatabase("user");
        boolean flag = false;
        for( Info obj : test){
            if (Objects.equals(email, obj.Email)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    private boolean validateName(){
        ArrayList<Character> a = new ArrayList<>();
        String b="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        boolean test=false;
        for(int i=0;i<b.length();i++)
        {
            a.add(b.charAt(i));
        }
        for (int i = 0; i < Name.getText().length(); i++) {
            if (!a.contains(Name.getText().charAt(i)) && !Character.isWhitespace(Name.getText().charAt(i))) {
                test=true;
            }
        }
        boolean flag = true;
        if(Name.getText().isEmpty()){
            flag = false;
        } else if (test) {
            flag=false;
        }
        return flag;
    }
    private boolean validateUsername(){
        ArrayList<Character> a = new ArrayList<>();
        String b="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        boolean test=false;
        for(int i=0;i<b.length();i++)
        {
            a.add(b.charAt(i));
        }
        if (!a.contains(Username.getText().charAt(0)) && !Character.isWhitespace(Username.getText().charAt(0))) {
            test=true;
        }
        boolean flag = true;
        if(Username.getText().isEmpty()){
            flag = false;
        } else if (test) {
            flag=false;
        } else if (Username.getText().contains(" ")) {
            flag = false;
        }
        return flag;
    }
    private boolean validateEmail(){
        boolean flag = true;
        if(Email.getText().isEmpty()){
            flag = false;
        } else if ( !(Email.getText().contains("@"))) {
            flag = false;
        } else if (!(Email.getText().contains("."))) {
            flag = false;
        } else if (checkEmail(Email.getText())) {
            flag = false;
        }
        return flag;
    }
    private boolean validatePassword(){
        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(Password.getText());
        boolean b= matcher.find();
        ArrayList<Character> a = new ArrayList<>();
        String smallLetters="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        boolean test=false;
        for(int i=0;i<smallLetters.length();i++)
        {
            a.add(smallLetters.charAt(i));
        }
        for (int i = 0; i < Password.getText().length(); i++) {
            if (!a.contains(Password.getText().charAt(i)) && !Character.isWhitespace(Password.getText().charAt(i))) {
                test=true;
            }
        }
        boolean flag = true;
        if(Password.getText().isEmpty()){
            flag = false;
        } else if (Password.getText().length()<8) {
            flag = false;
        } else if (test) {
            flag = false;
        } else if (!b) {
            flag = false;
        }
        return flag;
    }
    private boolean validateCNIC(){
        boolean flag = true;
        if(CNIC.getText().isEmpty()){
            flag = false;
        } else if (CNIC.getText().length()<13) {
            flag=false;
        }
        return flag;
    }
    private boolean validatePhone(){
        boolean flag = true;
        if(Phone.getText().isEmpty()){
            flag = false;
        } else if (Phone.getText().length()<11) {
            flag=false;
        }
        return flag;
    }
    private boolean validateAge(){
        boolean flag = true;
        if(Age.getText().isEmpty()){
            flag = false;
        } else if (Age.getText().length()<2) {
            flag = false;
        } else if (Integer.parseInt(Age.getText())<=18) {
            flag = false;
        }
        return flag;
    }
    private boolean validateCity(){
        ArrayList<Character> a = new ArrayList<>();
        String b="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        boolean test=false;
        for(int i=0;i<b.length();i++)
        {
            a.add(b.charAt(i));
        }
        for (int i = 0; i < City.getText().length(); i++) {
            if (!a.contains(City.getText().charAt(i)) && !Character.isWhitespace(City.getText().charAt(i))) {
                test=true;
            }
        }
        boolean flag = true;
        if(City.getText().isEmpty()){
            flag = false;
        } else if (test) {
            flag=false;
        } else if (City.getText().contains(" ")) {
            flag = false;
        }
        return flag;
    }
    @FXML
    void SignupButton(ActionEvent ev) throws SQLException, IOException {
        if(validateName()){
            if(validateUsername()){
                if (validateEmail()){
                    if(validatePassword()){
                        if(validateCNIC()){
                            if(validateAge()){
                                if(validateCity()){
                                    if(validatePhone()){
                                        LoginDatabaseConnection db = new LoginDatabaseConnection();
                                        Info member = new UserInfo(Name.getText(),Username.getText(),Email.getText(),Password.getText(),
                                                Phone.getText(),Integer.parseInt(Age.getText()), CNIC.getText(), City.getText());
                                        db.insertData("user",member);
                                        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("UserLogin.fxml"));
                                        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
                                        Stage stage = (Stage) (((Node)ev.getSource()).getScene().getWindow());
                                        stage.setScene(scene);
                                        stage.show();
                                    }else {
                                        System.out.println("Invalid Phone Number");
                                    }
                                }else {
                                    System.out.println("Invalid City");
                                }
                            }else {
                                System.out.println("Invalid Age");
                            }
                        }else {
                            System.out.println("Invalid CNIC");
                        }
                    }else {
                        System.out.println("Invalid Password");
                    }
                }else {
                    System.out.println("Invalid Email");
                }
            }else {
                System.out.println("Invalid Username");
            }
        }else {
            System.out.println("Invalid Name");
        }
    }
    @FXML
    void LoginButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("UserLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node)ev.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CNIC.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                CNIC.setText(newValue.replaceAll("\\D", ""));
            }
            if (CNIC.getText().length() > 13) {
                String s = CNIC.getText().substring(0, 13);
                CNIC.setText(s);
            }
        });
        Phone.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                Phone.setText(newValue.replaceAll("\\D", ""));
            }
            if (Phone.getText().length() > 11) {
                String s = Phone.getText().substring(0, 11);
                Phone.setText(s);
            }
        });
        Age.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                Age.setText(newValue.replaceAll("\\D", ""));
            }
            if (Age.getText().length() > 2) {
                String s = Age.getText().substring(0, 2);
                Age.setText(s);
            }
        });
        Pattern pattern = Pattern.compile("[a-zA-Z]*");
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (pattern.matcher(c.getControlNewText()).matches()) {
                return c ;
            } else {
                return null ;
            }
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        City.setTextFormatter(formatter);
    }
}
