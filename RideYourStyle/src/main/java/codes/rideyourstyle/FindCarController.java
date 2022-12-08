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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private ChoiceBox<String> BodyType ;
    @FXML
    private Slider minPriceSlider;
    @FXML
    private Slider maxPriceSlider;
    @FXML
    private Label minPriceLabel;
    @FXML
    private Label maxPriceLabel;
    @FXML
    private ListView<String> vehicleListView;


    int minPrice;
    int maxPrice;
    ObservableList<String> allVehiclesName = FXCollections.observableArrayList();

    String[] engineRanges = {"1500 - 2999","3000 - 4499","4500 - 5999","6000 - 7499"};
    String[] bodyType = {"Sadan","SUV","Coupe"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getDatabaseLink();
        Engine.getItems().addAll(engineRanges);
        BodyType.getItems().addAll(bodyType);
        try{
            Statement stm = connectDB.createStatement();
            String query =  "(select name from bentley)union" +
                            "(select name from bmw)union" +
                            "(select name from chevrolet)union" +
                            "(select name from mercedes)union" +
                            "(select name from porsche)union" +
                            "(select name from rollsroyce)";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                String carName = rs.getString("name");
                allVehiclesName.add(carName);
            }
        }catch (Exception e){
            Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE,null,e);
        }
        vehicleListView.getItems().addAll(allVehiclesName);
        minPriceSlider.valueProperty().addListener((observableValue, number, t1) -> {
            minPrice = (int) minPriceSlider.getValue();
            minPriceLabel.setText(Integer.toString(minPrice));
        });
        maxPriceSlider.valueProperty().addListener((observableValue, number, t1) -> {
            maxPrice = (int) maxPriceSlider.getValue();
            maxPriceLabel.setText(Integer.toString(maxPrice));
        });
    }
}



