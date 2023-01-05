package codes.rideyourstyle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class JReportController {
    JasperReport jReport;
    JasperDesign design;
    InputStream input = null;
    JasperPrint jasperPrint = new JasperPrint();
    int orderno;
    String ordern;
    int Revenue = 0;
    public JasperPrint createReport() {

        Map<String, Object> parameters = new HashMap<>();
        String path = "E:\\JavaFX Codes\\SemProject\\CarIMDB\\RideYourStyle\\src\\main\\resources\\JasperReports\\Order_Receipt.jrxml";
        try {
            jReport = JasperCompileManager.compileReport(path);
            LoginDatabaseConnection link = new LoginDatabaseConnection();
            Connection connectDB = link.getDatabaseLink();

            String query = "SELECT id, Listed FROM `pendings` WHERE user_id='" + UserLoginController.loggedIn.id + "'";
            try {
                Statement stm = connectDB.createStatement();
                ResultSet rs = stm.executeQuery(query);
                ArrayList<String> arr = new ArrayList<>();
                while (rs.next()) {
                    orderno = rs.getInt("id");
                    ordern = rs.getString("Listed");
                }
                parameters.put("OrderNo", orderno);
                parameters.put("CustomerEm", UserLoginController.loggedIn.Email);
                parameters.put("CustomerN", UserLoginController.loggedIn.Username);
                parameters.put("ProductN", ordern);
                Stream<String> List = ordern.lines();
                List.forEach(arr::add);
                for (String s : arr) {
                    for (Vehicle v1 : RideYouStyle.allVehicles) {
                        if (s.equals(v1.name)) {
                            Revenue = Revenue + Integer.parseInt(v1.price);
                        }
                    }
                }
                parameters.put("ProductPrice", Revenue);
            }catch (SQLException e) {
                Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE, null, e);
        }
            jasperPrint = JasperFillManager.fillReport(jReport, parameters, new JREmptyDataSource());

//
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            JasperExportManager.exportReportToPdfFile(jasperPrint,"jasper.pdf");
                jasperViewer.setTitle("My Jasper Report");
                jasperViewer.setVisible(true);

        } catch (JRException e) {
            System.out.println(e);
        }
        return jasperPrint;
    }
}
