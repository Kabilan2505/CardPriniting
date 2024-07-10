package com.card.printing.app.cardprinting.fxcontroller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {

    @FXML
    private AnchorPane slider;

    @FXML
    private Button batchButton;

    @FXML
    private Label Menu;

    @FXML
    private Button onDemandButton;

    @FXML
    private Button qualityButton;

    @FXML
    private Button reportsButton;


    private Button activeButton;

    @FXML
    private BorderPane bp;

    @FXML
    private AnchorPane ap;


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void setCenterContent(Parent content) {
      bp.setCenter(content);

    }
    private void setButtonActive(Button button) {
        if (activeButton != null) {
            activeButton.getStyleClass().remove("sideBarItem-active");
            activeButton.getStyleClass().add("sideBarItem");
        }
        button.getStyleClass().remove("sideBarItem");
        button.getStyleClass().add("sideBarItem-active");
        activeButton = button;
    }
    @FXML
    public void batch(MouseEvent mouseEvent) {

//        bp.setCenter(ap);
        loadPage("batch");
    }
    @FXML
    public void quality(MouseEvent mouseEvent) {
        loadPage("quality");
    }
    @FXML
    public void onDemand(MouseEvent mouseEvent) {
        loadPage("onDemand");
    }
    @FXML
    public void reports(MouseEvent mouseEvent) {
        loadPage("reports");
    }


    private void loadPage(String page) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
            bp.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
            // Optionally, you can add some error handling here, such as showing an alert to the user.
        }
    }

    public void handleBatch(ActionEvent actionEvent) {
        try {
            System.out.println(actionEvent);
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

    public void handleOnDemand(ActionEvent actionEvent) {
        try {
            System.out.println(actionEvent);
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

    public void handleQuality(ActionEvent actionEvent) {
        try {
            System.out.println(actionEvent);
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

    // Add additional methods as needed to handle events or data
}

