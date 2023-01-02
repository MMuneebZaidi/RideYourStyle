package codes.rideyourstyle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;
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
    private Label Profitg;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            int Profit=Revenue-Expenses;
            Profitg.setText("Rs. "+String.valueOf(Profit)+"/-");
        }
        catch (SQLException e){
            Logger.getLogger(PendingRequestsController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}