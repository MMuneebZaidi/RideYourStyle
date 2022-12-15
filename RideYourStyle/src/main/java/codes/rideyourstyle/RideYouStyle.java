package codes.rideyourstyle;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class RideYouStyle extends Application {
    public static ObservableList<Vehicle> allVehicles;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Start.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setResizable(false);
        stage.getIcons().add(new Image("RideYourStyleLOGO.png"));
        stage.setTitle("Ride Your Style");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        allVehicles = db.retrieveDatabase();
        launch();
    }
}