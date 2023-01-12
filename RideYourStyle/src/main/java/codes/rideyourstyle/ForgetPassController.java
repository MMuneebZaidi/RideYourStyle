package codes.rideyourstyle;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForgetPassController implements Initializable {
    @FXML
    private Label validation;

    CarDataSingleton data = CarDataSingleton.getInstance();
    @FXML
    private JFXTextField Phone;
    @FXML
    void backButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYourStyle.class.getResource("UserLogin.fxml"));
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        Scene scene;
        if (stage.isMaximized()) {
            scene = new Scene(fxmlLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        } else {
            scene = new Scene(fxmlLoader.load());
        }
        stage.setScene(scene);
    }
    private void setRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        if(!styleClass.contains("error")) {
            styleClass.add("error");
        }
    }
    private boolean validatePhone(){
        boolean flag = true;
        if(Phone.getText().isEmpty()){
            validation.setText("Enter Phone Number!");
            setRed(Phone);
            flag = false;
        } else if (Phone.getText().length()<11) {
            validation.setText("Enter valid Phone Number!");
            setRed(Phone);
            flag=false;
        }
        return flag;
    }
    @FXML
    void resetButton(ActionEvent ev) throws IOException {
        if(validatePhone()){
            if(UserLoginController.data.PhoneNumber.equals(Phone.getText())){
                data.setPass_name("Reset Password");
                FXMLLoader fxmlLoader = new FXMLLoader(RideYourStyle.class.getResource("PasswordReset.fxml"));
                Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
                Stage stage = (Stage) (((Node) ev.getSource()).getScene().getWindow());
                Scene scene;
                if (stage.isMaximized()) {
                    scene = new Scene(fxmlLoader.load(), screenSize.getWidth(), screenSize.getHeight());
                } else {
                    scene = new Scene(fxmlLoader.load());
                }
                stage.setScene(scene);
                stage.show();
            }else{
                setRed(Phone);
                validation.setText("Phone number didn't match!");
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Phone.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                Phone.setText(newValue.replaceAll("\\D", ""));
            }
            if (Phone.getText().length() > 11) {
                String s = Phone.getText().substring(0, 11);
                Phone.setText(s);
            }
        });
    }
}
