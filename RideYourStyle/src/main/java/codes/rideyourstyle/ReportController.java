package codes.rideyourstyle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

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

@SuppressWarnings("ALL")
public class ReportController  implements Initializable {
    @FXML
    private TextField CarsSold;
    @FXML
    private TextField RevenueG;
    @FXML
    private TextField ExpensesG;
    @FXML
    private Label cmonth;

    @FXML
    private LineChart<?, ?> graph;

    @FXML
    private TextField Profitg;
    JasperReport jReport;
    JasperPrint jasperPrint = new JasperPrint();

    @FXML
    void backButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYourStyle.class.getResource("AdminDashboard.fxml"));
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
    void HomeButton(ActionEvent ev) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RideYourStyle.class.getResource("AdminDashboard.fxml"));
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
    int profit=0;
    Calendar mCalendar = Calendar.getInstance();
    String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LoginDatabaseConnection db = new LoginDatabaseConnection();
            Connection purchaseHis = db.getDatabaseLink();
            Statement stm = purchaseHis.createStatement();

            int Revenue=0;
            ArrayList<String> arr = new ArrayList<>();
            String purchase = "SELECT * FROM `sell/purchase`";
            ResultSet purchased = stm.executeQuery(purchase);
            while (purchased.next()){
                String status = purchased.getString("Status");
                String s2 = purchased.getString("Listed");
                if(status.equals("Approved")){
                    Stream<String> List = s2.lines();
                    List.forEach(arr::add);
                }
            }
            CarsSold.setText(arr.size() +" cars");
            for(String s:arr){
                for(Vehicle v1: RideYourStyle.allVehicles){
                    if(s.equals(v1.name)){
                        Revenue=Revenue+Integer.parseInt(v1.price);
                    }
                }
            }
            RevenueG.setText("Rs. "+ Revenue +"/-");
            int Expenses = (Revenue/100)*95;
            ExpensesG.setText("Rs. "+ Expenses +"/-");
            profit=Revenue-Expenses;
            Profitg.setText("Rs. "+ profit +"/-");
            cmonth.setText(month);
            createReport(Revenue,Expenses,profit,arr.size());
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
    public JasperPrint createReport(int rev, int exp, int tot, int cars) {

        Map<String, Object> parameters = new HashMap<>();
        String path = "E:\\JavaFX Codes\\SemProject\\CarIMDB\\RideYourStyle\\src\\main\\resources\\JasperReports\\AdminRep.jrxml";
        try {
            jReport = JasperCompileManager.compileReport(path);
            parameters.put("Month", month);
            parameters.put("Carn", cars);
            parameters.put("Expenses", exp);
            parameters.put("Revenue", rev);
            parameters.put("Profit", tot);
            jasperPrint = JasperFillManager.fillReport(jReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint,"jasper.pdf");
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle("Monthly data");
                jasperViewer.setVisible(true);
        } catch (JRException e) {
            System.out.println(e);
        }
        return jasperPrint;
    }
}