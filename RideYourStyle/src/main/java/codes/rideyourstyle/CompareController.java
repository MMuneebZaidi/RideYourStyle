package codes.rideyourstyle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

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
        String[] arr ={"Bentley Bentayga","Bentley Continental GT","Bentley Flying Spur","Bentley Mulsanne","BMW 740i","BMW m850i","BMW X7","Chevrolet"};
    }
}
