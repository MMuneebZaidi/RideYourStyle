package codes.rideyourstyle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    public Connection DatabaseLink;

    public Connection getDatabaseLink() {
        String databaseName = "rideyourstyle";
        String databaseUser = "root";
        String databasePassword = "root";
        String url = "jdbc:mysql://localhost/"+databaseName;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            DatabaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return DatabaseLink;
    }
    ObservableList<Vehicle> allVehicles = FXCollections.observableArrayList();

    public ObservableList<Vehicle> retrieveDatabase(){

        Connection connectDB = getDatabaseLink();
        String query;

        for(int i=1;i<=6;i++){
            if(i==1) {
                query = "SELECT * FROM bentley";
            }else if (i==2){
                query = "SELECT * FROM bmw";
            }else if (i==3){
                query = "SELECT * FROM chevrolet";
            }else if (i==4){
                query = "SELECT * FROM mercedes";
            }else if (i==5){
                query = "SELECT * FROM porsche";
            }else {
                query = "SELECT * FROM rollsroyce";
            }
            try{
                Statement stm = connectDB.createStatement();
                ResultSet output = stm.executeQuery(query);

                while (output.next()){

                    String name = output.getString("Name");
                    String engine = output.getString("Engine");
                    String transmissionType = output.getString("Transmission Type");
                    String enginePower = output.getString("Engine Power");
                    String topSpeed = output.getString("Top Speed");
                    String acceleration = output.getString("Acceleration");
                    String mileage = output.getString("Mileage");
                    String fuelType = output.getString("Fuel Type");
                    String fuelCapacity = output.getString("Fuel Tank");
                    String bodyType = output.getString("Body Type");
                    String seatingCapacity = output.getString("Seating Capacity");
                    String doors = output.getString("Doors");
                    String wheelSize = output.getString("Wheel Size");
                    String convertible = output.getString("Convertible");
                    String rating = output.getString("Rating");
                    String price = output.getString("Price");
                    String stock = output.getString("Stock");
                    Blob image = output.getBlob("Image");

                    switch (query) {
                        case "SELECT * FROM bentley" ->
                                allVehicles.add(new Bently(name, engine, transmissionType, enginePower, topSpeed, acceleration, mileage, fuelType, bodyType, price, rating, seatingCapacity, convertible, doors, wheelSize, fuelCapacity, stock, image));
                        case "SELECT * FROM bmw" ->
                                allVehicles.add(new BMW(name, engine, transmissionType, enginePower, topSpeed, acceleration, mileage, fuelType, bodyType, price, rating, seatingCapacity, convertible, doors, wheelSize, fuelCapacity, stock, image));
                        case "SELECT * FROM chevrolet" ->
                                allVehicles.add(new Cheverolet(name, engine, transmissionType, enginePower, topSpeed, acceleration, mileage, fuelType, bodyType, price, rating, seatingCapacity, convertible, doors, wheelSize, fuelCapacity, stock, image));
                        case "SELECT * FROM mercedes" ->
                                allVehicles.add(new Mercedes(name, engine, transmissionType, enginePower, topSpeed, acceleration, mileage, fuelType, bodyType, price, rating, seatingCapacity, convertible, doors, wheelSize, fuelCapacity, stock, image));
                        case "SELECT * FROM porsche" ->
                                allVehicles.add(new Porsche(name, engine, transmissionType, enginePower, topSpeed, acceleration, mileage, fuelType, bodyType, price, rating, seatingCapacity, convertible, doors, wheelSize, fuelCapacity, stock, image));
                        case "SELECT * FROM rollsroyce" ->
                                allVehicles.add(new RollsRoyce(name, engine, transmissionType, enginePower, topSpeed, acceleration, mileage, fuelType, bodyType, price, rating, seatingCapacity, convertible, doors, wheelSize, fuelCapacity, stock, image));
                    }
                }
            }catch (Exception e){
                Logger.getLogger(FindCarController.class.getName()).log(Level.SEVERE,null,e);
            }
        }
        return  allVehicles;
    }
}
