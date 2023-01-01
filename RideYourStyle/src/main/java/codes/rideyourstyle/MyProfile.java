package codes.rideyourstyle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    private Label CNIC;

    @FXML
    private Label Username;

    @FXML
    private Label age;

    @FXML
    private Label city;

    @FXML
    private Label email;

    @FXML
    private Label name;

    @FXML
    private Label phone;
    @FXML
    void HomeButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void backButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void changeNumber(ActionEvent event) throws IOException {
        data.setPass_name("Change Number");
        data.setName1("PhoneNo.");
        data.setName2("Enter new Phone Number");
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("PasswordReset.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void changePassword(ActionEvent event) throws IOException {
        data.setPass_name("Change Password");
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("PasswordReset.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void changeUsername(ActionEvent event) throws IOException {
        data.setPass_name("Change Username");
        data.setName1("Username");
        data.setName2("Enter new Username");
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("PasswordReset.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LoginDatabaseConnection link = new LoginDatabaseConnection();
            Connection connectDB = link.getDatabaseLink();
            Statement stm = connectDB.createStatement();
            String query = "SELECT `Name`, `Username`, `Email`, `CNIC`, `Phone Number`, `Age`, `City` FROM `user` WHERE id = '" + UserLoginController.loggedIn.id + "'";
            ResultSet output = stm.executeQuery(query);
            while (output.next()){
                name.setText(output.getString("Name"));
                Username.setText(output.getString("Username"));
                email.setText(output.getString("Email"));
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