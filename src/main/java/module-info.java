module com.example.egg_timer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.egg_timer to javafx.fxml;
    exports com.example.egg_timer;
}