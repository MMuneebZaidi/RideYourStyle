package codes.rideyourstyle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    void findButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Finding.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node)ev.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void CompareButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("Compare.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    Parent root;
    public static String Company;
    public static String Logo;
    @FXML
    void MercedesButton(ActionEvent event) throws IOException {
        Company="Mercedes";
        Logo="mercedes-logo.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Scene scene = new Scene(root, 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void BMWButton(ActionEvent event) throws IOException {
        Company="BMW";
        Logo="bmwlogo.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Scene scene = new Scene(root, 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void CheveroletButton(ActionEvent event) throws IOException {
        Company="Cheverolet";
        Logo="CheveroletLogo.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Scene scene = new Scene(root, 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void PorscheButton(ActionEvent event) throws IOException {
        Company="Porsche";
        Logo="Porsche.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Scene scene = new Scene(root, 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void RollsRoyceButton(ActionEvent event) throws IOException {
        Company="Rolls Royce";
        Logo="rollsroyce.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Scene scene = new Scene(root, 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void BentlyButton(ActionEvent event) throws IOException {
        Company="Bently";
        Logo="bently.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Scene scene = new Scene(root, 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void AllCarsButton(ActionEvent event) throws IOException {
        Company="All Cars";
        Logo="ALL-CARS.png";
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("CarCompany.fxml"));
        root = fxmlLoader.load();
        CarCompanyController ccc = fxmlLoader.getController();
        ccc.setCompany(Company,Logo);
        Scene scene = new Scene(root, 1080, 720);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
}