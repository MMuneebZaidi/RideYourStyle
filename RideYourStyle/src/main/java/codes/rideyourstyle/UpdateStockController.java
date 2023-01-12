package codes.rideyourstyle;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class UpdateStockController implements Initializable {
    @FXML
    private TableView<Vehicle> stockUpdateTable;
    @FXML
    private TableColumn<Vehicle,String> modelCol;
    @FXML
    private TableColumn<Vehicle,String> stockCol;
    @FXML
    private TableColumn<Vehicle,String> updateCol;
    @FXML
    private JFXListView<String> searchlistView;
    @FXML
    private JFXScrollPane searchscroll;

    @FXML
    private JFXTextField searchBar;
    @FXML
    void HomeButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("AdminDashboard.fxml"));
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
    static Vehicle car;

    ArrayList<String> carName = new ArrayList<>();
    ObservableList<Vehicle> allVehicles = FXCollections.observableArrayList();
    ObservableList<Vehicle> tableList = FXCollections.observableArrayList();
    @FXML
    void backButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("AdminDashboard.fxml"));
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
    private void addToTable() {
        for(Vehicle vehicle : allVehicles){
            if(vehicle.name.equals(searchlistView.getSelectionModel().getSelectedItem())){
                tableList.add(vehicle);
            }
        }
        stockUpdateTable.setItems(tableList);
    }
    private void loadDate() {
        modelCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        stockCol.setStyle( "-fx-alignment: CENTER;");

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
                    TextField stockVal = new TextField(car.getStock());
                    Button updateBtn = new Button("Update");

                    stockVal.setPrefWidth(30.0);
                    add.setPrefWidth(35);
                    minus.setPrefWidth(35);
                    updateBtn.setPrefWidth(70);

                    stockVal.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (!newValue.matches("\\d*")) {
                            stockVal.setText(newValue.replaceAll("\\D", ""));
                        }
                        if (stockVal.getText().length() > 2) {
                            String s = stockVal.getText().substring(0, 2);
                            stockVal.setText(s);
                        }
                    });

                    add.setOnAction(actionEvent -> stockVal.setText(String.valueOf(Integer.parseInt(stockVal.getText()) + 1)));

                    minus.setOnAction(actionEvent -> {
                        if (Integer.parseInt(stockVal.getText()) > 0) {
                            stockVal.setText(String.valueOf(Integer.parseInt(stockVal.getText()) - 1));
                        }
                    });

                    updateBtn.setOnAction(actionEvent -> {
                        TableRow<Vehicle> row = this.getTableRow();
                        row.getItem().stock=stockVal.getText();
                        for(Vehicle vehicle : allVehicles){
                            String table = null;
                            if(vehicle instanceof Bently){
                                table="bentley";
                            }
                            if(vehicle instanceof BMW){
                                table="bmw";
                            }
                            if(vehicle instanceof Cheverolet){
                                table="chevrolet";
                            }
                            if(vehicle instanceof Mercedes){
                                table="mercedes";
                            }
                            if(vehicle instanceof Porsche){
                                table="porsche";
                            }
                            if(vehicle instanceof RollsRoyce){
                                table="rollsroyce";
                            }

                            if(row.getItem().name.equals(vehicle.name)){
                                String primary = vehicle.name;
                                String query = "UPDATE `"+table+"` SET `Stock` = '"+row.getItem().stock+"' WHERE `"+table+"`.`Name` = '"+primary+"'";
                                DatabaseConnection DC = new DatabaseConnection();
                                Connection connectDB = DC.getDatabaseLink();
                                Statement stm;
                                try {
                                    stm = connectDB.createStatement();
                                    stm.execute(query);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        searchlistView.getItems().clear();
                        carName.add(row.getItem().name);
                        search();
                        searchlistView.refresh();
                        tableList.removeIf(vehicle -> (row.getItem().name.equals(vehicle.name)));
                        stockUpdateTable.refresh();
                        searchscroll.setVisible(false);
                        searchlistView.setVisible(false);
                    });

                    HBox update = new HBox(minus, stockVal, add, updateBtn);
                    update.setStyle("-fx-alignment:center");
                    HBox.setMargin(minus, new Insets(1, 1, 1, 1));
                    HBox.setMargin(add, new Insets(1, 1, 1, 1));
                    HBox.setMargin(stockVal, new Insets(1, 1, 1, 1));
                    HBox.setMargin(update, new Insets(1, 1, 1, 1));

                    add.setOnMousePressed(mouseEvent -> {
                        searchlistView.setVisible(false);
                        searchscroll.setVisible(false);
                    });
                    minus.setOnMousePressed(mouseEvent -> {
                        searchlistView.setVisible(false);
                        searchscroll.setVisible(false);
                    });
                    stockVal.setOnMousePressed(mouseEvent -> {
                        searchlistView.setVisible(false);
                        searchscroll.setVisible(false);
                    });
                    setGraphic(update);
                    setText(null);
                }
            }

        };
        updateCol.setCellFactory(cellFactory);
        stockUpdateTable.setItems(tableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allVehicles.addAll(RideYouStyle.allVehicles);
        for (Vehicle vehicle: allVehicles){
            carName.add(vehicle.name);
        }
        loadDate();
        searchlistView.setOnMouseClicked(event -> {
            for(Vehicle vehicle : allVehicles){
                if(this.searchlistView.getSelectionModel().getSelectedItem().equals(vehicle.name)){
                    car=vehicle;
                }
            }
            carName.removeIf(vehicle -> (searchlistView.getSelectionModel().getSelectedItem().equals(vehicle)));
            addToTable();
            searchlistView.getItems().clear();
            searchlistView.refresh();
            search();
            searchBar.requestFocus();
        });
        stockUpdateTable.setOnMousePressed(mouseEvent -> {
            searchlistView.setVisible(false);
            searchscroll.setVisible(false);
        });
        searchBar.setOnMouseClicked(mouseEvent -> {
            if(searchBar.isFocused() && !searchlistView.isVisible() && !searchBar.getText().isEmpty()){
                searchlistView.setVisible(true);
                searchscroll.setVisible(true);
            }
        });
    }
}