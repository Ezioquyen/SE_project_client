package com.example.project_client.view.controller.Truong;

import com.example.project_client.model.Ingredient;
import com.example.project_client.repository.IngredientRepository;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.Getter;

import java.io.IOException;

public class addIngredientView {
    @Getter
    private Ingredient ingredient = new Ingredient();
    @FXML
    private TextField id, name, unit_Price, unit;
    @FXML
    public void cancel() throws IOException {
        Router.switchTo(Pages.INGREDIENT_VIEW);
    }
    @FXML
    public void confirm() throws Exception {
        try {
            setIngredient();
            IngredientRepository.saveIngredient(ingredient);
            Router.switchTo(Pages.INGREDIENT_VIEW);
        }
        catch (Exception e) {
            System.out.println("ERROR");
        }
    }
    private void setIngredient() throws Exception{
        ingredient.setId(Integer.parseInt(id.getText()));
        ingredient.setName(name.getText());
        ingredient.setUnit_Price(Integer.parseInt(unit_Price.getText()));
        ingredient.setUnit(unit.getText());
    }
}
