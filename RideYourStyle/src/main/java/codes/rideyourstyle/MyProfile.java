package codes.rideyourstyle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyProfile implements Initializable {
    CarDataSingleton data = CarDataSingleton.getInstance();
    @FXML
    private TextField CNIC;

    @FXML
    private TextField Username;
    @FXML
    private PasswordField Pass;

    @FXML
    private TextField age;

    @FXML
    private TextField city;

    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;
    @FXML
    void HomeButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYourStyle.class.getResource("Main.fxml"));
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

    @FXML
    void backButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYourStyle.class.getResource("Main.fxml"));
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

    @FXML
    void changeNumber(ActionEvent event) throws IOException {
        data.setPass_name("Change Number");
        data.setName1("PhoneNo.");
        data.setName2("Enter new Phone Number");
        FXMLLoader fxmlLoader = new FXMLLoader(RideYourStyle.class.getResource("PasswordReset.fxml"));
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

    @FXML
    void changePassword(ActionEvent event) throws IOException {
        data.setPass_name("Change Password");
        FXMLLoader fxmlLoader = new FXMLLoader(RideYourStyle.class.getResource("PasswordReset.fxml"));
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

    @FXML
    void changeUsername(ActionEvent event) throws IOException {
        data.setPass_name("Change Username");
        data.setName1("Username");
        data.setName2("Enter new Username");
        FXMLLoader fxmlLoader = new FXMLLoader(RideYourStyle.class.getResource("PasswordReset.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LoginDatabaseConnection link = new LoginDatabaseConnection();
            Connection connectDB = link.getDatabaseLink();
            Statement stm = connectDB.createStatement();
            String query = "SELECT * FROM `user` WHERE id = '" + UserLoginController.loggedIn.id + "'";
            ResultSet output = stm.executeQuery(query);
            while (output.next()){
                name.setText(output.getString("Name"));
                Username.setText(output.getString("Username"));
                email.setText(output.getString("Email"));
                Pass.setText(output.getString("Password"));
                CNIC.setText(output.getString("CNIC"));
                phone.setText(output.getString("Phone Number"));
                age.setText(String.valueOf(output.getInt("Age")));
                city.setText(output.getString("City"));
            }
        } catch (Exception e) {
            Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}