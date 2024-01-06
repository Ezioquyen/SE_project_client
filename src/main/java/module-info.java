module com.example.project_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires lombok;

    requires atlantafx.base;
    requires com.fasterxml.jackson.databind;

    opens com.example.project_client to javafx.fxml;
    exports com.example.project_client;
    opens com.example.project_client.model;
    exports com.example.project_client.model;
    exports com.example.project_client.view.controller.Quyen;
    opens com.example.project_client.view.controller.Quyen to javafx.fxml;
    exports com.example.project_client.view.controller.Truong;
    opens com.example.project_client.view.controller.Truong to javafx.fxml;
}