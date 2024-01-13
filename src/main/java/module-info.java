module com.example.project_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires lombok;
    requires atlantafx.styles;
    requires org.kordamp.ikonli.material2;
    requires itextpdf;
    requires atlantafx.base;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens com.example.project_client to javafx.fxml;
    exports com.example.project_client;
    opens com.example.project_client.model;
    exports com.example.project_client.model;
    exports com.example.project_client.view.controller.Quyen;
    opens com.example.project_client.view.controller.Quyen to javafx.fxml;
    exports com.example.project_client.view.controller.Khai;
    opens com.example.project_client.view.controller.Khai to javafx.fxml;
    exports com.example.project_client.view.controller.Truong;
    opens com.example.project_client.view.controller.Truong to javafx.fxml;
    exports com.example.project_client.view.controller.Quyen.event;
    opens com.example.project_client.view.controller.Quyen.event to javafx.fxml;
}