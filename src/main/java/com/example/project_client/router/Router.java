package com.example.project_client.router;

import javafx.scene.layout.VBox;
import lombok.Getter;

public class Router {
    @Getter
    private static VBox root;
    public static void setRoot(VBox root) {
        Router.root = root;
    }

}
