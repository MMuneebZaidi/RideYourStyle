package codes.rideyourstyle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import java.util.ArrayList;
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
        if(Garage.car1 == null){
            for(Vehicle vehicle : RideYouStyle.allVehicles){
                if (garage.getVehicle().equals(vehicle.name)){
                    Garage.car1=vehicle;
                }
            }
        }else if(Garage.car2 == null){
            for(Vehicle vehicle : RideYouStyle.allVehicles){
                if (garage.getVehicle().equals(vehicle.name)){
                    Garage.car2=vehicle;
                }
            }
        }
        else if(Garage.car3 == null){
            for(Vehicle vehicle : RideYouStyle.allVehicles){
                if (garage.getVehicle().equals(vehicle.name)){
                    Garage.car3=vehicle;
                }
            }
        }
        else if(Garage.car4 == null){
            for(Vehicle vehicle : RideYouStyle.allVehicles){
                if (garage.getVehicle().equals(vehicle.name)){
                    Garage.car4=vehicle;
                }
            }
        }
        else if(Garage.car5 == null){
            for(Vehicle vehicle : RideYouStyle.allVehicles){
                if (garage.getVehicle().equals(vehicle.name)){
                    Garage.car5=vehicle;
                }
            }
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
