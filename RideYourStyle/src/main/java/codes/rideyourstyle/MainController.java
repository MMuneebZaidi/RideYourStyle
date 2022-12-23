package codes.rideyourstyle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MainController implements Initializable {
    @FXML
    private ListView<String> searchlistView;
    @FXML
    private ScrollPane searchscroll;
    public static String FXMLSelector;

    @FXML
    private TextField searchBar;
    ArrayList<String> carName = new ArrayList<>();

    String car;
    CarDataSingleton data = CarDataSingleton.getInstance();
    void setCar(Vehicle vehicle){
        car = vehicle.name;
    }
    ObservableList<Vehicle> allVehicles = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getDatabaseLink();
            Statement stmt = connectDB.createStatement();
            String query = "(select name from bentley)union" +
                    "(select name from bmw)union" +
                    "(select name from chevrolet)union" +
                    "(select name from mercedes)union" +
                    "(select name from porsche)union" +
                    "(select name from rollsroyce)";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                carName.add(rs.getString("name"));
            }
            searchlistView.getItems().addAll(carName);
        }catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE,null,e);
        }
        allVehicles = RideYouStyle.allVehicles;
        searchlistView.setOnMouseClicked(event -> {
            FXMLSelector="Main";
            for(Vehicle vehicle : allVehicles){
                if(Objects.equals(searchlistView.getSelectionModel().getSelectedItem(), vehicle.name)){
                    setCar(vehicle);
                    data.setVehicle(car);
                    FXMLLoader fxmlLoader1 = new FXMLLoader(RideYouStyle.class.getResource("CarDetail.fxml"));
                    Scene scene;
                    try {
                        scene = new Scene(fxmlLoader1.load(), 1080, 720);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage stage =  (Stage) searchlistView.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }
            }
        });
    }
    @FXML
    void search() {

        if(searchBar.getText().isEmpty()){
            searchlistView.setVisible(false);
            searchscroll.setVisible(false);
        }
        else{
            searchlistView.getItems().clear();
            searchlistView.getItems().addAll(searchList(searchBar.getText(),carName));
            if(searchlistView.getItems().isEmpty()){
                searchlistView.getItems().clear();
                searchlistView.getItems().addAll("No Car Found");
            }
                searchlistView.setVisible(true);
                searchscroll.setVisible(true);
        }

    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> searchWordsArray.stream().allMatch(word ->
                input.toLowerCase().contains(word.toLowerCase()))).collect(Collectors.toList());
    }
    @FXML
    void findButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Finding.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node)ev.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void CompareButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Compare.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    Parent root;
    public static String Company;
    public static String Logo;
    @FXML
    void MercedesButton(ActionEvent event) throws IOException {
        Company="Mercedes";
        Logo="E:\\JavaFX Codes\\SemProject\\CarIMDB\\RideYourStyle\\src\\main\\resources\\images\\logos\\mercedes-logo.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Scene scene = new Scene(root, 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void BMWButton(ActionEvent event) throws IOException {
        Company="BMW";
        Logo="E:\\JavaFX Codes\\SemProject\\CarIMDB\\RideYourStyle\\src\\main\\resources\\images\\logos\\bmwlogo.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Scene scene = new Scene(root, 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void CheveroletButton(ActionEvent event) throws IOException {
        Company="Cheverolet";
        Logo="E:\\JavaFX Codes\\SemProject\\CarIMDB\\RideYourStyle\\src\\main\\resources\\images\\logos\\CheveroletLogo.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Scene scene = new Scene(root, 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void PorscheButton(ActionEvent event) throws IOException {
        Company="Porsche";
        Logo="E:\\JavaFX Codes\\SemProject\\CarIMDB\\RideYourStyle\\src\\main\\resources\\images\\logos\\Porsche.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Scene scene = new Scene(root, 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void RollsRoyceButton(ActionEvent event) throws IOException {
        Company="Rolls Royce";
        Logo="E:\\JavaFX Codes\\SemProject\\CarIMDB\\RideYourStyle\\src\\main\\resources\\images\\logos\\rollsroyce.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Scene scene = new Scene(root, 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void BentlyButton(ActionEvent event) throws IOException {
        Company="Bently";
        Logo="E:\\JavaFX Codes\\SemProject\\CarIMDB\\RideYourStyle\\src\\main\\resources\\images\\logos\\bently.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Scene scene = new Scene(root, 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void AllCarsButton(ActionEvent event) throws IOException {
        Company="All Cars";
        Logo="E:\\JavaFX Codes\\SemProject\\CarIMDB\\RideYourStyle\\src\\main\\resources\\images\\logos\\ALL-CARS.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Scene scene = new Scene(root, 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
}