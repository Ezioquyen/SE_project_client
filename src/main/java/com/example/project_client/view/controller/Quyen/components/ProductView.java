package com.example.project_client.view.controller.Quyen.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;


public class ProductView extends VBox {
    public ProductView(String productName, String price, String availability, String imagePath) {

        setSpacing(10);
        setPadding(new Insets(10));

        ImageView imageView = new ImageView();
        imageView.setFitHeight(150.0);
        imageView.setFitWidth(200.0);
        imageView.setPreserveRatio(true);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        imageView.setImage(image);

        Label nameLabel = new Label(productName);
        Label priceLabel = new Label(price + " VND");
        Label availabilityLabel = new Label(availability);
        VBox vBox = new VBox(nameLabel, priceLabel, availabilityLabel);
        getChildren().addAll(imageView, vBox);
        setMargin(vBox,new  Insets(0,0,0,15));
    }
}
