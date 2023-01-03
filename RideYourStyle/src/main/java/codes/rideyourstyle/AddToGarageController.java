package codes.rideyourstyle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddToGarageController implements Initializable {
    @FXML
    private TableView<Vehicle> addToGarage;
    @FXML
    private TableColumn<Vehicle, String> Modify;
    @FXML
    private TableColumn<Vehicle, String> Name;
    @FXML
    private TableColumn<Vehicle, String> Price;

    LoginDatabaseConnection db = new LoginDatabaseConnection();
    Connection cart = db.getDatabaseLink();
    @FXML
    void backButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node) ev.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    private void loadDate() {
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        Name.setStyle("-fx-alignment:center-left");
        Price.setStyle( "-fx-alignment: CENTER;");

        Callback<TableColumn<Vehicle, String>, TableCell<Vehicle, String>> cellFactory2 = (TableColumn<Vehicle, String> param) -> new TableCell<>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    ImageView img = new ImageView(new Image("delete.png",15,15,true,false));
                    Button del = new Button("Delete",img);

                    del.setText(null);

                    del.setStyle("-fx-background-color: rgba(0,0,0,0);");
                    del.setOnMouseClicked(mouseEvent -> {
                        System.out.println(Garage.cars.size());
                        try {
                            String q = "UPDATE `garage` SET car"+ Garage.cars.size() +" = NULL WHERE user_id = '"+UserLoginController.loggedIn.id+"'";
                            Statement st3 = cart.createStatement();
                            st3.execute(q);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        for(int i=0; i<Garage.cars.size() ; i++){
                            if(this.getTableRow().getItem().name.equals(Garage.cars.get(i).name)){
                                Garage.cars.remove(i);
                                break;
                            }
                        }
                        try {
                            for(int i=Garage.cars.size(); i>0 ; i--){
                                String q = "UPDATE `garage` SET car"+i+" = '"+Garage.cars.get(i-1).name+"' WHERE user_id = '"+UserLoginController.loggedIn.id+"'";
                                Statement st3 = cart.createStatement();
                                st3.execute(q);
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        addToGarage.refresh();
                    });
                    HBox update = new HBox(del);
                    update.setStyle("-fx-alignment:center");
                    HBox.setMargin(update, new Insets(1, 1, 1, 1));

                    setGraphic(update);
                    setText(null);
                }
            }

        };
        Modify.setCellFactory(cellFactory2);
        addToGarage.setItems(Garage.getCars());
    }
    @FXML
    void Checkout(){
        try {
            Statement stm = cart.createStatement();
            String check = "SELECT `user_id` FROM `pendings`";
            ResultSet checking = stm.executeQuery(check);
            boolean test = true;
            while (checking.next()){
                if(UserLoginController.loggedIn.id==checking.getInt("user_id")){
                    test=false;
                }
            }
            if (test){
                String query = "SELECT car1, car2 , car3 , car4 , car5 FROM garage WHERE user_id = '" + UserLoginController.loggedIn.id + "'";
                ResultSet output = stm.executeQuery(query);
                StringBuilder cars= new StringBuilder();
                while (output.next()) {
                    if (output.getString("car1") != null) {
                        cars.append(output.getString("car1")).append("\n");
                    }
                    if (output.getString("car2") != null) {
                        cars.append(output.getString("car2")).append("\n");
                    }
                    if (output.getString("car3") != null) {
                        cars.append(output.getString("car3")).append("\n");
                    }
                    if (output.getString("car4") != null) {
                        cars.append(output.getString("car4")).append("\n");
                    }
                    if (output.getString("car5") != null) {
                        cars.append(output.getString("car5"));
                    }
                }
                db.insertPendingData(UserLoginController.loggedIn,cars);
                db.UpdateGarageData(UserLoginController.loggedIn);
                Garage.cars.clear();
                addToGarage.refresh();
            }else {
                Alert pending = new Alert(Alert.AlertType.INFORMATION,
                        "You already have a pending request!", ButtonType.OK);
                pending.showAndWait();
            }

        }catch (SQLException e) {
            Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Statement stm = cart.createStatement();
            String query = "SELECT car1, car2 , car3 , car4 , car5 FROM garage WHERE user_id = '" + UserLoginController.loggedIn.id + "'";
            ResultSet output = stm.executeQuery(query);

            while (output.next()) {
                if (output.getString("car1") != null) {
                    for(Vehicle vehicle : RideYouStyle.allVehicles){
                        if (output.getString("car1").equals(vehicle.name)){
                            Garage.cars.add(vehicle);
                        }
                    }
                }
                if (output.getString("car2") != null) {
                    for(Vehicle vehicle : RideYouStyle.allVehicles){
                        if (output.getString("car2").equals(vehicle.name)){
                            Garage.cars.add(vehicle);
                        }
                    }
                }
                if (output.getString("car3") != null) {
                    for(Vehicle vehicle : RideYouStyle.allVehicles){
                        if (output.getString("car3").equals(vehicle.name)){
                            Garage.cars.add(vehicle);
                        }
                    }
                }
                if (output.getString("car4") != null) {
                    for(Vehicle vehicle : RideYouStyle.allVehicles){
                        if (output.getString("car4").equals(vehicle.name)){
                            Garage.cars.add(vehicle);
                        }
                    }
                }
                if (output.getString("car5") != null) {
                    for(Vehicle vehicle : RideYouStyle.allVehicles){
                        if (output.getString("car5").equals(vehicle.name)){
                            Garage.cars.add(vehicle);
                        }
                    }
                }

            }
        }catch (SQLException e) {
            Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE, null, e);
        }
        loadDate();
    }
}
