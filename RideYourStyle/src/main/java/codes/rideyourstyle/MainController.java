package codes.rideyourstyle;

import com.jfoenix.controls.*;
import java.time.LocalDate;
import java.time.Month;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MainController implements Initializable {
    @FXML
    private JFXListView<String> searchlistView;
    @FXML
    private JFXScrollPane searchscroll;
    public static String FXMLSelector;
    @FXML
    private JFXDrawer profiledraw;

    @FXML
    private VBox profv;

    @FXML
    private JFXButton ProfileB;
    @FXML
    private JFXButton MyGarage;
    @FXML
    private JFXButton MyRecord;
    @FXML
    private JFXTextField searchBar;

    @FXML
    private JFXButton LogoutButton = new JFXButton();

    @FXML
    public JFXButton userloginbutton = new JFXButton();

    ArrayList<String> carName = new ArrayList<>();

    String car;
    CarDataSingleton data = CarDataSingleton.getInstance();
    void setCar(Vehicle vehicle){
        car = vehicle.name;
    }
    ObservableList<Vehicle> allVehicles = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userloginbutton.setVisible(false);
        profiledraw.setSidePane(profv);
        allVehicles = RideYouStyle.allVehicles;
        for (Vehicle vehicle: allVehicles){
            carName.add(vehicle.name);
        }
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
     if(UserLoginController.loggedIn==null){
         userloginbutton.setVisible(true);
         LogoutButton.setVisible(false);
         ProfileB.setDisable(true);
         MyRecord.setDisable(true);
         MyGarage.setDisable(true);
     }
    }
    @FXML
    void Profile() {
        if(profiledraw.isShown())
            profiledraw.close();
        if(profiledraw.isHidden())
            profiledraw.open();
    }
    @FXML
    void profile(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void MyCartButton (ActionEvent event) throws IOException {
        ObservableList<Vehicle> test = FXCollections.observableArrayList();
        if(Garage.car1!=null){
            test.add(Garage.car1);
        }
        if(Garage.car2!=null){
            test.add(Garage.car2);
        }
        if(Garage.car3!=null){
            test.add(Garage.car3);
        }
        if(Garage.car4!=null){
            test.add(Garage.car4);
        }
        if(Garage.car5!=null){
            test.add(Garage.car5);
        }
        Garage.setCars(test);
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("AddToGarage.fxml"));
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        Scene scene;
        if (stage.isMaximized()) {
            scene = new Scene(fxmlLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        } else {
            scene = new Scene(fxmlLoader.load());
        }
        stage.setScene(scene);
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
    void PurchaseHistoryButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("PurchaseHistory.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node)ev.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
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
    @FXML
    void LogoutButton(ActionEvent event) throws IOException {
        UserLoginController.loggedIn=null;
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("UserLogin.fxml"));
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        Scene scene;
        if (stage.isMaximized()) {
            scene = new Scene(fxmlLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        } else {
            scene = new Scene(fxmlLoader.load());
        }
        stage.setScene(scene);
    }
    @FXML
    void switchButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("AdminLogin.fxml"));
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        Scene scene;
        if (stage.isMaximized()) {
            scene = new Scene(fxmlLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        } else {
            scene = new Scene(fxmlLoader.load());
        }
        stage.setScene(scene);
    }
    @FXML
    void UserLoginButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("UserLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("RideYourStyleLOGO.png"));
        stage.setResizable(true);
        stage.setTitle("Ride Your Style");
        stage.setScene(scene);
        stage.show();
        ((Stage)(((Node)ev.getSource()).getScene().getWindow())).close();
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
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        Scene scene;
        if (stage.isMaximized()) {
            scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        } else {
            scene = new Scene(root);
        }
        stage.setScene(scene);
    }
    @FXML
    void BMWButton(ActionEvent event) throws IOException {
        Company="BMW";
        Logo="E:\\JavaFX Codes\\SemProject\\CarIMDB\\RideYourStyle\\src\\main\\resources\\images\\logos\\bmwlogo.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        Scene scene;
        if (stage.isMaximized()) {
            scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        } else {
            scene = new Scene(root);
        }
        stage.setScene(scene);
    }
    @FXML
    void CheveroletButton(ActionEvent event) throws IOException {
        Company="Cheverolet";
        Logo="E:\\JavaFX Codes\\SemProject\\CarIMDB\\RideYourStyle\\src\\main\\resources\\images\\logos\\CheveroletLogo.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        Scene scene;
        if (stage.isMaximized()) {
            scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        } else {
            scene = new Scene(root);
        }
        stage.setScene(scene);
    }
    @FXML
    void PorscheButton(ActionEvent event) throws IOException {
        Company="Porsche";
        Logo="E:\\JavaFX Codes\\SemProject\\CarIMDB\\RideYourStyle\\src\\main\\resources\\images\\logos\\Porsche.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        Scene scene;
        if (stage.isMaximized()) {
            scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        } else {
            scene = new Scene(root);
        }
        stage.setScene(scene);
    }
    @FXML
    void RollsRoyceButton(ActionEvent event) throws IOException {
        Company="Rolls Royce";
        Logo="E:\\JavaFX Codes\\SemProject\\CarIMDB\\RideYourStyle\\src\\main\\resources\\images\\logos\\rollsroyce.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        Scene scene;
        if (stage.isMaximized()) {
            scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        } else {
            scene = new Scene(root);
        }
        stage.setScene(scene);
    }
    @FXML
    void BentlyButton(ActionEvent event) throws IOException {
        Company="Bently";
        Logo="E:\\JavaFX Codes\\SemProject\\CarIMDB\\RideYourStyle\\src\\main\\resources\\images\\logos\\bently.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        Scene scene;
        if (stage.isMaximized()) {
            scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        } else {
            scene = new Scene(root);
        }
        stage.setScene(scene);
    }
    @FXML
    void AllCarsButton(ActionEvent event) throws IOException {
        Company="All Cars";
        Logo="E:\\JavaFX Codes\\SemProject\\CarIMDB\\RideYourStyle\\src\\main\\resources\\images\\logos\\ALL-CARS.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        Scene scene;
        if (stage.isMaximized()) {
            scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        } else {
            scene = new Scene(root);
        }
        stage.setScene(scene);
    }
}