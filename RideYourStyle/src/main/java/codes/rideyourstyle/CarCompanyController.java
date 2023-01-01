package codes.rideyourstyle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class CarCompanyController implements Initializable {

    @FXML
    private GridPane carGridList;

    @FXML
    private ImageView companyLOGO;

    @FXML
    private Label companyNAME;
    @FXML
    private ListView<String> searchlistView;
    @FXML
    private ScrollPane searchscroll;
    public static String FXMLSelector;

    @FXML
    private TextField searchBar;

    @FXML
    private Button mycart;
    ArrayList<String> carName = new ArrayList<>();


    @FXML
    void backButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void HomeButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    void setCompany(String company,String logo) {
        companyNAME.setText(company);
        companyLOGO.setImage(new Image(logo));
    }

    public ObservableList<Vehicle> vehicles;

    CarDataSingleton data = CarDataSingleton.getInstance();
    String car;
    void setCar(Vehicle vehicle){
        car = vehicle.name;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vehicles = RideYouStyle.allVehicles;

        companyNAME.setText(MainController.Company);
        companyLOGO.setImage(new Image(MainController.Logo));

        FindCarController.FXMLSelector="";

        int col = 0;
        int row = 1;

        switch (MainController.Company){
            case "Mercedes" -> {

                try {
                    for (Vehicle vehicle : vehicles) {
                        if (vehicle instanceof Mercedes){
                            
                            FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("cargrid.fxml"));
                            AnchorPane anchorPane = fxmlLoader.load();

                            CarGridController cgc = fxmlLoader.getController();
                            cgc.setDetails(vehicle);

                            if (col == 1) {
                                col = 0;
                                row++;
                            }
                            
                            carGridList.add(anchorPane, col++, row);
                            anchorPane.setOnMousePressed(event -> {
                                setCar(vehicle);
                                data.setVehicle(car);
                                FXMLLoader fxmlLoader1 = new FXMLLoader(RideYouStyle.class.getResource("CarDetail.fxml"));
                                Scene scene;
                                try {
                                    scene = new Scene(fxmlLoader1.load(), 1080, 720);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                Stage stage =  (Stage) carGridList.getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            });
                            GridPane.setMargin(anchorPane, new Insets(10));}
                            
                    }
                    for (Vehicle vehicle: vehicles){
                        if(vehicle instanceof Mercedes)
                            carName.add(vehicle.name);
                    }
                    searchlistView.setOnMouseClicked(event -> {
                        FXMLSelector="Mercedes";
                        for(Vehicle vehicle : vehicles){
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
                } catch (IOException e) {
                    e.printStackTrace();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case "Bently" -> {
                try {
                    for (Vehicle vehicle : vehicles) {
                        if (vehicle instanceof Bently){

                            FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("cargrid.fxml"));
                            AnchorPane anchorPane = fxmlLoader.load();

                            CarGridController cgc = fxmlLoader.getController();
                            cgc.setDetails(vehicle);

                            if (col == 1) {
                                col = 0;
                                row++;
                            }

                            carGridList.add(anchorPane, col++, row);
                            anchorPane.setOnMousePressed(event -> {
                                setCar(vehicle);
                                data.setVehicle(car);
                                FXMLLoader fxmlLoader1 = new FXMLLoader(RideYouStyle.class.getResource("CarDetail.fxml"));
                                Scene scene;
                                try {
                                    scene = new Scene(fxmlLoader1.load(), 1080, 720);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                Stage stage =  (Stage) carGridList.getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            });
                            GridPane.setMargin(anchorPane, new Insets(10));
                        }

                    }
                    for (Vehicle vehicle: vehicles){
                        if(vehicle instanceof Bently)
                            carName.add(vehicle.name);
                    }
                    searchlistView.setOnMouseClicked(event -> {
                        FXMLSelector="Bently";
                        for(Vehicle vehicle : vehicles){
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
                } catch (IOException e) {
                    e.printStackTrace();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case "BMW" -> {
                try {
                    for (Vehicle vehicle : vehicles) {
                        if (vehicle instanceof BMW){

                            FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("cargrid.fxml"));
                            AnchorPane anchorPane = fxmlLoader.load();

                            CarGridController cgc = fxmlLoader.getController();
                            cgc.setDetails(vehicle);

                            if (col == 1) {
                                col = 0;
                                row++;
                            }

                            carGridList.add(anchorPane, col++, row);
                            anchorPane.setOnMousePressed(event -> {
                                setCar(vehicle);
                                data.setVehicle(car);
                                FXMLLoader fxmlLoader1 = new FXMLLoader(RideYouStyle.class.getResource("CarDetail.fxml"));
                                Scene scene;
                                try {
                                    scene = new Scene(fxmlLoader1.load(), 1080, 720);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                Stage stage =  (Stage) carGridList.getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            });
                            GridPane.setMargin(anchorPane, new Insets(10));}

                    }
                    for (Vehicle vehicle: vehicles){
                        if(vehicle instanceof BMW)
                            carName.add(vehicle.name);
                    }
                    searchlistView.setOnMouseClicked(event -> {
                        FXMLSelector="BMW";
                        for(Vehicle vehicle : vehicles){
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
                } catch (IOException e) {
                    e.printStackTrace();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case "Rolls Royce" -> {
                try {
                    for (Vehicle vehicle : vehicles) {
                        if (vehicle instanceof RollsRoyce){

                            FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("cargrid.fxml"));
                            AnchorPane anchorPane = fxmlLoader.load();

                            CarGridController cgc = fxmlLoader.getController();
                            cgc.setDetails(vehicle);

                            if (col == 1) {
                                col = 0;
                                row++;
                            }

                            carGridList.add(anchorPane, col++, row);
                            anchorPane.setOnMousePressed(event -> {
                                setCar(vehicle);
                                data.setVehicle(car);
                                FXMLLoader fxmlLoader1 = new FXMLLoader(RideYouStyle.class.getResource("CarDetail.fxml"));
                                Scene scene;
                                try {
                                    scene = new Scene(fxmlLoader1.load(), 1080, 720);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                Stage stage =  (Stage) carGridList.getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            });
                            GridPane.setMargin(anchorPane, new Insets(10));}

                    }
                    for (Vehicle vehicle: vehicles){
                        if(vehicle instanceof RollsRoyce)
                            carName.add(vehicle.name);
                    }
                    searchlistView.setOnMouseClicked(event -> {
                        FXMLSelector="Rolls Royce";
                        for(Vehicle vehicle : vehicles){
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
                } catch (IOException e) {
                    e.printStackTrace();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case "Porsche" -> {
                try {
                    for (Vehicle vehicle : vehicles) {
                        if (vehicle instanceof Porsche){

                            FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("cargrid.fxml"));
                            AnchorPane anchorPane = fxmlLoader.load();

                            CarGridController cgc = fxmlLoader.getController();
                            cgc.setDetails(vehicle);

                            if (col == 1) {
                                col = 0;
                                row++;
                            }

                            carGridList.add(anchorPane, col++, row);
                            anchorPane.setOnMousePressed(event -> {
                                setCar(vehicle);
                                data.setVehicle(car);
                                FXMLLoader fxmlLoader1 = new FXMLLoader(RideYouStyle.class.getResource("CarDetail.fxml"));
                                Scene scene;
                                try {
                                    scene = new Scene(fxmlLoader1.load(), 1080, 720);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                Stage stage =  (Stage) carGridList.getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            });
                            GridPane.setMargin(anchorPane, new Insets(10));}

                    }
                    for (Vehicle vehicle: vehicles){
                        if(vehicle instanceof Porsche)
                            carName.add(vehicle.name);
                    }
                    searchlistView.setOnMouseClicked(event -> {
                        FXMLSelector="Porsche";
                        for(Vehicle vehicle : vehicles){
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
                } catch (IOException e) {
                    e.printStackTrace();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case "Cheverolet" -> {
                try {
                    for (Vehicle vehicle : vehicles) {
                        if (vehicle instanceof Cheverolet){

                            FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("cargrid.fxml"));
                            AnchorPane anchorPane = fxmlLoader.load();

                            CarGridController cgc = fxmlLoader.getController();
                            cgc.setDetails(vehicle);

                            if (col == 1) {
                                col = 0;
                                row++;
                            }

                            carGridList.add(anchorPane, col++, row);
                            anchorPane.setOnMousePressed(event -> {
                                setCar(vehicle);
                                data.setVehicle(car);
                                FXMLLoader fxmlLoader1 = new FXMLLoader(RideYouStyle.class.getResource("CarDetail.fxml"));
                                Scene scene;
                                try {
                                    scene = new Scene(fxmlLoader1.load(), 1080, 720);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                Stage stage =  (Stage) carGridList.getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            });
                            GridPane.setMargin(anchorPane, new Insets(10));}

                    }
                    for (Vehicle vehicle: vehicles){
                        if(vehicle instanceof Cheverolet)
                            carName.add(vehicle.name);
                    }
                    searchlistView.setOnMouseClicked(event -> {
                        FXMLSelector="Cheverolet";
                        for(Vehicle vehicle : vehicles){
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
                } catch (IOException e) {
                    e.printStackTrace();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
            case "All Cars" -> {
                try {
                    for (Vehicle vehicle : vehicles) {

                        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("cargrid.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();

                        CarGridController cgc = fxmlLoader.getController();
                        cgc.setDetails(vehicle);

                        if (col == 1) {
                            col = 0;
                            row++;
                        }

                        carGridList.add(anchorPane, col++, row);
                        anchorPane.setOnMousePressed(event -> {
                            setCar(vehicle);
                            data.setVehicle(car);
                            FXMLLoader fxmlLoader1 = new FXMLLoader(RideYouStyle.class.getResource("CarDetail.fxml"));
                            Scene scene;
                            try {
                                scene = new Scene(fxmlLoader1.load(), 1080, 720);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Stage stage =  (Stage) carGridList.getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        });
                        GridPane.setMargin(anchorPane, new Insets(10));

                    }
                    for (Vehicle vehicle: vehicles){
                            carName.add(vehicle.name);
                    }
                    searchlistView.setOnMouseClicked(event -> {
                        FXMLSelector="All Cars";
                        for(Vehicle vehicle : vehicles){
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
                } catch (IOException e) {
                    e.printStackTrace();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
            default -> throw new IllegalStateException("Unexpected value: " + MainController.Company);
        }
        carGridList.setMinWidth(Region.USE_COMPUTED_SIZE);
        carGridList.setPrefWidth(Region.USE_COMPUTED_SIZE);
        carGridList.setMaxWidth(Region.USE_PREF_SIZE);


        carGridList.setMinHeight(Region.USE_COMPUTED_SIZE);
        carGridList.setPrefHeight(Region.USE_COMPUTED_SIZE);
        carGridList.setMaxHeight(Region.USE_PREF_SIZE);

        
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
}