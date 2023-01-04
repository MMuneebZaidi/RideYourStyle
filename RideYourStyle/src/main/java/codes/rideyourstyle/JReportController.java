package codes.rideyourstyle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class JReportController {
    JasperReport jReport;
    JasperDesign design;
    InputStream input = null;
    int orderno;
    @FXML
    void printReport(ActionEvent event) throws JRException {
                HashMap parameters = new HashMap();
                String path = "C:\\Users\\Aliyan\\JaspersoftWorkspace\\MyReports\\Order_Receipt.jrxml";
                jReport = JasperCompileManager.compileReport(path);
                LoginDatabaseConnection link = new LoginDatabaseConnection();
                Connection connectDB = link.getDatabaseLink();

                String query = "SELECT * FROM `sell/purchase` WHERE user_id='" + UserLoginController.loggedIn.id + "'";
                try {
                    Statement stm = connectDB.createStatement();
                    ResultSet rs = stm.executeQuery(query);
                    ArrayList<String> arr = new ArrayList<String>();
                    while (rs.next()){
                        orderno = rs.getInt("id");
                        String ordern = rs.getString("Listed");
                        String status = rs.getString("Status");
                        while(status.equals("Accepted")){
                        parameters.put("OrderNo",orderno);
                        parameters.put("CustomerEm",UserLoginController.loggedIn.Email);
                        parameters.put("CustomerN",UserLoginController.loggedIn.Username);
                        parameters.put("ProductN",ordern);

                            Stream<String> List = ordern.lines();
                            List.forEach(arr::add);
                            int Revenue=0;
                            for(String s:arr){
                                for(Vehicle v1:RideYouStyle.allVehicles){
                                    if(s.equals(v1.name)){
                                        Revenue=Revenue+Integer.valueOf(v1.price);
                                    }
                                }
                            }
                        parameters.put("ProductPrice",Revenue);
                        }
                    }
                } catch (SQLException e) {
                    Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE, null, e);
                }
                Map<String, Object> params = new HashMap<>();
                JasperPrint jasperPrint = JasperFillManager.fillReport(jReport, params, DatabaseConnection.getDatabaseLink());
                JasperViewer jasperViewer = new JasperViewer(jasperPrint);
                jasperViewer.setTitle("My Jasper Report");
                jasperViewer.setVisible(true);
    }

}
