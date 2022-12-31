module codes.rideyourstyle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires mysql.connector.j;
    requires com.jfoenix;
    opens codes.rideyourstyle to javafx.fxml;
    exports codes.rideyourstyle;
}