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
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class FileListController{


    @FXML
    private ListView<String> listView;

    @FXML
    private VBox batchView;

    List<String> txtFile;

    @FXML
    private TableView<FileRecord> tableView;

    @FXML
    public TableColumn<FileRecord, String> fileNameColumn;

    String outputPath="E:/output";

    private final ObservableList<FileRecord> fileRecords = FXCollections.observableArrayList();

    public void initialize() {
        // Specify the folder path
        // Specify the folder path
        String folderPath = "E:\\CardPrinting";


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
//        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("fileName"));
//        tableView.setItems(fileRecords);
    }

    @FXML
    public void handleUnzippedFile(ActionEvent actionEvent) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("extracted-file.fxml"));
//        Parent newContent = loader.load();
//        batchView.getChildren().setAll(newContent);

        String selectedPath = listView.getSelectionModel().getSelectedItem();
        System.out.println("selectedPath"+selectedPath);
        if (selectedPath != null) {
            File selectedFile = new File(selectedPath);
            if (!selectedFile.exists()) {
//                showAlert("File Error", "Selected file does not exist.");
                return;
            }
            System.out.println("outputPath"+outputPath);
            // Prepare request object
            ArchiveExtractor archive = new ArchiveExtractor();
            archive.extract(selectedPath, outputPath);// Adjust the output path as needed
            System.out.println("selectedPath"+selectedPath);
            txtFile =archive.getTextFeild();
            addFileRecords(txtFile);

//            extractedFileController.initialize();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("extracted-file.fxml"));
            try {
                Parent newContent = loader.load();
                ExtractedFileController extractedFileController = loader.getController();
                extractedFileController.setFilePath(selectedPath, txtFile); // Pass the selected file name
                batchView.getChildren().setAll(newContent);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("txtFile"+txtFile);
        }

    }
//    public List<String> textFiles(){
//        return txtFile;
//    }
    public void addFileRecords(List<String> fileNames) {
        for (String fileName : fileNames) {
            System.out.println(fileName);
            fileRecords.add(new FileRecord(fileName));
        }


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