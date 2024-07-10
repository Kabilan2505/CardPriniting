package com.card.printing.app.cardprinting;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    public void initialize() {

    }



    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println(username + password);

        if (username.equals("kabilan25") && password.equals("Admin@12345")) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");

            // Load the new FXML file
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                BorderPane helloRoot = loader.load();

                // Get the current scene and set its root to the loaded BorderPane
                Scene currentScene = usernameField.getScene();
                currentScene.setRoot(helloRoot);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    private boolean authenticate(String username, String password) {
        // Replace with your actual authentication logic
        return "user".equals(username) && "pass".equals(password);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleSignIn() {
        String user = usernameField.getText();
        String pass = passwordField.getText();
        // Add logic to handle sign-in here
        System.out.println("Username: " + user + ", Password: " + pass);
    }
}
