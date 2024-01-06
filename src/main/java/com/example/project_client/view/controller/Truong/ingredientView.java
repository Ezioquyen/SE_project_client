package com.example.project_client.view.controller.Truong;


import com.example.project_client.model.Ingredient;
import com.example.project_client.repository.IngredientRepository;
import com.example.project_client.repository.ProductRepository;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.io.IOException;
import java.util.List;


public final class ingredientView {
    private static Ingredient ingredient;
    public static Ingredient getIngredient() {
        return ingredient;
    }

    Button button;
    @FXML
    VBox vBox = new VBox();
    @Getter
    private List<Ingredient> ingredients;
    @FXML
    public void initialize() throws IOException{
        ingredients = IngredientRepository.getIngredientsApi();
        System.out.println(ingredients);
        for(Ingredient ingredient1 : ingredients){
            Button button1 = new Button(ingredient1.getName());
            button1.setId(String.valueOf(ingredient1.getId()));
            button1.setPrefWidth(100.0);
            button1.setOnAction(actionEvent ->{
                ingredient = ingredient1;
                button = button1;
            });
            vBox.getChildren().add(button1);
        }
    }
    @FXML
    public void cancel() throws IOException {
        Router.switchTo(Pages.MAIN_VIEW);
    }
    @FXML
    public void addIngredient(ActionEvent event) throws IOException {
        Router.switchTo(Pages.ADD_INGREDIENT);
    }
    @FXML
    public void changeIngredient(ActionEvent event) throws IOException {
        Router.switchTo(Pages.CHANGE_INGREDIENT);
    }
    @FXML
    public void deleteIngredient(ActionEvent event) throws Exception {
        try {
            IngredientRepository.deleteIngredient(button.getId());
            vBox.getChildren().remove(button);
        }
        catch (Exception e){
            System.out.println("Please choose Ingredient to delete");
        }
    }
}
