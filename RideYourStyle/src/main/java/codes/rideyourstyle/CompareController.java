package codes.rideyourstyle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.sql.*;

public class CompareController implements Initializable {
    @FXML
    void backButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node)ev.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private ChoiceBox<String> choicebox1;

    @FXML
    private ChoiceBox<String> choicebox2;

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
            while (rs.next()){
                String carName = rs.getString("name");
                choicebox1.getItems().add(carName);
                choicebox2.getItems().add(carName);
            }
        }catch (Exception e){
            e.printStackTrace();}
    }
}
