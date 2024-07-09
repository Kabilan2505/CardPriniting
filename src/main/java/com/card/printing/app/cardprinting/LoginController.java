package com.card.printing.app.cardprinting;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private void initialize() {
        // Initialize any required logic here
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                Parent sidebarRoot = loader.load();

                // Create a new stage for the sidebar
                Stage sidebarStage = new Stage();
                sidebarStage.setScene(new Scene(sidebarRoot));
                sidebarStage.show();

                // Optionally, close the login window
//                Stage loginStage = (Stage) rootPane.getScene().getWindow();
//                loginStage.close();

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
