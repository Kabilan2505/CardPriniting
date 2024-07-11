package com.card.printing.app.cardprinting;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExtractedFileController {

    @FXML
    public ListView textlistview;
    public VBox textview;


    public void setFilePath(String selectedFileName, List<String> txtFiles) {
        System.out.println("textFiles"+txtFiles);
        if (txtFiles != null) {
            // Clear previous items
            textlistview.getItems().clear();
            // Add file names to the ListView
            for (String file : txtFiles) {

                textlistview.getItems().add(file);

            }
        } else {
//            System.err.println("Failed to read files from the folder: " + folderPath);
        }
    }
    @FXML
    public void previewText(ActionEvent actionEvent) {

        String selectedFileName = (String) textlistview.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("preview.fxml"));
        try {
            Parent newContent = loader.load();
            PreviewController previewController = loader.getController();
            previewController.setFilePath(selectedFileName); // Pass the selected file name
            textview.getChildren().setAll(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
