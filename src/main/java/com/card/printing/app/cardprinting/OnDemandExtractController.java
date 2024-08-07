package com.card.printing.app.cardprinting;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OnDemandExtractController {


    public VBox onedemandtextview;
    public ListView<String> ondemandtextlistview;

    public void setFilePath(String selectedFileName, List<String> txtFiles) {
        System.out.println("textFiles"+txtFiles);
        if (txtFiles != null) {
            // Clear previous items
            ondemandtextlistview.getItems().clear();
            // Add file names to the ListView
            for ( String file : txtFiles) {
                ondemandtextlistview.getItems().add(file);

            }
        } else {
//            System.err.println("Failed to read files from the folder: " + folderPath);
        }
    }
    @FXML
    public void previewText1(ActionEvent actionEvent) {

        String selectedFileName = (String) ondemandtextlistview.getSelectionModel().getSelectedItem();
        System.out.println(" preview "+selectedFileName);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("preview.fxml"));
        try {
            Parent newContent = loader.load();
            PreviewController previewController = loader.getController();
            previewController.setFilePath(selectedFileName); // Pass the selected file name
            onedemandtextview.getChildren().setAll(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
