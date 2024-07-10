package com.card.printing.app.cardprinting;

import com.card.printing.app.cardprinting.service.ArchiveExtractor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileListController{

    @FXML
    private ListView<String> listView;

    String loc;

    public void initialize() {
        // Specify the folder path
        String folderPath = "D:\\CardPrinting";

        // Read files from the specified folder
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            // Add file names to the ListView
            for (File file : files) {
                if (file.isFile()) {
                    listView.getItems().add(file.getAbsolutePath());
                }
            }
        } else {
            System.err.println("Failed to read files from the folder: " + folderPath);
        }
    }

    @FXML
    private void handleZip(ActionEvent event) {
        String selectedPath = listView.getSelectionModel().getSelectedItem();
        if (selectedPath != null) {
            File selectedFile = new File(selectedPath);
            if (!selectedFile.exists()) {
//                showAlert("File Error", "Selected file does not exist.");
                return;
            }

            // Prepare request object
            ArchiveExtractor archive = new ArchiveExtractor();
            archive.extract(selectedPath, "D:/output");// Adjust the output path as needed

        }


       /* public void Button1Action(ActionEvent event) {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Folder");

            // Optional: Set the initial directory
            File initialDirectory = new File("D:\\CardPrinting"); // Change to your desired folder
            if (initialDirectory.exists() && initialDirectory.isDirectory()) {
                directoryChooser.setInitialDirectory(initialDirectory);
            }

            // Show the open dialog to select a folder
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
//        File selectedDirectory = directoryChooser.showDialog(stage);

//        if (selectedDirectory != null && selectedDirectory.isDirectory()) {
            // Filter for zip files in the selected directory
            List<File> zipFiles = Arrays.stream(initialDirectory.listFiles())
                    .filter(file -> file.isFile() && file.getName().endsWith(".zip"))
                    .collect(Collectors.toList());

            if (!zipFiles.isEmpty()) {
                zipFiles.forEach(file -> listview.getItems().add(file.getAbsolutePath()));
            } else {
                System.out.println("No zip files found in the selected directory!");
            }*/
        }
    }
