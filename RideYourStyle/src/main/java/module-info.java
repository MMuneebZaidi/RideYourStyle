module codes.rideyourstyle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires jasperreports;
    requires com.jfoenix;
    requires mysql.connector.j;

    opens codes.rideyourstyle to javafx.fxml;
    exports codes.rideyourstyle;
}