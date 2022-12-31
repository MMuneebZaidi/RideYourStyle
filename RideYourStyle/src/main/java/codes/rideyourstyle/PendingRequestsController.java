//package codes.rideyourstyle;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.geometry.Insets;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.HBox;
//import javafx.util.Callback;
//
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ResourceBundle;
//
//public class PendingRequestsController implements Initializable {
//    @FXML
//    TableView<Pendings> pendingRequests;
//    @FXML
//    private TableColumn<Pendings, String> customerName;
//    @FXML
//    private TableColumn<Pendings, StringBuilder> requests;
//    @FXML
//    private TableColumn<Pendings, String> Action;
//    @FXML
//    private TableColumn<Pendings, String> ManagedBy;
//
//    LoginDatabaseConnection db = new LoginDatabaseConnection();
//    Connection pendings = db.getDatabaseLink();
//    private void loadDate() {
//        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
//        requests.setCellValueFactory(new PropertyValueFactory<>("requests"));
////        stockCol.setStyle( "-fx-alignment: CENTER;");
//
//        Callback<TableColumn<UserInfo, String>, TableCell<UserInfo, String>> cellFactory = (TableColumn<UserInfo, String> param) -> new TableCell<>() {
//            @Override
//            public void updateItem(String item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) {
//                    setGraphic(null);
//                    setText(null);
//                } else {
//
//                }
//            }
//
//        };
//        updateCol.setCellFactory(cellFactory);
//        stockUpdateTable.setItems(tableList);
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try {
//            Statement stm = pendings.createStatement();
//            String check = "SELECT * FROM `pendings`";
//            ResultSet checking = stm.executeQuery(check);
//            while (checking.next()){
//                int userID = checking.getInt("user_id");
//
//                StringBuilder listed = new StringBuilder();
//                listed.append(checking.getString("Listed"));
//
//            }
//        }
//        catch (SQLException e){
//
//        }
//    }
//}
