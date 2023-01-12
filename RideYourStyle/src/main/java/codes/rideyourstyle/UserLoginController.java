package codes.rideyourstyle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Objects;
import java.util.ResourceBundle;
import static codes.rideyourstyle.UserSignUpController.patternMatches;

public class UserLoginController implements Initializable {
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXPasswordField Password;
    @FXML
    private Label validation;
    @FXML
    private JFXTextField pass_text;
    @FXML
    private JFXCheckBox pass_toggle;
    ObservableList<Info> userinfo = FXCollections.observableArrayList();
    public static Info data;
    public static Info loggedIn=null;
    @FXML
    void ForgotPassword(ActionEvent ev) throws IOException {
        for(Info member : userinfo){
            if(member.Email.equals(Email.getText())) {
                data = member;
                FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("ForgotPassword.fxml"));
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
        }
    }
    private void setRed(JFXTextField tf,JFXPasswordField pf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        ObservableList<String> styleClass2 = pf.getStyleClass();
        if(!styleClass.contains("error")) {
            styleClass.add("error");
        }
        if(!styleClass2.contains("error")) {
            styleClass2.add("error");
        }
    }
    private void setRed(JFXTextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        if(!styleClass.contains("error")) {
            styleClass.add("error");
        }
    }
    private void removeRed(JFXTextField tf, JFXTextField tf1, JFXPasswordField pf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("error"));
        ObservableList<String> styleClass1 = tf1.getStyleClass();
        styleClass1.removeAll(Collections.singleton("error"));
        ObservableList<String> styleClass2 = pf.getStyleClass();
        styleClass2.removeAll(Collections.singleton("error"));
    }
    private boolean checkEmail(ObservableList<Info> data){
        boolean flag = false;
        for(Info info : data){
            if(Objects.equals(Email.getText(), info.Email)){
                flag = true;
            }
        }
        return flag;
    }
    private boolean checkPass(ObservableList<Info> data){
        boolean flag = false;
        for(Info info : data){
            if(Objects.equals(Password.getText(), info.Password) && Email.getText().equals(info.Email)){
                loggedIn=info;
                UserLoginController.data=info;
                flag = true;
            }
        }
        return flag;
    }
    private boolean validateEmail(){
        boolean flag = true;
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(Email.getText().isEmpty()){
            validation.setText("Field cannot be left empty!");
            Email.setFocusColor(Paint.valueOf("RED"));
            Password.setFocusColor(Paint.valueOf("RED"));
            pass_text.setFocusColor(Paint.valueOf("RED"));
            setRed(Email,Password);
            setRed(pass_text);
            flag = false;
        } else if (!(patternMatches(Email.getText(), regexPattern))) {
            validation.setText("Enter correct email!");
            Email.setFocusColor(Paint.valueOf("RED"));
            setRed(Email,Password);
            setRed(pass_text);
            flag = false;
        }
        return flag;
    }
    @FXML
    void LoginButton(ActionEvent ev) throws IOException {
        removeRed(Email,pass_text,Password);
        if(validateEmail()){
            if(checkEmail(userinfo)){
                if(checkPass(userinfo)){
                    FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
                    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
                    Stage stage = (Stage) (((Node) ev.getSource()).getScene().getWindow());
                    Scene scene;
                    if (stage.isMaximized()) {
                        scene = new Scene(fxmlLoader.load(), screenSize.getWidth(), screenSize.getHeight());
                    } else {
                        scene = new Scene(fxmlLoader.load());
                    }
                    stage.setScene(scene);
                }else{
                    validation.setText("Incorrect Password!");
                    Email.setFocusColor(Paint.valueOf("#e9e9e9"));
                    Password.setFocusColor(Paint.valueOf("RED"));
                    setRed(pass_text,Password);
                    Password.setText(null);
                }
            }else {
                validation.setText("Incorrect Username or Password!");
                setRed(Email,Password);
                setRed(pass_text);
                Password.setText(null);
            }
        }
    }
    @FXML
    public void showPassword() {
        if (pass_toggle.isSelected()) {
            pass_text.setText(Password.getText());
            pass_text.setVisible(true);
            Password.setVisible(false);
            return;
        }
        Password.setText(pass_text.getText());
        Password.setVisible(true);
        pass_text.setVisible(false);
    }

    @FXML
    void SignupButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("UserSignUp.fxml"));
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
    @FXML
    void AdminLoginButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("AdminLogin.fxml"));
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
    @FXML
    void GuestButton(ActionEvent ev) throws IOException {
        UserLoginController.loggedIn=null;
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
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
        pass_text.setOnKeyReleased(keyEvent -> Password.setText(pass_text.getText()));
        LoginDatabaseConnection db = new LoginDatabaseConnection();
        userinfo = db.retrieveDatabase("user");
        pass_text.setVisible(false);

    }
}
