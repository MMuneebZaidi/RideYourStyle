package codes.rideyourstyle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

public class UserLoginController {
    @FXML
    private TextField Email;
    @FXML
    private PasswordField Password;
    @FXML
    private Label validation;
    @FXML
    void ForgotPassword(){

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
    private void removeRed(TextField tf,PasswordField pf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("error"));
        ObservableList<String> styleClass2 = pf.getStyleClass();
        styleClass2.removeAll(Collections.singleton("error"));
    }
    private boolean check(ObservableList<Info> data){
        boolean flag = false;
        for(Info info : data){
            if(Objects.equals(Email.getText(), info.Email) && Objects.equals(Password.getText(), info.Password)){
                flag = true;
            }
        }
        return flag;
    }
    @FXML
    void LoginButton(ActionEvent ev) throws IOException {
        LoginDatabaseConnection db = new LoginDatabaseConnection();
        userinfo = db.retrieveDatabase("user");
        removeRed(Email,Password);
        if(check(userinfo)){
            FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
            Stage stage = (Stage) (((Node)ev.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }else {
            validation.setText("Incorrect Username or Password!");
            setRed(Email,Password);
            Password.setText(null);
        }
    }

    @FXML
    void SignupButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("UserSignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node)ev.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    ObservableList<Info> userinfo = FXCollections.observableArrayList();
}
