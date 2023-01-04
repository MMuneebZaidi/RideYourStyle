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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaseHistoryController implements Initializable {
    @FXML
    private TableView<Pendings> purchaseHistory;
    @FXML
    private TableColumn<Pendings,Integer> id;
    @FXML
    private TableColumn<Pendings,StringBuilder> order;
    @FXML
    private TableColumn<Pendings,String> status;
    @FXML
    private TableColumn<Pendings,String> receipt;
    @FXML
    void HomeButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
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
    void backButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Stage stage = (Stage) (((Node) ev.getSource()).getScene().getWindow());
        Scene scene;
        if (stage.isMaximized()) {
            scene = new Scene(fxmlLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        } else {
            scene = new Scene(fxmlLoader.load());
        }
        stage.setScene(scene);
    }
    private void loadDate() {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        order.setCellValueFactory(new PropertyValueFactory<>("requests"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        id.setStyle( "-fx-alignment: CENTER;");
        order.setStyle( "-fx-alignment: CENTER-LEFT;");
        status.setStyle( "-fx-alignment: CENTER;");

        ObservableList<Pendings> purchaseHistoryList = FXCollections.observableArrayList();
        try {
            LoginDatabaseConnection db = new LoginDatabaseConnection();
            Connection purchaseHis = db.getDatabaseLink();
            Statement stm = purchaseHis.createStatement();

            int i = 1;

            String pending = "SELECT * FROM `pendings`";
            ResultSet pendings = stm.executeQuery(pending);

            while (pendings.next()){
                int userID = pendings.getInt("user_id");
                String status = pendings.getString("Status");
                StringBuilder listed = new StringBuilder();
                listed.append(pendings.getString("Listed"));
                String ManagedBy = pendings.getString("Managed By");
                if(userID==UserLoginController.loggedIn.id){
                    purchaseHistoryList.add(new Pendings(i,userID,listed,status,ManagedBy));
                    i++;
                }
            }

            String purchase = "SELECT * FROM `sell/purchase`";
            ResultSet purchased = stm.executeQuery(purchase);

            while (purchased.next()){
                int userID = purchased.getInt("user_id");
                String status = purchased.getString("Status");
                StringBuilder listed = new StringBuilder();
                listed.append(purchased.getString("Listed"));
                String ManagedBy = purchased.getString("Managed By");
                if(userID==UserLoginController.loggedIn.id){
                    purchaseHistoryList.add(new Pendings(i,userID,listed,status,ManagedBy));
                    i++;
                }
            }
        }
        catch (SQLException e){
            Logger.getLogger(PendingRequestsController.class.getName()).log(Level.SEVERE, null, e);
        }
        Callback<TableColumn<Pendings, String>, TableCell<Pendings, String>> cellFactory = (TableColumn<Pendings, String> param) -> new TableCell<>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    setText(null);

                } else {
                    ImageView Receipt = new ImageView(new Image("printReceipt.png",15.0,15.0,false,false));
                    Receipt.setDisable(true);
                    Receipt.setOnMouseClicked(actionEvent -> {
                        System.out.println("ok");
                    });

                    HBox update = new HBox(Receipt);

                    HBox.setMargin(update, new Insets(1, 1, 1, 1));


                    setGraphic(update);
                    setText(null);
                }
            }

        };
        receipt.setCellFactory(cellFactory);
        purchaseHistory.setItems(purchaseHistoryList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        purchaseHistory.setFocusTraversable(false);
        loadDate();
    }
}
