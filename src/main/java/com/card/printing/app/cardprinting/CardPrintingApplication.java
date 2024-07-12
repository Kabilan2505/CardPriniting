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
//        stage.setFullScreen(true);
        stage.show();
    }

    public static void javaFx(String[] args) {
        launch();
    }
}
