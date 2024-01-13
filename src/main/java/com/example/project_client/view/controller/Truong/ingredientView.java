package com.example.project_client.view.controller.Truong;

import com.example.project_client.model.Ingredient;
import com.example.project_client.repository.IngredientRepository;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


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
    private void readIngredient() {
        try {
            ingredient = tableView.getSelectionModel().getSelectedItem();
            if(ingredient == null) {
                throw new Exception("Please choose Ingredient to view information");
            }
            Router.switchTo(Pages.READ_INGREDIENT);
        }
        catch (Exception e) {
            raiseAlert(e.getMessage());
        }
    }
    @FXML
    public void addIngredient() throws IOException {
        Router.switchTo(Pages.ADD_INGREDIENT);
    }
    @FXML
    public void changeIngredient() {
        try {
            ingredient = tableView.getSelectionModel().getSelectedItem();
            if(ingredient == null) {
                throw new Exception("Please choose Ingredient to change");
            }
            Router.switchTo(Pages.CHANGE_INGREDIENT);
        }
        catch (Exception e) {
            raiseAlert(e.getMessage());
        }
    }
    @FXML
    public void deleteIngredient() {
        try {
            ingredient = tableView.getSelectionModel().getSelectedItem();
            if(ingredient == null) {
                throw new Exception("Please choose Ingredient to delete");
            }
            askDelete();
        }
        catch (Exception e){
            raiseAlert(e.getMessage());
        }
    }
    private void askDelete() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to delete Ingredient");
        alert.setContentText("choose your option");

        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();

        String message;
        if(result.get().getButtonData() == ButtonBar.ButtonData.YES){
            try {
                IngredientRepository.deleteIngredient(ingredient.getId().toString());
                tableList.remove(ingredient);
                message = "Deleted";
            }
            catch (Exception e) {
                message = "Cannot delete";
            }
        } else {
            message = "Cancel delete";
        }
    }
    private void raiseAlert(String alertText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Notification");
        alert.setContentText(alertText);
        alert.show();
    }
    private void setTableView() throws IOException {
        ingredients = IngredientRepository.getIngredientsApi();
        tableList = FXCollections.observableArrayList(ingredients);
        tableView.setItems(tableList);
    }
    private void setColumn() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        unit_Price.setCellValueFactory(new PropertyValueFactory<>("unit_Price"));
    }
    public static Ingredient getIngredient() {
        return ingredient;
    }

}
