package com.card.printing.app.cardprinting;


import com.card.printing.app.cardprinting.service.ArchiveExtractor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class FileListController{

    @FXML
    private ListView<String> listView;

    @FXML
    private VBox batchView;
    @FXML
    private HBox hBoxView;

    List<String> txtFile;
    @FXML
    private TableView<FileRecord> tableView;

    @FXML
    public TableColumn<FileRecord, String> fileNameColumn;

    String outputPath="E:/output";
    String folderPath = "E:\\CardPrinting\\";

    private final ObservableList<FileRecord> fileRecords = FXCollections.observableArrayList();

    public void initialize() {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    listView.getItems().add(file.getName());
                    listView.setStyle("-fx-font-size: 20px");
                }
            }
        } else {
            System.err.println("Failed to read files from the folder: " + folderPath);
        }
    }

    @FXML
    public void handleUnzippedFile(ActionEvent actionEvent) throws IOException {

        String selectedPath = listView.getSelectionModel().getSelectedItem();
        String absolutePath = folderPath + selectedPath;
        System.out.println("selectedPath naveen"+absolutePath);
        if (absolutePath != null) {
            File selectedFile = new File(absolutePath);
            if (!selectedFile.exists()) {
//                showAlert("File Error", "Selected file does not exist.");
                return;
            }
            System.out.println("outputPath"+outputPath);

            ArchiveExtractor archive = new ArchiveExtractor();
            archive.extract(absolutePath, outputPath);

            txtFile = archive.getTextFeild();
            addFileRecords(txtFile);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("extracted-file.fxml"));
            try {
                Parent newContent = loader.load();
                ExtractedFileController extractedFileController = loader.getController();
                extractedFileController.setFilePath(absolutePath, txtFile);
                batchView.getChildren().setAll(newContent);
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
}