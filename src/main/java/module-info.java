module com.example.javacolor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javacolor to javafx.fxml;
    exports com.example.javacolor;
    opens com.example.javacolor.controller;
}