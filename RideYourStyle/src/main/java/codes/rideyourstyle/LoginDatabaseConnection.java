package codes.rideyourstyle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDatabaseConnection {
    public Connection DatabaseLink;


    public Connection getDatabaseLink() {
        String databaseName = "admin/user";
        String databaseUser = "root";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost/"+databaseName;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            DatabaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return DatabaseLink;
    }
    public ObservableList<Info> retrieveDatabase(String src){
        ObservableList<Info> list = FXCollections.observableArrayList();
        Connection connectDB = getDatabaseLink();
        String query = "SELECT * FROM "+src;
        try {
            Statement stm = connectDB.createStatement();
            ResultSet output = stm.executeQuery(query);
            while (output.next()) {
                int id = output.getInt("id");
                String Name = output.getString("Name");
                String Username = output.getString("Username");
                String Email = output.getString("Email");
                String Password = output.getString("Password");
                String CNIC = output.getString("CNIC");
                String PhoneNumber = output.getString("Phone Number");
                int Age = output.getInt("Age");
                String City = output.getString("City");
                switch (src) {
                    case "user" -> list.add(new UserInfo(id,Name, Username, Email, Password, PhoneNumber, Age, CNIC, City));
                    case "admin" -> list.add(new AdminInfo(id,Name, Username, Email, Password, PhoneNumber, Age, CNIC, City));
                }
            }
        }
            catch (Exception e) {
            Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE,null,e);
        }
        return list;
    }
    public void insertData(String src, Info data) throws SQLException {
        String insert="INSERT INTO `"+src+"`(`Name`, `Username`, `Email`, `Password`, `CNIC`, `Phone Number`, `Age`, `City`) ";
        String values="VALUES ('"+data.Name+"','"+data.Username+"','"+data.Email+"','"+data.Password+"','"+data.CNIC+
                "','"+data.PhoneNumber+"','"+data.Age+"','"+data.City+"')";
        String query = insert+values;
        Connection connectDB = getDatabaseLink();
        Statement stm = connectDB.createStatement();
        stm.execute(query);
    }
    public void insertGarageData(Info data) {
        try {
            String insert = "INSERT INTO `garage`(`user_id`)";
            String values = "VALUES ('"+data.id+"')";
            String query = insert + values;
            Connection connectDB = getDatabaseLink();
            Statement stm = connectDB.createStatement();
            stm.execute(query);
        } catch (Exception e) {
            Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void UpdateGarageData(Info data) {
        try {
            String update = "UPDATE `garage` SET `car1` = NULL, `car2` = NULL, `car3` = NULL, `car4` = NULL, `car5` = NULL ";
            String id = "WHERE user_id = '"+data.id+"'";
            String query = update+id;
            Connection connectDB = getDatabaseLink();
            Statement stm = connectDB.createStatement();
            stm.execute(query);
        } catch (Exception e) {
            Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void insertPendingData(Info data,StringBuilder cars) {
        try {
            String insert = "INSERT INTO `pendings`(`user_id`, `Listed`, `Status`)";
            String values = "VALUES ('"+data.id+"','"+cars+"','"+"Request Pending"+"')";
            String query = insert + values;
            Connection connectDB = getDatabaseLink();
            Statement stm = connectDB.createStatement();
            stm.execute(query);
        } catch (Exception e) {
            Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void insertSellPurchaseData(Pendings data) {
        try {
            String insert = "INSERT INTO `sell/purchase`(`user_id`, `Listed`, `Status`, `Managed By`)";
            String values = "VALUES ('"+data.getUser_id()+"','"+data.getRequests()+"','"+data.getStatus()+"','"+data.getManagedBy()+"')";
            String query = insert + values;
            Connection connectDB = getDatabaseLink();
            Statement stm = connectDB.createStatement();
            stm.execute(query);
        } catch (Exception e) {
            Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
