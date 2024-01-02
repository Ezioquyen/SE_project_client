package com.example.project_client.router;
import com.example.project_client.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public final class Router {
    @Getter
    private static VBox root = new VBox();
    private static final String WINDOW_TITLE = "";
    private static final Double WINDOW_WIDTH = 800.0D;
    private static final Double WINDOW_HEIGHT = 600.0D;
    private static Router router;
    private static Object mainRef;
    private static Stage window;
    private static Stage dialog;
    private static String windowTitle;
    private static Double windowWidth;
    private static Double windowHeight;
    private static final Map<Pages,String> routerLabel = new HashMap<>();
    private static final Map<Pages,Object> dataLabel = new HashMap<>();
    public static void init(){
        window.setTitle(windowTitle);
        window.setScene(new Scene(root));
        window.show();
    }
    public static void setRoot(VBox root) {
        Router.root = root;
    }
    public static void setRouter(Pages page, String path){
        routerLabel.put(page,path);
    }
    public static void setRouter(Pages page, String path, Object data){
        routerLabel.put(page,path);
        dataLabel.put(page,data);
    }

    public static void switchTo(Pages page) throws IOException {
        String scenePath = routerLabel.get(page);
        Parent resource = FXMLLoader.load(HelloApplication.class.getResource(scenePath));
        root.getChildren().setAll(resource);
    }
    public static void goTo(Pages page) throws IOException {
        String scenePath = routerLabel.get(page);
        Parent resource = FXMLLoader.load(HelloApplication.class.getResource(scenePath));
        window.setTitle(windowTitle);
        window.setScene(new Scene(resource));
        window.show();
    }
    public  static void showDialog(Pages page) throws IOException {
        dialog = new Stage();
        String scenePath = routerLabel.get(page);
        Parent resource = FXMLLoader.load(HelloApplication.class.getResource(scenePath));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(window);
        Scene dialogScene = new Scene(resource);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    public static void closeDialog(){
        if(dialog.isShowing()) dialog.close();
    }

    public static void bind(Object ref, Stage win) {
        checkInstances(ref, win);
    }

    public static void bind(Object ref, Stage win, String winTitle) {
        checkInstances(ref, win);
        windowTitle = winTitle;
    }

    public static void bind(Object ref, Stage win, double winWidth, double winHeight) {
        checkInstances(ref, win);
        windowWidth = winWidth;
        windowHeight = winHeight;
    }

    public static void bind(Object ref, Stage win, String winTitle, double winWidth, double winHeight) {
        checkInstances(ref, win);
        root.setPrefSize(winWidth,winHeight);
        windowTitle = winTitle;
        windowWidth = winWidth;
        windowHeight = winHeight;
    }

    private static void checkInstances(Object ref, Stage win) {
        if (mainRef == null) {
            mainRef = ref;
        }

        if (router == null) {
            router = new Router();
        }

        if (window == null) {
            window = win;
        }

    }


}
