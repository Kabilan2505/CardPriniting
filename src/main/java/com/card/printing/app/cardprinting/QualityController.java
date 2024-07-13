package com.card.printing.app.cardprinting;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class QualityController {
    public TextField enterPcn;
    public TextField reason;
//    @FXML
//    public void openpopup(ActionEvent actionEvent) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("popup.fxml"));
//        Stage stage = new Stage();
//        stage.setScene(new Scene(loader.load()));
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.showAndWait();
//    }
    public void clearField(ActionEvent actionEvent) {
        enterPcn.clear();
        reason.clear();
    }

    public void submit(ActionEvent actionEvent) throws IOException {
        String enteredPCn=enterPcn.getText();
        String rsn=reason.getText();
        System.out.println(enteredPCn);
        System.out.println(rsn);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("popup.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);

        PopupController popupController = loader.getController();

        if (enterPcn.getText().isEmpty() || reason.getText().isEmpty()) {
            popupController.popupMsg.setText("Enter the required fields");
//            popupController.dialogMain.setStyle("-fx-border-color: red;");
            popupController.dialog.setStyle("-fx-border-color: red;");
        } else {
            popupController.popupMsg.setText("Your request has been submitted");
//            popupController.dialogMain.setStyle("-fx-border-color: green;");
            popupController.dialog.setStyle("-fx-border-color: green;");
        }

        stage.showAndWait();
    }
}
