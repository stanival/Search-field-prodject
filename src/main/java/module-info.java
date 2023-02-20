module com.example.search_field_object_java {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.search_field_object_java to javafx.fxml;
    exports com.example.search_field_object_java;
}