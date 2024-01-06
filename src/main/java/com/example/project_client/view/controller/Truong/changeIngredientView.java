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
    public void initialize() throws Exception {
        ingredient = ingredientView.getIngredient();
        System.out.println(ingredient.getName());
        id.setPromptText(ingredient.getId().toString());
        name.setPromptText(ingredient.getName());
        unit_Price.setPromptText(ingredient.getUnit_Price().toString());
        unit.setPromptText(ingredient.getUnit());
    }
    @FXML
    public void cancel() throws IOException {
        Router.switchTo(Pages.INGREDIENT_VIEW);
    }
    @FXML
    public void confirm() throws IOException {
        Router.switchTo(Pages.INGREDIENT_VIEW);
    }
    private Boolean setIngredient(){
        try{
            ingredient.setId(Integer.parseInt(id.getText()));
            ingredient.setName(name.getText());
            ingredient.setUnit_Price(Integer.parseInt(unit_Price.getText()));
            ingredient.setUnit(unit.getText());
            return Boolean.TRUE;
        }
        catch (Exception e){
            System.out.println("ERROR");
            return Boolean.FALSE;
        }
    }
}
