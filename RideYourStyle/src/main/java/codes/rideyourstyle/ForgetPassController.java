package codes.rideyourstyle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForgetPassController implements Initializable {
    @FXML
    private Label validation;

    CarDataSingleton data = CarDataSingleton.getInstance();
    @FXML
    private TextField Phone;
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
                data.setPass_name("Change Password");
                FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("PasswordReset.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
                Stage stage = (Stage) (((Node)ev.getSource()).getScene().getWindow());
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
