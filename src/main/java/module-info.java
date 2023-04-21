module com.example.project3apma {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project3apma to javafx.fxml;
    exports com.example.project3apma;
}