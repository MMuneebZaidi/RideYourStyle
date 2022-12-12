package codes.rideyourstyle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CarCompanyController implements Initializable {

    @FXML
    private GridPane carGridList;

    @FXML
    private ImageView companyLOGO;

    @FXML
    private Label companyNAME;

    @FXML
    void backButton(ActionEvent event) throws IOException {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vehicles = RideYouStyle.allVehicles;

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

                            carGridList.setMinWidth(Region.USE_COMPUTED_SIZE);
                            carGridList.setPrefWidth(Region.USE_COMPUTED_SIZE);
                            carGridList.setMaxWidth(Region.USE_PREF_SIZE);


                            carGridList.setMinHeight(Region.USE_COMPUTED_SIZE);
                            carGridList.setPrefHeight(Region.USE_COMPUTED_SIZE);
                            carGridList.setMaxHeight(Region.USE_PREF_SIZE);

                            GridPane.setMargin(anchorPane, new Insets(10));}

                    }
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

                            carGridList.setMinWidth(Region.USE_COMPUTED_SIZE);
                            carGridList.setPrefWidth(Region.USE_COMPUTED_SIZE);
                            carGridList.setMaxWidth(Region.USE_PREF_SIZE);


                            carGridList.setMinHeight(Region.USE_COMPUTED_SIZE);
                            carGridList.setPrefHeight(Region.USE_COMPUTED_SIZE);
                            carGridList.setMaxHeight(Region.USE_PREF_SIZE);

                            GridPane.setMargin(anchorPane, new Insets(10));}

                    }
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

                            carGridList.setMinWidth(Region.USE_COMPUTED_SIZE);
                            carGridList.setPrefWidth(Region.USE_COMPUTED_SIZE);
                            carGridList.setMaxWidth(Region.USE_PREF_SIZE);


                            carGridList.setMinHeight(Region.USE_COMPUTED_SIZE);
                            carGridList.setPrefHeight(Region.USE_COMPUTED_SIZE);
                            carGridList.setMaxHeight(Region.USE_PREF_SIZE);

                            GridPane.setMargin(anchorPane, new Insets(10));}

                    }
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

                            carGridList.setMinWidth(Region.USE_COMPUTED_SIZE);
                            carGridList.setPrefWidth(Region.USE_COMPUTED_SIZE);
                            carGridList.setMaxWidth(Region.USE_PREF_SIZE);


                            carGridList.setMinHeight(Region.USE_COMPUTED_SIZE);
                            carGridList.setPrefHeight(Region.USE_COMPUTED_SIZE);
                            carGridList.setMaxHeight(Region.USE_PREF_SIZE);

                            GridPane.setMargin(anchorPane, new Insets(10));}

                    }
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

                            carGridList.setMinWidth(Region.USE_COMPUTED_SIZE);
                            carGridList.setPrefWidth(Region.USE_COMPUTED_SIZE);
                            carGridList.setMaxWidth(Region.USE_PREF_SIZE);


                            carGridList.setMinHeight(Region.USE_COMPUTED_SIZE);
                            carGridList.setPrefHeight(Region.USE_COMPUTED_SIZE);
                            carGridList.setMaxHeight(Region.USE_PREF_SIZE);

                            GridPane.setMargin(anchorPane, new Insets(10));}

                    }
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

                            carGridList.setMinWidth(Region.USE_COMPUTED_SIZE);
                            carGridList.setPrefWidth(Region.USE_COMPUTED_SIZE);
                            carGridList.setMaxWidth(Region.USE_PREF_SIZE);


                            carGridList.setMinHeight(Region.USE_COMPUTED_SIZE);
                            carGridList.setPrefHeight(Region.USE_COMPUTED_SIZE);
                            carGridList.setMaxHeight(Region.USE_PREF_SIZE);

                            GridPane.setMargin(anchorPane, new Insets(10));}

                    }
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

                        carGridList.setMinWidth(Region.USE_COMPUTED_SIZE);
                        carGridList.setPrefWidth(Region.USE_COMPUTED_SIZE);
                        carGridList.setMaxWidth(Region.USE_PREF_SIZE);


                        carGridList.setMinHeight(Region.USE_COMPUTED_SIZE);
                        carGridList.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        carGridList.setMaxHeight(Region.USE_PREF_SIZE);

                        GridPane.setMargin(anchorPane, new Insets(10));

                    }
                } catch (IOException e) {
                    e.printStackTrace();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}