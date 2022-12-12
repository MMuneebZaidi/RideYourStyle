package codes.rideyourstyle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ComparedScene implements Initializable {
    @FXML
    void compBackButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Compare.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node) ev.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private ListView<String> car1list;

    @FXML
    private ListView<String> car2list;

    ObservableList<Vehicle> list = FXCollections.observableArrayList();
    Vehicle car1 , car2;
    DataSingleton data = DataSingleton.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            String s1 = data.getCar1();
            String s2 = data.getCar2();
            list = RideYouStyle.allVehicles;
            for(int i=0;i<20;i++){
                if(list.get(i).name.equals(s1))
                    car1 = list.get(i);
                else if(list.get(i).name.equals(s2))
                    car2 = list.get(i);
            }
            car1list.getItems().addAll(car1.name,car1.engine, car1.transmissionType,car1.enginePower,car1.topSpeed,car1.acceleration,car1.mileage,car1.fuelType,car1.fuelCapacity,car1.bodyType,car1.seatingCapacity,car1.doors,car1.wheelSize,car1.convertible,car1.rating,car1.price,car1.stock);
            car2list.getItems().addAll(car2.name,car2.engine, car2.transmissionType,car2.enginePower,car2.topSpeed,car2.acceleration,car2.mileage,car2.fuelType,car2.fuelCapacity,car2.bodyType,car2.seatingCapacity,car2.doors,car2.wheelSize,car2.convertible,car2.rating,car2.price,car2.stock);
    }
}
