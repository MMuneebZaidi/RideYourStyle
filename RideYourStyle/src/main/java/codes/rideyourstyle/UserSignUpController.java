package codes.rideyourstyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

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
    @FXML
    private Label validateName;
    @FXML
    private Label validateUsername;
    @FXML
    private Label validateEmail;
    @FXML
    private Label validatePassword;
    @FXML
    private Label validateAge;
    @FXML
    private Label validateCNIC;
    @FXML
    private Label validateCity;
    @FXML
    private Label validatePhone;

    public UserSignUpController() {
    }

    private boolean checkEmail(String email) {
        LoginDatabaseConnection db = new LoginDatabaseConnection();
        ObservableList<Info> test = db.retrieveDatabase("user");
        boolean flag = false;

        for (Info obj : test) {
            if (Objects.equals(email, obj.Email)) {
                flag = true;
                this.validateEmail.setText("Email already exists! Login.");
                break;
            }
        }

        return flag;
    }

    private boolean validateName() {
        ArrayList<Character> a = new ArrayList<>();
        String b = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        boolean test = false;

        int i;
        for(i = 0; i < b.length(); ++i) {
            a.add(b.charAt(i));
        }

        for(i = 0; i < this.Name.getText().length(); ++i) {
            if (!a.contains(this.Name.getText().charAt(i)) && !Character.isWhitespace(this.Name.getText().charAt(i))) {
                test = true;
            }
        }

        boolean flag = true;
        if (this.Name.getText().isEmpty()) {
            this.validateName.setText("Name cannot be empty!");
            flag = false;
        } else if (test) {
            this.validateName.setText("First letter must be an alphabet!");
            flag = false;
        }

        return flag;
    }

    private boolean validateUsername() {
        ArrayList<Character> a = new ArrayList<>();
        String b = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < b.length(); ++i) {
            a.add(b.charAt(i));
        }

        boolean flag = true;
        if (this.Username.getText().isEmpty()) {
            flag = false;
        } else if (!a.contains(this.Username.getText().charAt(0)) && !Character.isWhitespace(this.Username.getText().charAt(0))) {
            flag = false;
        } else if (this.Username.getText().contains(" ")) {
            flag = false;
        }

        return flag;
    }

    private boolean validateEmail() {
        boolean flag = true;
        if (this.Email.getText().isEmpty()) {
            flag = false;
        } else if (!this.Email.getText().contains("@")) {
            flag = false;
        } else if (!this.Email.getText().contains(".")) {
            flag = false;
        } else if (this.checkEmail(this.Email.getText())) {
            flag = false;
        }

        return flag;
    }

    private boolean validatePassword() {
        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(this.Password.getText());
        boolean b = matcher.find();
        boolean flag = true;
        if (this.Password.getText().isEmpty()) {
            flag = false;
        } else if (this.Password.getText().length() < 8) {
            flag = false;
        } else if (!b) {
            flag = false;
        }

        return flag;
    }

    private boolean validateCNIC() {
        boolean flag = true;
        if (this.CNIC.getText().isEmpty()) {
            flag = false;
        } else if (this.CNIC.getText().length() < 13) {
            flag = false;
        }

        return flag;
    }

    private boolean validatePhone() {
        boolean flag = true;
        if (this.Phone.getText().isEmpty()) {
            flag = false;
        } else if (this.Phone.getText().length() < 11) {
            flag = false;
        }

        return flag;
    }

    private boolean validateAge() {
        boolean flag = true;
        if (this.Age.getText().isEmpty()) {
            flag = false;
        } else if (this.Age.getText().length() < 2) {
            flag = false;
        } else if (Integer.parseInt(this.Age.getText()) <= 18) {
            flag = false;
        }

        return flag;
    }

    private boolean validateCity() {
        ArrayList<Character> a = new ArrayList<>();
        String b = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        boolean test = false;

        int i;
        for(i = 0; i < b.length(); ++i) {
            a.add(b.charAt(i));
        }

        for(i = 0; i < this.City.getText().length(); ++i) {
            if (!a.contains(this.City.getText().charAt(i)) && !Character.isWhitespace(this.City.getText().charAt(i))) {
                test = true;
            }
        }

        boolean flag = true;
        if (this.City.getText().isEmpty()) {
            flag = false;
        } else if (test) {
            flag = false;
        } else if (this.City.getText().contains(" ")) {
            flag = false;
        }

        return flag;
    }

    @FXML
    void SignupButton(ActionEvent ev) throws SQLException, IOException {
        if (this.validateName()) {
            if (this.validateUsername()) {
                if (this.validateEmail()) {
                    if (this.validatePassword()) {
                        if (this.validateCNIC()) {
                            if (this.validateAge()) {
                                if (this.validateCity()) {
                                    if (this.validatePhone()) {
                                        LoginDatabaseConnection db = new LoginDatabaseConnection();
                                        Info member = new UserInfo(this.Name.getText(), this.Username.getText(), this.Email.getText(), this.Password.getText(), this.Phone.getText(), Integer.parseInt(this.Age.getText()), this.CNIC.getText(), this.City.getText());
                                        db.insertData("user", member);
                                        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("UserLogin.fxml"));
                                        Scene scene = new Scene(fxmlLoader.load(), 1080.0, 720.0);
                                        Stage stage = (Stage)((Node)ev.getSource()).getScene().getWindow();
                                        stage.setScene(scene);
                                        stage.show();
                                    } else {
                                        System.out.println("Invalid Phone Number");
                                    }
                                } else {
                                    System.out.println("Invalid City");
                                }
                            } else {
                                System.out.println("Invalid Age");
                            }
                        } else {
                            System.out.println("Invalid CNIC");
                        }
                    } else {
                        System.out.println("Invalid Password");
                    }
                } else {
                    System.out.println("Invalid Email");
                }
            } else {
                System.out.println("Invalid Username");
            }
        } else {
            System.out.println("Invalid Name");
        }

    }

    @FXML
    void LoginButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("UserLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080.0, 720.0);
        Stage stage = (Stage)((Node)ev.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.CNIC.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                this.CNIC.setText(newValue.replaceAll("\\D", ""));
            }

            if (this.CNIC.getText().length() > 13) {
                String s = this.CNIC.getText().substring(0, 13);
                this.CNIC.setText(s);
            }

        });
        this.Phone.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                this.Phone.setText(newValue.replaceAll("\\D", ""));
            }

            if (this.Phone.getText().length() > 11) {
                String s = this.Phone.getText().substring(0, 11);
                this.Phone.setText(s);
            }

        });
        this.Age.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                this.Age.setText(newValue.replaceAll("\\D", ""));
            }

            if (this.Age.getText().length() > 2) {
                String s = this.Age.getText().substring(0, 2);
                this.Age.setText(s);
            }

        });
        Pattern pattern = Pattern.compile("[a-zA-Z]*");
        UnaryOperator<TextFormatter.Change> filter = (c) -> pattern.matcher(c.getControlNewText()).matches() ? c : null;
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        this.City.setTextFormatter(formatter);
    }
}
