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

        @FXML
        private Button reportButton;

        @FXML
        private Label contentLabel;

        @FXML
        private VBox vBox;

    private void setCenterContent(Parent root) {
        borderpane.setCenter(root);
    }

        public void initialize() {
            try {
                FileInputStream inputStream = new FileInputStream("src/main/resources/image/logo.png");
                Image image = new Image(inputStream);
                headerImage.setImage(image);
                inputStream.close();
            } catch (IOException e) {
                System.out.println("Image file not found.");
            }
        }

        @FXML
        private void handleButtonClick(ActionEvent event) {
            Button clickedButton = (Button) event.getSource();
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
//            ChoiceBox<String> choiceBox = new ChoiceBox<>();
//            choiceBox.getItems().addAll("Option 1", "Option 2", "Option 3", "Option 4", "Option 5");
//
//            // Handle selection changes (optional)
//            choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//                System.out.println("Selected item: " + newValue);
//            });
//
//            ((BorderPane) batchButton.getScene().getRoot()).setCenter(choiceBox);

            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("batch.fxml"));
            Parent root = loader.load();
            setCenterContent(root);
            System.out.println(root);

            // Create a new stage for the batch view

            // Optionally, you can close the current stage (if needed)
            // Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            // currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        }



    private void handleOnDemandButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("onDemand.fxml"));
            Parent root = loader.load();
            setCenterContent(root);
            System.out.println(root);

            // Create a new stage for the batch view

            // Optionally, you can close the current stage (if needed)
            // Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            // currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        }

        private void handleQualityButton() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("quality.fxml"));
                Parent root = loader.load();
                setCenterContent(root);
                System.out.println(root);

                // Create a new stage for the batch view

                // Optionally, you can close the current stage (if needed)
                // Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                // currentStage.close();

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

                // Create a new stage for the batch view

                // Optionally, you can close the current stage (if needed)
                // Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                // currentStage.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



//    public void handleBatch(ActionEvent actionEvent) {
//            ha
//        try {
//            System.out.println(actionEvent);
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("batch.fxml"));
//            Parent root = loader.load();
//            setCenterContent(root);
//            System.out.println(root);
//
//            // Create a new stage for the batch view
//
//            // Optionally, you can close the current stage (if needed)
//            // Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//            // currentStage.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void handleOnDemandButton(ActionEvent actionEvent) {
//        try {
//            System.out.println(actionEvent);
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("onDemand.fxml"));
//            Parent root = loader.load();
//            setCenterContent(root);
//            System.out.println(root);
//
//            // Create a new stage for the batch view
//
//            // Optionally, you can close the current stage (if needed)
//            // Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//            // currentStage.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void handleQualityButton(ActionEvent actionEvent) {
//        try {
//            System.out.println(actionEvent);
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("quality.fxml"));
//            Parent root = loader.load();
//            setCenterContent(root);
//            System.out.println(root);
//
//            // Create a new stage for the batch view
//
//            // Optionally, you can close the current stage (if needed)
//            // Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//            // currentStage.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}
