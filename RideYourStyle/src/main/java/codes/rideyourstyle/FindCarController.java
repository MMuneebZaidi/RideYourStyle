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
import java.util.*;


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



    int minPrice=10000000;
    int maxPrice=30000000;
    ObservableList<Vehicle> allVehicles = FXCollections.observableArrayList();

    String[] engineRanges = {"Default","1500 cc - 2999 cc","3000 cc - 4499 cc","4500 cc - 5999 cc","6000 cc - 7499 cc"};
    String[] bodyType = {"Default","Sedan","SUV","Coupe"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allVehicles = RideYouStyle.allVehicles;

        Engine.getItems().addAll(engineRanges);
        BodyType.getItems().addAll(bodyType);

        minPriceSlider.valueProperty().addListener((observableValue, number, t1) -> {
            minPrice = (int) minPriceSlider.getValue();
            minPriceLabel.setText(Integer.toString(minPrice));
        });
        maxPriceSlider.valueProperty().addListener((observableValue, number, t1) -> {
            maxPrice = (int) maxPriceSlider.getValue();
            maxPriceLabel.setText(Integer.toString(maxPrice));
        });

        Engine.getSelectionModel().selectFirst();
        BodyType.getSelectionModel().selectFirst();
    }
    ArrayList<String> extractedNames = new ArrayList<>();
    ObservableList<Vehicle> extractedVehicles = FXCollections.observableArrayList();

    int minEngine;
    int maxEngine;

    public void clearAll(){

        extractedVehicles.clear();
        extractedNames.clear();
        vehicleListView.getItems().clear();
    }

    public void applyFilters(){

        switch (Engine.getValue()) {
            case "1500 cc - 2999 cc" -> {
                minEngine = 1500;
                maxEngine = 2999;
            }
            case "3000 cc - 4499 cc" -> {
                minEngine = 3000;
                maxEngine = 4499;
            }
            case "4500 cc - 5999 cc" -> {
                minEngine = 4500;
                maxEngine = 5999;
            }
            case "6000 cc - 7499 cc" -> {
                minEngine = 6000;
                maxEngine = 7499;
            }
            default -> {
                minEngine = 1500;
                maxEngine = 7499;
            }
        }
        extractedVehicles.removeIf(vehicle -> !(Integer.parseInt(vehicle.engine) >= minEngine && Integer.parseInt(vehicle.engine) <= maxEngine));
        switch (BodyType.getValue()) {
            case "Sedan" -> extractedVehicles.removeIf(vehicle -> !(Objects.equals(vehicle.bodyType, "Sedan")));
            case "SUV" -> extractedVehicles.removeIf(vehicle -> !(Objects.equals(vehicle.bodyType, "SUV")));
            case "Coupe" -> extractedVehicles.removeIf(vehicle -> !(Objects.equals(vehicle.bodyType, "Coupe")));
            default -> {}
        }
        extractedVehicles.removeIf(vehicle -> !(Integer.parseInt(vehicle.price) >= minPrice && Integer.parseInt(vehicle.price) <= maxPrice));

    }

    public void sortCars(){

        for(int i=0;i<extractedVehicles.size();i++) {
            for (int j = 0; j < extractedVehicles.size(); j++) {
                if (Double.parseDouble(extractedVehicles.get(i).rating) > Double.parseDouble(extractedVehicles.get(j).rating)){
                    Collections.swap(extractedVehicles, j, i);
                }
            }
        }
    }
    String car;
    CarDataSingleton data = CarDataSingleton.getInstance();
    void setCar(Vehicle vehicle){
        car = vehicle.name;
    }
    public static String FXMLSelector;
    @FXML
    void searchButton(){

        clearAll();

        extractedVehicles.addAll(allVehicles);

        applyFilters();

        sortCars();

        for (Vehicle vehicle : extractedVehicles){
            extractedNames.add(vehicle.name);
        }
        if(extractedNames.isEmpty()){
            vehicleListView.getItems().add("No Item Found");
        }

        vehicleListView.getItems().addAll(extractedNames);

        vehicleListView.setOnMouseClicked(event -> {
            FXMLSelector="Finding";
            for(Vehicle vehicle : extractedVehicles){
                if(Objects.equals(vehicleListView.getSelectionModel().getSelectedItem(), vehicle.name)){
                    setCar(vehicle);
                    data.setVehicle(car);
                    FXMLLoader fxmlLoader1 = new FXMLLoader(RideYouStyle.class.getResource("CarDetail.fxml"));
                    Scene scene;
                    try {
                        scene = new Scene(fxmlLoader1.load(), 1080, 720);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage stage =  (Stage) vehicleListView.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }
            }
        });
    }

}









