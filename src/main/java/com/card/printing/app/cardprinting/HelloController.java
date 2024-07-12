package com.card.printing.app.cardprinting;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.IOException;


public class HelloController {

        @FXML
        private BorderPane borderpane;

        @FXML
        private ImageView headerImage;

        @FXML
        private Button batchButton;

        @FXML
        private Button onDemandButton;

        @FXML
        private Button qualityButton;


    private void setCenterContent(Parent root) {
        borderpane.setCenter(root);
    }

        public void initialize() {
            try {
                FileInputStream inputStream = new FileInputStream("src/main/resources/image/logo.png");
                Image image = new Image(inputStream);
                headerImage.setImage(image);
                inputStream.close();
                handleBatchButton();
            } catch (IOException e) {
                System.out.println("Image file not found.");
            }
        }

        @FXML
        private void handleButtonClick(ActionEvent event) {
            Button clickedButton = (Button) event.getSource();

            // Clear active class from all buttons
            batchButton.getStyleClass().remove("active");
            onDemandButton.getStyleClass().remove("active");
            qualityButton.getStyleClass().remove("active");

            // Add active class to the clicked button
            clickedButton.getStyleClass().add("active");
            switch (clickedButton.getId()) {
                case "batchButton":
                    handleBatchButton();
                    break;
                case "onDemandButton":
                    handleOnDemandButton();
                    break;
                case "qualityButton":
                    handleQualityButton();
                    break;
                case "reportButton":
                    handleReportButton();
                    break;
                default:
                    System.out.println("Unknown button clicked");
                    break;
            }
        }

        private void handleBatchButton() {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("batch.fxml"));
            Parent root = loader.load();
            setCenterContent(root);
            System.out.println("batch");

        } catch (IOException e) {
            e.printStackTrace();
        }
        }

    private void handleOnDemandButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("onDemand.fxml"));
            Parent root = loader.load();
            setCenterContent(root);
            System.out.println("Demand");
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

        private void handleQualityButton() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("quality.fxml"));
                Parent root = loader.load();
                setCenterContent(root);
                System.out.println("quality");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void handleReportButton() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("report.fxml"));
                Parent root = loader.load();
                setCenterContent(root);
                System.out.println(root);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
