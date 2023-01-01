package codes.rideyourstyle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        purchaseHistory.setItems(purchaseHistoryList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
    }
}
