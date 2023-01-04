package codes.rideyourstyle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PendingRequestsController implements Initializable {
    @FXML
    TableView<Pendings> pendingRequests;
    @FXML
    private TableColumn<Pendings, String> customerName;
    @FXML
    private TableColumn<Pendings, StringBuilder> requests;
    @FXML
    private TableColumn<Pendings, String> Action;
    @FXML
    void backButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("AdminDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node) ev.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    LoginDatabaseConnection db = new LoginDatabaseConnection();
    Connection pendings = db.getDatabaseLink();
    private void loadDate() {

        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        requests.setCellValueFactory(new PropertyValueFactory<>("requests"));
        customerName.setStyle( "-fx-alignment: CENTER-LEFT;");

        Callback<TableColumn<Pendings, String>, TableCell<Pendings, String>> cellFactory = (TableColumn<Pendings, String> param) -> new TableCell<>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {


                    ImageView accept = new ImageView(new Image("accept.png",25,25,true,false));
                    ImageView reject = new ImageView(new Image("reject.png",25,25,true,false));

                    Button Accept = new Button("Accept" , accept);
                    Button Reject = new Button("Reject" , reject);

                    Accept.setText(null);
                    Reject.setText(null);

                    Accept.setStyle("-fx-background-color: rgba(0,0,0,0);");
                    Reject.setStyle("-fx-background-color: rgba(0,0,0,0);");

                    Accept.setPrefWidth(45);
                    Reject.setPrefWidth(45);
                    try{
                        if(!(this.getTableRow().getItem().getStatus().equals("Request Pending"))){
                            Accept.setVisible(false);
                            Reject.setVisible(false);
                        }
                    }catch (Exception e){
                        return;
                    }
                    Accept.setOnAction(actionEvent -> {

                        try {
                            Statement stm = db.DatabaseLink.createStatement();
                            String status = "UPDATE `pendings` SET `Status` = 'Accepted' WHERE user_id = '" + this.getTableRow().getItem().getUser_id()+ "'";
                            String managedBy = "UPDATE `pendings` SET `Managed By` = '"+AdminLoginController.loggedInAdmin.Email+"' WHERE user_id = '" + this.getTableRow().getItem().getUser_id()+ "'";
                            stm.execute(status);
                            stm.execute(managedBy);

                        } catch (SQLException e) {
                            Logger.getLogger(PendingRequestsController.class.getName()).log(Level.SEVERE, null, e);
                        }
                        pendingRequest.clear();
                        try {
                            Statement stm = pendings.createStatement();
                            String check = "SELECT * FROM `pendings`";
                            ResultSet checking = stm.executeQuery(check);
                            while (checking.next()){
                                int userID = checking.getInt("user_id");
                                String status = checking.getString("Status");
                                StringBuilder listed = new StringBuilder();
                                listed.append(checking.getString("Listed"));
                                String ManagedBy = checking.getString("Managed By");
                                pendingRequest.add(new Pendings(userID,listed,status,ManagedBy));
                            }
                        }
                        catch (SQLException e){
                            Logger.getLogger(PendingRequestsController.class.getName()).log(Level.SEVERE, null, e);
                        }
                        try {
                        for (int i = 0 ; i < pendingRequest.size() ; i++){
                            if(pendingRequest.get(i).getManagedBy()!=null){
                                    Statement stm = db.DatabaseLink.createStatement();
                                    db.insertSellPurchaseData(new Pendings(pendingRequest.get(i).getUser_id(),pendingRequest.get(i).getRequests(),pendingRequest.get(i).getStatus(),pendingRequest.get(i).getManagedBy()));
                                    String delete = "DELETE FROM pendings WHERE `pendings`.`user_id` = "+pendingRequest.get(i).getUser_id();
                                    stm.execute(delete);
                                    pendingRequest.remove(i);
                                }
                            }
                        }
                        catch (SQLException e){
                            Logger.getLogger(PendingRequestsController.class.getName()).log(Level.SEVERE, null, e);
                        }

                        pendingRequests.refresh();
                    });

                    Reject.setOnAction(actionEvent -> {

                        try {
                            Statement stm = db.DatabaseLink.createStatement();
                            String status = "UPDATE `pendings` SET `Status` = 'Rejected' WHERE user_id = '" + this.getTableRow().getItem().getUser_id()+ "'";
                            String managedBy = "UPDATE `pendings` SET `Managed By` = '"+AdminLoginController.loggedInAdmin.Email+"' WHERE user_id = '" + this.getTableRow().getItem().getUser_id()+ "'";
                            stm.execute(status);
                            stm.execute(managedBy);

                        } catch (SQLException e) {
                            Logger.getLogger(PendingRequestsController.class.getName()).log(Level.SEVERE, null, e);
                        }
                        pendingRequest.clear();
                        try {
                            Statement stm = pendings.createStatement();
                            String check = "SELECT * FROM `pendings`";
                            ResultSet checking = stm.executeQuery(check);
                            while (checking.next()){
                                int userID = checking.getInt("user_id");
                                String status = checking.getString("Status");
                                StringBuilder listed = new StringBuilder();
                                listed.append(checking.getString("Listed"));
                                String ManagedBy = checking.getString("Managed By");
                                pendingRequest.add(new Pendings(userID,listed,status,ManagedBy));
                            }
                        }
                        catch (SQLException e){
                            Logger.getLogger(PendingRequestsController.class.getName()).log(Level.SEVERE, null, e);
                        }
                        try {
                            for (int i = 0 ; i < pendingRequest.size() ; i++){
                                if(pendingRequest.get(i).getManagedBy()!=null){
                                    Statement stm = db.DatabaseLink.createStatement();
                                    db.insertSellPurchaseData(new Pendings(pendingRequest.get(i).getUser_id(),pendingRequest.get(i).getRequests(),pendingRequest.get(i).getStatus(),pendingRequest.get(i).getManagedBy()));
                                    String delete = "DELETE FROM pendings WHERE `pendings`.`user_id` = "+pendingRequest.get(i).getUser_id();
                                    stm.execute(delete);
                                    //noinspection SuspiciousListRemoveInLoop
                                    pendingRequest.remove(i);
                                }
                            }
                        }
                        catch (SQLException e){
                            Logger.getLogger(PendingRequestsController.class.getName()).log(Level.SEVERE, null, e);
                        }

                        pendingRequests.refresh();
                    });

                    HBox action = new HBox(Reject, Accept);
                    action.setStyle("-fx-alignment:center");
                    HBox.setMargin(Reject, new Insets(1, 1, 1, 1));
                    HBox.setMargin(Accept, new Insets(1, 1, 1, 1));

                    setGraphic(action);
                    setText(null);
                }
            }

        };
        Action.setCellFactory(cellFactory);
        pendingRequests.setItems(pendingRequest);
    }

    ObservableList<Pendings> pendingRequest = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Statement stm = pendings.createStatement();
            String check = "SELECT * FROM `pendings`";
            ResultSet checking = stm.executeQuery(check);
            while (checking.next()){
                int userID = checking.getInt("user_id");
                String status = checking.getString("Status");
                StringBuilder listed = new StringBuilder();
                listed.append(checking.getString("Listed"));
                String ManagedBy = checking.getString("Managed By");
                pendingRequest.add(new Pendings(userID,listed,status,ManagedBy));
            }
        }
        catch (SQLException e){
            Logger.getLogger(PendingRequestsController.class.getName()).log(Level.SEVERE, null, e);
        }
        loadDate();
    }
}
