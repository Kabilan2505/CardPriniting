package com.card.printing.app.cardprinting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CardPrintingApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CardPrintingApplication.class.getResource("Login2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        String css=this.getClass().getResource("Style.css").toExternalForm();
        scene.getStylesheets().add(css);
//        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}

//package com.card.printing.app.cardprinting;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class CardPrintingApplication extends Application {
//
//    private Stage primaryStage;
//    private Scene loginScene;
//    private Scene mainScene;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        this.primaryStage = primaryStage;
//
//        // Load the login scene initially
//        loadLoginScene();
//        loadMainScene();
//
//
//        // Set the title and show the primary stage
//        primaryStage.setTitle("Login");
//        primaryStage.setScene(loginScene);
//        primaryStage.show();
//    }
//
//    // Method to load the login scene from FXML
//    private void loadLoginScene() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login2.fxml"));
//        Parent root = loader.load();
//        loginScene = new Scene(root, 800, 600);
//        loginScene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
//    }
//
//    private void loadMainScene() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
//        Parent root = loader.load();
//        mainScene = new Scene(root, 800, 600);
//        mainScene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
//    }
//
//
//    // Method to switch from the login scene to the main application scene
//    public void switchToMainScene() {
//        primaryStage.setScene(mainScene);
//        primaryStage.setTitle("Main Application");
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}

