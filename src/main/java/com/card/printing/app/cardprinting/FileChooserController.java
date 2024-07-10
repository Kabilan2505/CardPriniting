package com.card.printing.app.cardprinting;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileChooserController {


    @FXML
    public Button btn1, btn2;
    @FXML
    public ListView listview;

    /**D:\CardPrinting
     * select single pdf file
     */
    public void Button1Action(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Folder");

        // Optional: Set the initial directory
        File initialDirectory = new File("E:\\CardPrinting"); // Change to your desired folder
        if (initialDirectory.exists() && initialDirectory.isDirectory()) {
            directoryChooser.setInitialDirectory(initialDirectory);
        }

        // Show the open dialog to select a folder
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null && selectedDirectory.isDirectory()) {
            // Filter for zip files in the selected directory
            List<File> zipFiles = Arrays.stream(selectedDirectory.listFiles())
                    .filter(file -> file.isFile() && file.getName().endsWith(".zip"))
                    .collect(Collectors.toList());

            if (!zipFiles.isEmpty()) {
                zipFiles.forEach(file -> listview.getItems().add(file.getAbsolutePath()));
            } else {
                System.out.println("No zip files found in the selected directory!");
            }
        } else {
            System.out.println("Selected directory is not valid!");
        }
    }

    /**
     * Select multiple files
     */
    public void Button2Action(ActionEvent event) {
        FileChooser fc = new FileChooser();

        List<File> selectedFiles = fc.showOpenMultipleDialog(null);

        if(selectedFiles != null) {
            for(int i=0; i<selectedFiles.size(); i++) {
                listview.getItems().add(selectedFiles.get(i).getAbsolutePath());
            }
        }else {
            System.out.println("File is not valid!");
        }
    }
//
}
