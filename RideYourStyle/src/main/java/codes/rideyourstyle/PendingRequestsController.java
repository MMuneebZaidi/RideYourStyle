package codes.rideyourstyle;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PendingRequestsController {
    @FXML
    TableView<Info> pendingRequests;
    @FXML
    private TableColumn<UserInfo, String> customerName;
    @FXML
    private TableColumn<Info, StringBuilder> requests;
    @FXML
    private TableColumn<Info, String> Action;
    @FXML
    private TableColumn<AdminInfo, String> ManagedBy;
    private void loadDate() {

//        stockCol.setStyle( "-fx-alignment: CENTER;");
//
//        Callback<TableColumn<Vehicle, String>, TableCell<Vehicle, String>> cellFactory2 = (TableColumn<Vehicle, String> param) -> new TableCell<>() {
//            @Override
//            public void updateItem(String item, boolean empty) {
//                super.updateItem(item, empty);
//
//                if (empty) {
//                    setGraphic(null);
//                    setText(null);
//                } else {
//                    ImageView img = new ImageView(new Image("delete.png",15,15,true,false));
//                    Button del = new Button("Delete",img);
//
//                    del.setText(null);
//
//                    del.setStyle("-fx-background-color: rgba(0,0,0,0);");
//                    del.setOnMouseClicked(mouseEvent -> {
//                        System.out.println(Garage.cars.size());
//                        try {
//                            String q = "UPDATE `garage` SET car"+ Garage.cars.size() +" = NULL WHERE user_id = '"+UserLoginController.loggedIn.id+"'";
//                            Statement st3 = cart.createStatement();
//                            st3.execute(q);
//                        } catch (SQLException e) {
//                            throw new RuntimeException(e);
//                        }
//                        for(int i=0; i<Garage.cars.size() ; i++){
//                            if(this.getTableRow().getItem().name.equals(Garage.cars.get(i).name)){
//                                Garage.cars.remove(i);
//                                break;
//                            }
//                        }
//                        try {
//                            for(int i=Garage.cars.size(); i>0 ; i--){
//                                String q = "UPDATE `garage` SET car"+i+" = '"+Garage.cars.get(i-1).name+"' WHERE user_id = '"+UserLoginController.loggedIn.id+"'";
//                                Statement st3 = cart.createStatement();
//                                st3.execute(q);
//                            }
//                        } catch (SQLException e) {
//                            throw new RuntimeException(e);
//                        }
//                        addToGarage.refresh();
//                    });
//                    HBox update = new HBox(del);
//                    update.setStyle("-fx-alignment:center");
//                    HBox.setMargin(update, new Insets(1, 1, 1, 1));
//
//                    setGraphic(update);
//                    setText(null);
//                }
//            }
//
//        };
//        updateCol.setCellFactory(cellFactory);
//        stockUpdateTable.setItems(tableList);
//    }
}}
