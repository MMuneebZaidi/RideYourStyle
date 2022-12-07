package codes.rideyourstyle;
import java.sql.Connection;
import java.sql.DriverManager;
public class DatabaseConnection {
    public Connection DatabaseLink;

    public Connection getDatabaseLink() {
        String databaseName = "";
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
}
