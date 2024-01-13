package com.example.project_client.view.controller.Truong;

import com.example.project_client.model.Ingredient;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class changeIngredientView {
    @FXML
    private TextField id, name, unit_Price, unit;
    private Ingredient ingredient;
    @FXML
    private void initialize() throws Exception {
        ingredient = ingredientView.getIngredient();
        System.out.println(ingredient.getName());
        setField();
    }
    @FXML
    private void cancel() throws IOException {
        Router.switchTo(Pages.INGREDIENT_VIEW);
    }
    @FXML
    private void confirm() throws IOException {
        Router.switchTo(Pages.INGREDIENT_VIEW);
    }
    private void setField() throws Exception {
        id.setPromptText(ingredient.getId().toString());
        id.textProperty().addListener((observableValue, oldValue, newValue) -> {
            ingredient.setId(Integer.parseInt(newValue));
        });
        name.setPromptText(ingredient.getName());
        name.textProperty().addListener((observableValue, oldValue, newValue) -> {
            ingredient.setName(newValue);
        });
        unit.setPromptText(ingredient.getUnit());
        unit.textProperty().addListener((observableValue, oldValue, newValue) -> {
            ingredient.setUnit(newValue);
        });
        unit_Price.setPromptText(ingredient.getUnit_Price().toString());
        unit_Price.textProperty().addListener((observableValue, oldValue, newValue) -> {
            ingredient.setUnit_Price(Integer.parseInt(newValue));
        });
    }
}
