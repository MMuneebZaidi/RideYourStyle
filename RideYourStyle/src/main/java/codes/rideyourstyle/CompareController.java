package codes.rideyourstyle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompareController implements Initializable {
    @FXML
    void HomeButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void backButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node) ev.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    String car1;
    String car2;
    @FXML
    private ChoiceBox<String> choicebox1;

    @FXML
    private ChoiceBox<String> choicebox2;
    DataSingleton data = DataSingleton.getInstance();
    @FXML
    void compareButton() throws IOException {
            if(Objects.equals(car1, car2)){
                ImageView img = new ImageView( new Image("E:\\JavaFX Codes\\SemProject\\CarIMDB\\RideYourStyle\\src\\main\\resources\\images\\logos\\Mohtaram.png"));
                Stage stage = new Stage();
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.getChildren().add(img);
                Scene sc = new Scene(anchorPane,300,275);
                stage.setTitle("Bhaggatt");
                stage.setResizable(false);
                stage.setScene(sc);
                stage.show();
            }else {data.setCar1(car1);
                data.setCar2(car2);
                FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("ComparedScene.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
                Stage stage = (Stage) choicebox1.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
    }
    ArrayList<String> carName = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getDatabaseLink();
            Statement stmt = connectDB.createStatement();
            String query = "(select name from bentley)union" +
                    "(select name from bmw)union" +
                    "(select name from chevrolet)union" +
                    "(select name from mercedes)union" +
                    "(select name from porsche)union" +
                    "(select name from rollsroyce)";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                carName.add(rs.getString("name"));
            }
            choicebox1.getItems().addAll(carName);
            choicebox2.getItems().addAll(carName);
            choicebox1.setOnAction(e -> car1 = choicebox1.getValue());
            choicebox2.setOnAction(e -> car2 = choicebox2.getValue());
        }catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE,null,e);
        }

    }

}
