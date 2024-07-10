package com.card.printing.app.cardprinting.fxcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.File;

public class FileListController{

    @FXML
    private ListView<String> listView;

    public void initialize() {
        // Specify the folder path
        String folderPath = "E:\\CardPrinting";

        // Read files from the specified folder
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            // Add file names to the ListView
            for (File file : files) {
                if (file.isFile()) {
                    String loc = file.getAbsolutePath();
                    listView.getItems().add(file.getName());
                }
            }
        } else {
            System.err.println("Failed to read files from the folder: " + folderPath);
        }
    }

    @FXML
    public void handleShareItem(ActionEvent actionEvent) {
        String selectedItem = listView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            // Perform sharing logic (e.g., print, send, etc.)
            System.out.println("Shared item: " + selectedItem);
        } else {
            System.out.println("No item selected.");
        }
    }
}
