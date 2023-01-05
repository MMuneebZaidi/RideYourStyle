package codes.rideyourstyle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private TextField pass_show;
    @FXML
    private TextField Age;
    @FXML
    private TextField CNIC;
    @FXML
    private TextField City;
    @FXML
    private TextField Phone;
    @FXML
    private Label nameError;
    @FXML
    private Label usernameError;
    @FXML
    private Label emailError;
    @FXML
    private Label passwordError;
    @FXML
    private Label ageError;
    @FXML
    private Label cnicError;
    @FXML
    private Label cityError;
    @FXML
    private Label phoneError;
    @FXML
    private CheckBox pass_toggle;
    public Connection getDatabaseLink() {
        String databaseName = "admin/user";
        String databaseUser = "root";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost/"+databaseName;
        Connection DatabaseLink;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            DatabaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return DatabaseLink;
    }
    @FXML
    public void showPassword() {
        if (pass_toggle.isSelected()) {
            pass_show.setText(Password.getText());
            pass_show.setVisible(true);
            Password.setVisible(false);
            return;
        }
        Password.setText(pass_show.getText());
        Password.setVisible(true);
        pass_show.setVisible(false);
    }

    private boolean checkEmail(String email){
        LoginDatabaseConnection db = new LoginDatabaseConnection();
        ObservableList<Info> test = db.retrieveDatabase("user");
        boolean flag = false;
        for( Info obj : test){
            if (Objects.equals(email, obj.Email)) {
                emailError.setText("Email already exists! Login");
                setRed(Email);
                flag = true;
                break;
            }
        }
        return flag;
    }

    private boolean validateName(){
        ArrayList<Character> a = new ArrayList<>();
        String b="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
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
            nameError.setText("Name cannot be empty!");
            setRed(Name);
            flag = false;
        } else if (test) {
            nameError.setText("Name must contains alphabets only!");
            setRed(Name);
            flag=false;
        }
        return flag;
    }
    private boolean validateUsername(){
        ArrayList<Character> a = new ArrayList<>();
        String b="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i=0;i<b.length();i++)
        {
            a.add(b.charAt(i));
        }
        boolean flag = true;
        if(Username.getText().isEmpty()){
            usernameError.setText("Name cannot be empty!");
            setRed(Username);
            flag = false;
        } else if (!a.contains(Username.getText().charAt(0)) && !Character.isWhitespace(Username.getText().charAt(0))) {
            usernameError.setText("First letter must be an Alphabet!");
            setRed(Username);
            flag=false;
        } else if (Username.getText().contains(" ")) {
            usernameError.setText("No spaces are allowed!");
            setRed(Username);
            flag = false;
        }
        return flag;
    }
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
    private boolean validateEmail(){
        boolean flag = true;
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(Email.getText().isEmpty()){
            emailError.setText("Field cannot be left empty!");
            setRed(Email);
            flag = false;
        } else if (!(patternMatches(Email.getText(), regexPattern))) {
            emailError.setText("Enter correct email!");
            setRed(Email);
            flag = false;
        } else if (checkEmail(Email.getText())) {
            flag = false;
        }
        return flag;
    }
    private boolean strongPass(){
        ArrayList<Character> a = new ArrayList<>();
        String small="abcdefghijklmnopqrstuvwxyz";
        boolean test = true ;
        for(int i=0;i<small.length();i++)
        {
            a.add(small.charAt(i));
        }
        for (int i = 0; i < Password.getText().length(); i++) {
            if (a.contains(Password.getText().charAt(i)) && !Character.isWhitespace(Password.getText().charAt(i))) {
                test=true;
                break;
            }else
                test=false;
        }
        if(test){
            ArrayList<Character> a1 = new ArrayList<>();
            String large="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            for(int i=0;i<large.length();i++)
            {
                a1.add(large.charAt(i));
            }
            for (int i = 0; i < Password.getText().length(); i++) {
                if (a1.contains(Password.getText().charAt(i)) && !Character.isWhitespace(Password.getText().charAt(i))) {
                    test=true;
                    break;
                }else
                    test=false;
            }
        }
        if (test){
            ArrayList<Character> a2 = new ArrayList<>();
            String num="0123456789";
            for(int i=0;i<num.length();i++)
            {
                a2.add(num.charAt(i));
            }
            for (int i = 0; i < Password.getText().length(); i++) {
                if (a2.contains(Password.getText().charAt(i)) && !Character.isWhitespace(Password.getText().charAt(i))) {
                    test=true;
                    break;
                }else
                    test=false;
            }
        }

        return test;
    }
    private boolean validatePassword(){
        boolean flag = true;
        if(Password.getText().isEmpty()){
            passwordError.setText("Enter password!");
            setRed(pass_show,Password);
            flag = false;
        } else if (Password.getText().length()<8) {
            passwordError.setText("Length should be greater then 8 !");
            setRed(pass_show,Password);
            flag = false;
        } else if (!strongPass()){
            passwordError.setText("Must have numbers & digits!");
            setRed(pass_show,Password);
            flag = false;
        }
        return flag;
    }
    private boolean validateCNIC(){
        boolean flag = true;
        if(CNIC.getText().isEmpty()){
            cnicError.setText("Enter CNIC!");
            setRed(CNIC);
            flag = false;
        } else if (CNIC.getText().length()<13) {
            cnicError.setText("Enter valid CNIC!");
            setRed(CNIC);
            flag=false;
        }
        return flag;
    }
    private boolean validatePhone(){
        boolean flag = true;
        if(Phone.getText().isEmpty()){
            phoneError.setText("Enter Phone Number!");
            setRed(Phone);
            flag = false;
        } else if (Phone.getText().length()<11) {
            phoneError.setText("Enter valid Phone Number!");
            setRed(Phone);
            flag=false;
        }
        return flag;
    }
    private boolean validateAge(){
        boolean flag = true;
        if(Age.getText().isEmpty()){
            ageError.setText("Enter age!");
            setRed(Age);
            flag = false;
        } else if (Age.getText().length()<2) {
            ageError.setText("Age should be greater then 18!");
            setRed(Age);
            flag = false;
        } else if (Integer.parseInt(Age.getText())<=18) {
            ageError.setText("Age should be greater then 18!");
            setRed(Age);
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
            cityError.setText("Enter City!");
            setRed(City);
            flag = false;
        } else if (test) {
            cityError.setText("Enter valid city!");
            setRed(City);
            flag=false;
        }
        return flag;
    }
    private void setRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        if(!styleClass.contains("error")) {
            styleClass.add("error");
        }
    }
    private void setRed(TextField tf,PasswordField pf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        ObservableList<String> styleClass2 = pf.getStyleClass();
        if(!styleClass.contains("error")) {
            styleClass.add("error");
        }
        if(!styleClass2.contains("error")) {
            styleClass2.add("error");
        }
    }
    private void removeRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("error"));
    }
    private void removeAll(){
        removeRed(Name);
        removeRed(Username);
        removeRed(Email);
        removeRed(Password);
        removeRed(Age);
        removeRed(CNIC);
        removeRed(City);
        removeRed(Phone);
        nameError.setText(null);
        usernameError.setText(null);
        emailError.setText(null);
        passwordError.setText(null);
        cnicError.setText(null);
        cityError.setText(null);
        ageError.setText(null);
        phoneError.setText(null);
    }
    @FXML
    void SignupButton(ActionEvent ev) throws SQLException, IOException {
        removeAll();
        if(validateName()){
            if(validateUsername()){
                if (validateEmail()){
                    if(validatePassword()){
                        if(validateAge()){
                            if(validateCNIC()){
                                if(validateCity()){
                                    if(validatePhone()){
                                        LoginDatabaseConnection db = new LoginDatabaseConnection();
                                        Info member = new UserInfo(1,Name.getText(),Username.getText(),Email.getText(),Password.getText(),
                                                Phone.getText(),Integer.parseInt(Age.getText()), CNIC.getText(), City.getText());
                                        db.insertData("user",member);
                                        Connection connectDB = getDatabaseLink();
                                        String query = "SELECT `id` FROM `user` WHERE Email='"+member.Email+"'";

                                        try {
                                            Statement stm = connectDB.createStatement();
                                            ResultSet output = stm.executeQuery(query);
                                            while (output.next()) {
                                                member.id = output.getInt("id");
                                            }
                                        }
                                        catch (Exception e) {
                                            Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE,null,e);
                                        }
                                        Info data = new UserInfo(member.id);
                                        LoginDatabaseConnection dbg = new LoginDatabaseConnection();
                                        dbg.insertGarageData(data);
                                        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("UserLogin.fxml"));
                                        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
                                        Stage stage = (Stage) (((Node) ev.getSource()).getScene().getWindow());
                                        Scene scene;
                                        if (stage.isMaximized()) {
                                            scene = new Scene(fxmlLoader.load(), screenSize.getWidth(), screenSize.getHeight());
                                        } else {
                                            scene = new Scene(fxmlLoader.load());
                                        }
                                        stage.setScene(scene);
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
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Stage stage = (Stage) (((Node) ev.getSource()).getScene().getWindow());
        Scene scene;
        if (stage.isMaximized()) {
            scene = new Scene(fxmlLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        } else {
            scene = new Scene(fxmlLoader.load());
        }
        stage.setScene(scene);
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
