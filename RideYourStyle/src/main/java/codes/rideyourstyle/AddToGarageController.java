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
    private TableColumn<Vehicle, String> Quantity;
    @FXML
    private TableColumn<Vehicle, String> Price;
    @FXML
    private Button backButton;
    @FXML
    void backButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    private void loadDate() {
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        Name.setStyle("-fx-alignment:center-left");
        Price.setStyle( "-fx-alignment: CENTER-LEFT;");
        Quantity.setStyle( "-fx-alignment: CENTER;");

        Callback<TableColumn<Vehicle, String>, TableCell<Vehicle, String>> cellFactory = (TableColumn<Vehicle, String> param) -> new TableCell<>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    setText(null);

                } else {
                    Button add = new Button("+");
                    Button minus = new Button("-");
                    TextField quantity = new TextField("1");

                    quantity.setPrefWidth(40);
                    add.setPrefWidth(35);
                    minus.setPrefWidth(35);

                    quantity.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (!newValue.matches("\\d*")) {
                            quantity.setText(newValue.replaceAll("\\D", ""));
                        }
                        if (quantity.getText().length() > 2) {
                            String s = quantity.getText().substring(0, 2);
                            quantity.setText(s);
                        }
                    });

                    add.setOnAction(actionEvent -> quantity.setText(String.valueOf(Integer.parseInt(quantity.getText()) + 1)));

                    minus.setOnAction(actionEvent -> {
                        if (Integer.parseInt(quantity.getText()) > 0) {
                            quantity.setText(String.valueOf(Integer.parseInt(quantity.getText()) - 1));
                        }
                    });

                    HBox update = new HBox(minus, quantity, add);
                    update.setStyle("-fx-alignment:center");
                    HBox.setMargin(minus, new Insets(1, 1, 1, 1));
                    HBox.setMargin(add, new Insets(1, 1, 1, 1));
                    HBox.setMargin(quantity, new Insets(1, 1, 1, 1));
                    HBox.setMargin(update, new Insets(1, 1, 1, 1));

                    setGraphic(update);
                    setText(null);
                }
            }

        };

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
                        Garage.cars.removeIf(vehicle -> (this.getTableRow().getItem().name.equals(vehicle.name)));
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
        Quantity.setCellFactory(cellFactory);
        addToGarage.setItems(Garage.getCars());
    }
    @FXML
    void Checkout(){

//        LoginDatabaseConnection db = new LoginDatabaseConnection();
//        System.out.println(UserLoginController.loggedIn.id);
//        for(Vehicle vehicle : Garage.cars){
//            try {
//            Connection connectDB = db.getDatabaseLink();
//            Statement stm = connectDB.createStatement();
//            String query = "SELECT car1, car2 , car3 , car4 , car5 FROM garage WHERE user_id = '"+UserLoginController.loggedIn.id+"'";
//            ResultSet output = stm.executeQuery(query);
//
//            while (output.next()){
//                if(output.getString("car1") == null){
//                    String q = "UPDATE `garage` SET car1 = '"+vehicle.name+"' WHERE user_id = '"+UserLoginController.loggedIn.id+"'";
//                    Statement st1 = connectDB.createStatement();
//                    st1.execute(q);
//                    break;
//                }
//                else if(output.getString("car2") == null){
//                    String q = "UPDATE `garage` SET car2 = '"+vehicle.name+"'WHERE user_id = '"+UserLoginController.loggedIn.id+"'";
//                    Statement st2 = connectDB.createStatement();
//                    st2.execute(q);
//                    break;
//                }
//                else if(output.getString("car3") == null){
//                    String q = "UPDATE `garage` SET car3 = '"+vehicle.name+"'WHERE user_id = '"+UserLoginController.loggedIn.id+"'";
//                    Statement st3 = connectDB.createStatement();
//                    st3.execute(q);
//                    break;
//                }
//                else if(output.getString("car4") == null){
//                    String q = "UPDATE `garage` SET car4 = '"+vehicle.name+"'WHERE user_id = '"+UserLoginController.loggedIn.id+"'";
//                    Statement st4 = connectDB.createStatement();
//                    st4.execute(q);
//                    break;
//                } else if(output.getString("car5") == null){
//                    String q = "UPDATE `garage` SET car5 = '"+vehicle.name+"'WHERE user_id = '"+UserLoginController.loggedIn.id+"'";
//                    Statement st5 = connectDB.createStatement();
//                    st5.execute(q);
//                    break;
//                }
//            }
//            }catch (Exception e) {
//            Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE, null, e);
//            }
//        }

        Garage.cars.clear();
        addToGarage.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoginDatabaseConnection db = new LoginDatabaseConnection();
        Connection cart = db.getDatabaseLink();
//        try {
//            Statement stm = cart.createStatement();
//            String query = "SELECT car1, car2 , car3 , car4 , car5 FROM garage WHERE user_id = '" + UserLoginController.loggedIn.id + "'";
//            ResultSet output = stm.executeQuery(query);
//
//            while (output.next()) {
//                if (output.getString("car1") == null) {
//                    String q = "UPDATE `garage` SET car1 = '" + vehicle.name + "' WHERE user_id = '" + UserLoginController.loggedIn.id + "'";
//                    Statement st1 = connectDB.createStatement();
//                    st1.execute(q);
//                    break;
//                } else if (output.getString("car2") == null) {
//                    String q = "UPDATE `garage` SET car2 = '" + vehicle.name + "'WHERE user_id = '" + UserLoginController.loggedIn.id + "'";
//                    Statement st2 = connectDB.createStatement();
//                    st2.execute(q);
//                    break;
//                } else if (output.getString("car3") == null) {
//                    String q = "UPDATE `garage` SET car3 = '" + vehicle.name + "'WHERE user_id = '" + UserLoginController.loggedIn.id + "'";
//                    Statement st3 = connectDB.createStatement();
//                    st3.execute(q);
//                    break;
//                } else if (output.getString("car4") == null) {
//                    String q = "UPDATE `garage` SET car4 = '" + vehicle.name + "'WHERE user_id = '" + UserLoginController.loggedIn.id + "'";
//                    Statement st4 = connectDB.createStatement();
//                    st4.execute(q);
//                    break;
//                } else if (output.getString("car5") == null) {
//                    String q = "UPDATE `garage` SET car5 = '" + vehicle.name + "'WHERE user_id = '" + UserLoginController.loggedIn.id + "'";
//                    Statement st5 = connectDB.createStatement();
//                    st5.execute(q);
//                    break;
//                }
//
//            }
//        }catch (SQLException e) {
//            Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE, null, e);
//        }
        loadDate();
    }
}
