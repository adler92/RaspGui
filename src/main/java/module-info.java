module com.example.raspgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.raspgui to javafx.fxml;
    exports com.example.raspgui;
}