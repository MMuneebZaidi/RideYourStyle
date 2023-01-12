package codes.rideyourstyle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class StartControllerClass {
    @FXML
    void startButton(ActionEvent ev) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(RideYourStyle.class.getResource("UserLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("RideYourStyleLOGO.png"));
        stage.setResizable(true);
        stage.setTitle("Ride Your Style");
        stage.setScene(scene);
        stage.show();
        ((Stage)(((Node)ev.getSource()).getScene().getWindow())).close();
    }
}
