package codes.rideyourstyle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarDetailsController implements Initializable {

    @FXML
    private Button AddGarageButton;

    @FXML
    void HomeButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void backButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader;
        if(Objects.equals(FindCarController.FXMLSelector, "Finding")){
            fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Finding.fxml"));
        }
        else if(Objects.equals(MainController.FXMLSelector, "Main")){
            fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
        }
        else
            fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
            Stage stage = (Stage) (((Node) ev.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    void AddGarageButton () {
        CarDataSingleton garage= CarDataSingleton.getInstance();
        System.out.println(UserLoginController.loggedIn.id);
        LoginDatabaseConnection db = new LoginDatabaseConnection();
        try {
            Connection connectDB = db.getDatabaseLink();
            Statement stm = connectDB.createStatement();
            String query = "SELECT car1, car2 , car3 , car4 , car5 FROM garage WHERE user_id = '"+UserLoginController.loggedIn.id+"'";
            ResultSet output = stm.executeQuery(query);

            while (output.next()){
                if(output.getString("car1") == null){
                    String q = "UPDATE `garage` SET car1 = '"+garage.getVehicle()+"' WHERE user_id = '"+UserLoginController.loggedIn.id+"'";
                    Statement st1 = connectDB.createStatement();
                    st1.execute(q);
                    break;
                }
                else if(output.getString("car2") == null){
                    String q = "UPDATE `garage` SET car2 = '"+garage.getVehicle()+"'WHERE user_id = '"+UserLoginController.loggedIn.id+"'";
                    Statement st2 = connectDB.createStatement();
                    st2.execute(q);
                    break;
                }
                else if(output.getString("car3") == null){
                    String q = "UPDATE `garage` SET car3 = '"+garage.getVehicle()+"'WHERE user_id = '"+UserLoginController.loggedIn.id+"'";
                    Statement st3 = connectDB.createStatement();
                    st3.execute(q);
                    break;
                }
                else if(output.getString("car4") == null){
                    String q = "UPDATE `garage` SET car4 = '"+garage.getVehicle()+"'WHERE user_id = '"+UserLoginController.loggedIn.id+"'";
                    Statement st4 = connectDB.createStatement();
                    st4.execute(q);
                    break;
                } else if(output.getString("car5") == null){
                    String q = "UPDATE `garage` SET car5 = '"+garage.getVehicle()+"'WHERE user_id = '"+UserLoginController.loggedIn.id+"'";
                    Statement st5 = connectDB.createStatement();
                    st5.execute(q);
                    break;
                } else if (output.getString("car5") != null) {
                    Alert a1 = new Alert(Alert.AlertType.WARNING,
                            "Maximum Limit to add Cars Reached!", ButtonType.OK);
                    a1.showAndWait();
                }
            }
        }catch (Exception e) {
            Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    @FXML
    private Label vehicleName;
    @FXML
    private ListView<String> vehicleList;
    @FXML
    private ImageView vehicleImage;
    ObservableList<Vehicle> list = FXCollections.observableArrayList();
    Vehicle vehicle;
    CarDataSingleton data = CarDataSingleton.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String v1 = data.getVehicle();
        list = RideYouStyle.allVehicles;
        for (Vehicle value : list) {
            if (value.name.equals(v1))
                vehicle = value;
        }
        vehicleList.getItems().addAll(vehicle.engine, vehicle.transmissionType,vehicle.enginePower,vehicle.topSpeed,vehicle.acceleration,vehicle.mileage,vehicle.fuelType,vehicle.fuelCapacity,vehicle.bodyType,vehicle.seatingCapacity,vehicle.doors,vehicle.wheelSize,vehicle.convertible,vehicle.rating,vehicle.price,vehicle.stock);
        InputStream is;
        try {
            is = vehicle.image.getBinaryStream();
            vehicleImage.setImage(new Image(is));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        vehicleName.setText(vehicle.name);
        if(UserLoginController.loggedIn==null){
            AddGarageButton.setDisable(true);
        }
    }

}
