package codes.rideyourstyle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
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
    private JFXPasswordField Password;
    @FXML
    private Label name;

    @FXML
    private Label passError;

    @FXML
    private JFXTextField pass_show;

    @FXML
    private CheckBox pass_toggle;

    @FXML
    private JFXTextField reText;

    @FXML
    private Label repassError;

    @FXML
    private JFXPasswordField repassField;

    @FXML
    private JFXTextField repass_show;

    @FXML
    private JFXTextField text;

    CarDataSingleton data = CarDataSingleton.getInstance();

    @FXML
    void backButton(ActionEvent event) throws IOException {
        if(data.getPass_name().equals("Reset Password")){
            FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("UserLogin.fxml"));
            Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            Scene scene;
            if (stage.isMaximized()) {
                scene = new Scene(fxmlLoader.load(), screenSize.getWidth(), screenSize.getHeight());
            } else {
                scene = new Scene(fxmlLoader.load());
            }
            stage.setScene(scene);
        }else {
            FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Profile.fxml"));
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
    private void setRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        if(!styleClass.contains("error")) {
            styleClass.add("error");
        }
    }
    private void removeRed(TextField tf, PasswordField pf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("error"));
        ObservableList<String> styleClass2 = pf.getStyleClass();
        styleClass2.removeAll(Collections.singleton("error"));
    }
    private void removeRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("error"));
    }
    @FXML
    public void showPassword() {
        if (pass_toggle.isSelected()) {
            repass_show.setText(repassField.getText());
            repass_show.setVisible(true);
            repassField.setVisible(false);
            return;
        }
        repassField.setText(repass_show.getText());
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
    private boolean validateUsername(){
        ArrayList<Character> a = new ArrayList<>();
        String b="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i=0;i<b.length();i++)
        {
            a.add(b.charAt(i));
        }
        boolean flag = true;
        if(text.getText().isEmpty()){
            passError.setText("Name cannot be empty!");
            setRed(text);
            flag = false;
        } else if (!a.contains(text.getText().charAt(0)) && !Character.isWhitespace(text.getText().charAt(0))) {
            passError.setText("First letter must be an Alphabet!");
            setRed(text);
            flag=false;
        } else if (text.getText().contains(" ")) {
            passError.setText("No spaces are allowed!");
            setRed(text);
            flag = false;
        }
        return flag;
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
            passError.setText("Must have numbers & alphabets!");
            setRed(pass_show,Password);
            flag = false;
        }
        return flag;
    }
    private boolean validatePhone(){
        boolean flag = true;
        if(text.getText().isEmpty()){
            text.setText("Enter Phone Number!");
            setRed(text);
            flag = false;
        } else if (text.getText().length()<11) {
            text.setText("Enter valid Phone Number!");
            setRed(text);
            flag=false;
        }
        return flag;
    }
    @FXML
    void resetButton(ActionEvent ev) throws SQLException, IOException {
        if(data.getPass_name().equals("Change Password") || data.getPass_name().equals("Reset Password")) {
            removeRed(pass_show, Password);
            removeRed(repass_show, repassField);
            if (validatePassword()) {
                removeRed(pass_show, Password);
                passError.setText(null);
                if (Password.getText().equals(repassField.getText())) {
                    String primary = UserLoginController.data.Email;
                    String query = "UPDATE `user` SET `Password` = '" + Password.getText() + "' WHERE `user`.`Email` = '" + primary + "'";
                    LoginDatabaseConnection LDC = new LoginDatabaseConnection();
                    Connection connectDB = LDC.getDatabaseLink();
                    Statement stm = connectDB.createStatement();
                    stm.execute(query);
                    if(data.getPass_name().equals("Change Password")) {
                        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Profile.fxml"));
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
                    else if(data.getPass_name().equals("Reset Password")){
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
                } else {
                    repassError.setText("Password didn't match!");
                    setRed(pass_show, Password);
                    setRed(repass_show, repassField);
                }
            }
        }
        if(data.getPass_name().equals("Change Username")){
            removeRed(text);
            removeRed(reText);
            if(validateUsername()){
                removeRed(text);
                passError.setText(null);
                if(text.getText().equals(reText.getText())){
                    String primary = UserLoginController.data.Email;
                    String query = "UPDATE `user` SET `Username` = '" + text.getText() + "' WHERE `user`.`Email` = '" + primary + "'";
                    LoginDatabaseConnection LDC = new LoginDatabaseConnection();
                    Connection connectDB = LDC.getDatabaseLink();
                    Statement stm = connectDB.createStatement();
                    stm.execute(query);
                    FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Profile.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
                    Stage stage = (Stage) (((Node) ev.getSource()).getScene().getWindow());
                    stage.setScene(scene);
                    stage.show();
                }
                else {
                    repassError.setText("Username didn't match");
                    setRed(text);
                    setRed(reText);
                }
            }
        }
        if(data.getPass_name().equals("Change Number")){
            removeRed(text);
            removeRed(reText);
            text.setPromptText("Enter new Phone Number");
            reText.setPromptText("Re-enter Phone Number");
            if(validatePhone()){
                removeRed(text);
                passError.setText(null);
                if(text.getText().equals(reText.getText())){
                    String primary = UserLoginController.data.Email;
                    String query = "UPDATE `user` SET `Phone Number` = '" + text.getText() + "' WHERE `user`.`Email` = '" + primary + "'";
                    LoginDatabaseConnection LDC = new LoginDatabaseConnection();
                    Connection connectDB = LDC.getDatabaseLink();
                    Statement stm = connectDB.createStatement();
                    stm.execute(query);
                    FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Profile.fxml"));
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
                else {
                    repassError.setText("Phone Number didn't match");
                    setRed(text);
                    setRed(reText);
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(data.getPass_name().equals("Change Password") || data.getPass_name().equals("Reset Password")) {
            name.setText(data.getPass_name());
        }
        if(data.getPass_name().equals("Change Username")){
            name.setText(data.getPass_name());
            pass_toggle.setVisible(false);
            Password.setVisible(false);
            repassField.setVisible(false);
            text.setVisible(true);
            reText.setVisible(true);
            text.setFocusTraversable(true);
        }
        if(data.getPass_name().equals("Change Number")){
            name.setText(data.getPass_name());
            pass_toggle.setVisible(false);
            Password.setVisible(false);
            repassField.setVisible(false);
            text.setVisible(true);
            reText.setVisible(true);
            text.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    text.setText(newValue.replaceAll("\\D", ""));
                }
                if (text.getText().length() > 11) {
                    String s = text.getText().substring(0, 11);
                    text.setText(s);
                }
            });
            reText.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    reText.setText(newValue.replaceAll("\\D", ""));
                }
                if (reText.getText().length() > 11) {
                    String s = reText.getText().substring(0, 11);
                    reText.setText(s);
                }
            });
        }
        pass_show.setVisible(false);
        repass_show.setVisible(false);
    }
}