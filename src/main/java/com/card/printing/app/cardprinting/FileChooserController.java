package com.card.printing.app.cardprinting;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class FileChooserController {


    @FXML
    public Button btn1, btn2;
    @FXML
    public ListView listview;

    /**D:\CardPrinting
     * select single pdf file
     */
    public void Button1Action(ActionEvent event) {
        FileChooser fc = new FileChooser();

        // Set the initial directory to a specific folder
        File initialDirectory = new File("D:\\CardPrinting"); // Change to your desired folder
        if (initialDirectory.exists() && initialDirectory.isDirectory()) {
            fc.setInitialDirectory(initialDirectory);
        }

        // Set file extension filter for zip files
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Zip Files", "*.zip"));

        // Show the open dialog
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            listview.getItems().add(selectedFile.getAbsolutePath());
        } else {
            System.out.println("File is not valid!");
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
