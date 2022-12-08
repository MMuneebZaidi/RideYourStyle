package codes.rideyourstyle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class FindCarController implements Initializable {
    @FXML
    void backButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node)ev.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private ChoiceBox<String> Engine ;
    @FXML
    private ChoiceBox<String> Mileage ;
    @FXML
    private ChoiceBox<String> Price ;
    @FXML
    private ChoiceBox<String> BodyType ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDatabaseLink();
        String[] engineRanges = {"1500 - 2990","3000 - 4490","4500 - 5990","6000 - 7490"};
        String[] bodyType = {"Sadan","SUV","Coupe"};

    }

    ObservableList<Vehicle> allVehicles = FXCollections.observableArrayList();


}
