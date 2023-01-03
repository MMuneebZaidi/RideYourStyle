package codes.rideyourstyle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class ReportController  implements Initializable {
    @FXML
    private Label CarsSold;
    @FXML
    private Label RevenueG;
    @FXML
    private Label ExpensesG;
    @FXML
    private Label cmonth;

    @FXML
    private LineChart<?, ?> graph;

    @FXML
    private Label Profitg;
    @FXML
    void backButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYouStyle.class.getResource("AdminDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = (Stage) (((Node) ev.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }
    int profit=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Calendar mCalendar = Calendar.getInstance();
        String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        try {
            LoginDatabaseConnection db = new LoginDatabaseConnection();
            Connection purchaseHis = db.getDatabaseLink();
            Statement stm = purchaseHis.createStatement();

            int i = 1;
            int Revenue=0;
            ArrayList<String> arr = new ArrayList<String>();
            String purchase = "SELECT * FROM `sell/purchase`";
            ResultSet purchased = stm.executeQuery(purchase);
            while (purchased.next()){
                int userID = purchased.getInt("user_id");
                String status = purchased.getString("Status");
                String s2 = purchased.getString("Listed");
                if(status.equals("Accepted")){
                    Stream<String> List = s2.lines();
                    List.forEach(arr::add);
                }
            }
            CarsSold.setText(String.valueOf(arr.size())+" cars");
            for(String s:arr){
                for(Vehicle v1:RideYouStyle.allVehicles){
                    if(s.equals(v1.name)){
                        Revenue=Revenue+Integer.valueOf(v1.price);
                    }
                }
            }
            RevenueG.setText("Rs. "+String.valueOf(Revenue)+"/-");
            int Expenses = (Revenue/100)*95;
            ExpensesG.setText("Rs. "+String.valueOf(Expenses)+"/-");
            profit=Revenue-Expenses;
            Profitg.setText("Rs. "+String.valueOf(profit)+"/-");
            cmonth.setText(month);
        }
        catch (SQLException e){
            Logger.getLogger(PendingRequestsController.class.getName()).log(Level.SEVERE, null, e);
        }
        XYChart.Series dataSeries1 = new XYChart.Series();


        dataSeries1.getData().add(new XYChart.Data("Jan", 4192300));
        dataSeries1.getData().add(new XYChart.Data("Feb", 7430000));
        dataSeries1.getData().add(new XYChart.Data("Mar", 6271300));
        dataSeries1.getData().add(new XYChart.Data("Apr", 4567700));
        dataSeries1.getData().add(new XYChart.Data("May",7542000 ));
        dataSeries1.getData().add(new XYChart.Data("Jun", 7880000));
        dataSeries1.getData().add(new XYChart.Data("Jul", 5035000));
        dataSeries1.getData().add(new XYChart.Data("Aug", 6174700));
        dataSeries1.getData().add(new XYChart.Data("Sep", 9111111));
        dataSeries1.getData().add(new XYChart.Data("Oct", 8100600));
        dataSeries1.getData().add(new XYChart.Data("Nov", 6826351));
        dataSeries1.getData().add(new XYChart.Data("Dec", profit ));

        graph.getData().addAll(dataSeries1);
    }
}