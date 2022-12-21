package codes.rideyourstyle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.sql.SQLException;

public class CarGridController {
    @FXML
    private Label CarName;
    @FXML
    private Label CarPrice;
    @FXML
    private Label CarEngine;
    @FXML
    private Label CarMileage;
    @FXML
    private Label CarTransmissionType;
    @FXML
    private ImageView CarImage;

    void setDetails(Vehicle vehicle) throws SQLException {
        CarName.setText(vehicle.name);
        CarPrice.setText("Rs. "+vehicle.price);
        CarEngine.setText(vehicle.engine+" cc");
        CarMileage.setText(vehicle.mileage + " km/L");
        CarTransmissionType.setText(vehicle.transmissionType);
        InputStream is = vehicle.image.getBinaryStream();
        CarImage.setImage(new Image(is));
    }
}
