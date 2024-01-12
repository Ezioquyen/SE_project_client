package com.example.project_client.view.controller.Truong;

import com.example.project_client.model.Ingredient;
import com.example.project_client.repository.IngredientRepository;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;

import java.io.IOException;
import java.util.List;


public final class ingredientView {
    private static Ingredient ingredient;
    @FXML
    TableColumn<Ingredient, Integer> id;
    @FXML
    TableColumn<Ingredient, String> name;
    @FXML
    TableColumn<Ingredient, String> unit;
    @FXML
    TableColumn<Ingredient, Integer> unit_Price;
    @Getter
    private List<Ingredient> ingredients;
    @FXML
    ObservableList<Ingredient> tableList;
    @FXML
    TableView<Ingredient> tableView;

    public void initialize(){
        try {
            setTableView();
            setColumn();
        }
        catch (Exception e) {
            System.out.println("ERROR");
        }
    }
    @FXML
    public void cancel() throws IOException {
        Router.switchTo(Pages.MAIN_VIEW);
    }
    @FXML
    public void addIngredient() throws IOException {
        Router.switchTo(Pages.ADD_INGREDIENT);
    }
    @FXML
    public void changeIngredient() {
        try {
            ingredient = tableView.getSelectionModel().getSelectedItem();
            Router.switchTo(Pages.CHANGE_INGREDIENT);
        }
        catch (Exception e) {
            System.out.println("ERROR");
        }
    }
    @FXML
    public void deleteIngredient() {
        try {
            ingredient = tableView.getSelectionModel().getSelectedItem();
            IngredientRepository.deleteIngredient(ingredient.getId().toString());
            tableList.remove(ingredient);
        }
        catch (Exception e){
            System.out.println("Please choose Ingredient to delete");
        }
    }
    private void setTableView() throws IOException {
        ingredients = IngredientRepository.getIngredientsApi();
        tableList = FXCollections.observableArrayList(ingredients);
        tableView.setItems(tableList);
    }
    private void setColumn() throws IOException {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        unit_Price.setCellValueFactory(new PropertyValueFactory<>("unit_Price"));
    }
    public static Ingredient getIngredient() {
        return ingredient;
    }

}
