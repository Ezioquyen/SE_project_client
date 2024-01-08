package com.example.project_client.view.controller.yin;
import com.example.project_client.model.User;
import com.example.project_client.repository.UserRepository;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;


public class LoginView {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Label invalidLoginLabel;

    @FXML
    private void handleLoginButton(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println("abc+"+username+password);

        if ("000".equals(username) && "000".equals(password)) {
            Router.switchTo(Pages.ADMIN_VIEW);
        } else if (usernameField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty()) {
            invalidLoginLabel.setText("Please enter username/password.");
        } else {
//        } else if (loginViewModel.loginUser(username)) {
            List<User> listUser = new UserRepository().getAllUsers();
            for (User user: listUser) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    Router.switchTo(Pages.MAIN_VIEW);
                    break;
                }
                    }
                }
            }




    @FXML
    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
