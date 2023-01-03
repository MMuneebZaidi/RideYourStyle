package codes.rideyourstyle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JReportController {
    JasperReport jReport;
    InputStream input = null;
    @FXML
    void printReport(ActionEvent event) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                HashMap parameters = new HashMap();
                LoginDatabaseConnection link = new LoginDatabaseConnection();
                Connection connectDB = link.getDatabaseLink();

                String query = "SELECT * FROM pendings WHERE id = '" + UserLoginController.loggedIn.id + "'";
                try {
                    Statement stm = connectDB.createStatement();
                    ResultSet rs = stm.executeQuery(query);
                    while (rs.next()){
                        input = rs.getBinaryStream(1);
                    }
                } catch (SQLException e) {
                    Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE, null, e);
                }
                jReport = (JasperReport) JRLoader.loadObject(input);
                JasperPrint jp = JasperFillManager.fillReport(jReport,parameters,link.getDatabaseLink());
                JasperViewer viewer = new JasperViewer(jp, false);
                viewer.setVisible(true);
                return null;
            }
        };
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task);
        service.shutdown();
    }

}
