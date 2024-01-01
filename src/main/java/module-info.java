module com.example.project_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires lombok;

    opens com.example.project_client to javafx.fxml;
    exports com.example.project_client;
    opens com.example.project_client.view.controller to javafx.fxml;
    exports com.example.project_client.view.controller;
}