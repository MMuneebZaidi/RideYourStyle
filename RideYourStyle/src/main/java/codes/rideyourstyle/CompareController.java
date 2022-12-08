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
import java.util.ResourceBundle;

public class CompareController implements Initializable {

    @FXML
    private ChoiceBox<String> choicebox1;

    @FXML
    private ChoiceBox<String> choicebox2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] arr ={"Bentley Bentayga","Bentley Continental GT","Bentley Flying Spur","Bentley Mulsanne","BMW 740i","BMW m850i","BMW X7","Chevrolet "};
        choicebox1.getItems().addAll(arr);
    }
    @FXML
    void backButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node)ev.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

}
