package com.card.printing.app.cardprinting;

import com.card.printing.app.cardprinting.service.ArchiveExtractor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OnDemandController {

    @FXML
    public VBox ondemandview;

    @FXML
    public ListView<String> ondemandlistView;

    String outputPath="E:/output";

    List<String> txtFile;

    String folderPath = "E:\\ondemand\\";

    private final ObservableList<FileRecord> fileRecords = FXCollections.observableArrayList();

    public void initialize() {
        // Specify the folder path
        // Specify the folder path



        // Read files from the specified folder
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            // Add file names to the ListView
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println("extracted file" + file.getName());
                    ondemandlistView.getItems().add(file.getName());
                    ondemandlistView.setStyle("-fx-font-size: 20px");
                }
            }
        } else {
            System.err.println("Failed to read files from the folder: " + folderPath);
        }
    }

    @FXML
    public void ondemandUnZip(ActionEvent actionEvent) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("extracted-file.fxml"));
//        Parent newContent = loader.load();
//        batchView.getChildren().setAll(newContent);

        String selectedPath = ondemandlistView.getSelectionModel().getSelectedItem();
        String absolutePath = folderPath + selectedPath;
        System.out.println("selectedPath"+absolutePath);
        if (selectedPath != null) {
            File selectedFile = new File(absolutePath);
            if (!selectedFile.exists()) {
//                showAlert("File Error", "Selected file does not exist.");
                return;
            }
            System.out.println("outputPath"+outputPath);
            // Prepare request object
            ArchiveExtractor archive = new ArchiveExtractor();
            archive.extract(absolutePath, outputPath);// Adjust the output path as needed
            System.out.println("selectedPath"+selectedPath);
            txtFile =archive.getTextFeild();
            addFileRecords(txtFile);

//            extractedFileController.initialize();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ondemandextracted.fxml"));
            try {
                Parent newContent = loader.load();
                OnDemandExtractController onDemandExtractController = loader.getController();
                onDemandExtractController.setFilePath(absolutePath, txtFile); // Pass the selected file name
                ondemandview.getChildren().setAll(newContent);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("txtFile"+txtFile);
        }

    }

    public void addFileRecords(List<String> fileNames) {
        for (String fileName : fileNames) {
            System.out.println(fileName);
            fileRecords.add(new FileRecord(fileName));
        }
    }

    @FXML
    public void previewText(ActionEvent actionEvent) {

        String selectedFileName = (String) ondemandlistView.getSelectionModel().getSelectedItem();
        System.out.println(" preview "+selectedFileName);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("preview.fxml"));
        try {
            Parent newContent = loader.load();
//            PreviewController previewController = loader.getController();
//            previewController.setFilePath(selectedFileName); // Pass the selected file name
            ondemandview.getChildren().setAll(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
