package com.example.project_client.view.controller.Quyen.components;

import atlantafx.base.controls.Card;
import atlantafx.base.theme.Styles;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;


public class ProductView extends Card {
    public ProductView(String productName, String price, String availability, String imagePath) {

        getStyleClass().add(Styles.INTERACTIVE);
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
        setHeader(imageView);
        setBody(vBox);

    }
}
