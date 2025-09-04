module com.example.personalinformationmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.personalinformationmanager to javafx.fxml;
    exports com.example.personalinformationmanager;
}