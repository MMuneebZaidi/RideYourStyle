package codes.rideyourstyle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ForgetPassController {
    @FXML
    private Label validation;
    @FXML
    private TextField Phone;
    private void setRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        if(!styleClass.contains("error")) {
            styleClass.add("error");
        }
    }
    @FXML
    void resetButton(ActionEvent ev) throws IOException {
        if(UserLoginController.data.PhoneNumber.equals(Phone.getText())){
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
