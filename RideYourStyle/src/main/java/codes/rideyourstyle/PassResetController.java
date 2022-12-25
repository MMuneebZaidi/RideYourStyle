package codes.rideyourstyle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class PassResetController implements Initializable {
    @FXML
    private TextField pass_show;
    @FXML
    private PasswordField Password;
    @FXML
    private TextField repass_show;
    @FXML
    private PasswordField repassField;
    @FXML
    private Label passError;
    @FXML
    private Label repassError;
    @FXML
    private CheckBox pass_toggle;

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
    private void removeRed(TextField tf, PasswordField pf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("error"));
        ObservableList<String> styleClass2 = pf.getStyleClass();
        styleClass2.removeAll(Collections.singleton("error"));
    }
    @FXML
    public void showPassword() {
        if (pass_toggle.isSelected()) {
            repass_show.setText(repassField.getText());
            repass_show.setVisible(true);
            repassField.setVisible(false);
            return;
        }
        Password.setText(repass_show.getText());
        repassField.setVisible(true);
        repass_show.setVisible(false);
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
            passError.setText("Enter password!");
            setRed(pass_show,Password);
            flag = false;
        } else if (Password.getText().length()<8) {
            passError.setText("Length should be greater then 8 !");
            setRed(pass_show,Password);
            flag = false;
        } else if (!strongPass()){
            passError.setText("Must have numbers & digits!");
            setRed(pass_show,Password);
            flag = false;
        }
        return flag;
    }
    @FXML
    void resetButton(ActionEvent ev) throws SQLException, IOException {
        removeRed(pass_show,Password);
        removeRed(repass_show,repassField);
        if(validatePassword()){
            removeRed(pass_show,Password);
            passError.setText(null);
            if(Password.getText().equals(repassField.getText())){
                String primary = UserLoginController.data.Email;
                String query = "UPDATE `user` SET `Password` = '"+Password.getText()+"' WHERE `user`.`Email` = '"+primary+"'";
                LoginDatabaseConnection LDC = new LoginDatabaseConnection();
                Connection connectDB = LDC.getDatabaseLink();
                Statement stm = connectDB.createStatement();
                stm.execute(query);
                FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("UserLogin.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
                Stage stage = (Stage) (((Node)ev.getSource()).getScene().getWindow());
                stage.setScene(scene);
                stage.show();
            }else {
                repassError.setText("Password didn't match!");
                setRed(pass_show,Password);
                setRed(repass_show,repassField);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pass_show.setVisible(false);
        repass_show.setVisible(false);
    }
}
