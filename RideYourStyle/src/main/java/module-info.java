module codes.rideyourstyle {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens codes.rideyourstyle to javafx.fxml;
    exports codes.rideyourstyle;
}