package codes.rideyourstyle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class CarDetailsController implements Initializable {
    @FXML
    void backButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader;
        if(Objects.equals(FindCarController.FXMLSelector, "Finding")){
            fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Finding.fxml"));
        }else
            fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node) ev.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
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
    }
}
