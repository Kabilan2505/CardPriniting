package com.card.printing.app.cardprinting;


import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.action.Action;

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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtonActive(onDemandButton);
        onDemandButton.setOnAction(event -> setButtonActive(onDemandButton));
        batchButton.setOnAction(event -> setButtonActive(batchButton));
        qualityButton.setOnAction(event -> setButtonActive(qualityButton));
        reportsButton.setOnAction(event -> setButtonActive(reportsButton));
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

    public void batch(MouseEvent mouseEvent) {
    }

    public void quality(MouseEvent mouseEvent) {
    }

    public void onDemand(MouseEvent mouseEvent) {
    }

    public void reports(MouseEvent mouseEvent) {
    }


    // Add additional methods as needed to handle events or data
}

