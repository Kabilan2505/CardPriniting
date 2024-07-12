package com.card.printing.app.cardprinting;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;


    @FXML
    private ImageView loginImage;

    @FXML
    public void initialize() {
        try {
            FileInputStream inputStream = new FileInputStream("src/main/resources/image/logo.png");
            Image image = new Image(inputStream);
            loginImage.setImage(image);
            inputStream.close();
        } catch (IOException e) {
            System.out.println("Image file not found.");
        }
    }

    @FXML
    private void handleLoginButtonAction() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println(username + password);

        if (username.equals("superadmin") && password.equals("superadmin")) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");


            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            BorderPane sidebarRoot = loader.load();
            Scene currentScene = usernameField.getScene();
            currentScene.setRoot(sidebarRoot);

        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    private boolean authenticate(String username, String password) {
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