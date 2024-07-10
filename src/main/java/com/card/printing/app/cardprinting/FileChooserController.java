package com.card.printing.app.cardprinting;

import com.card.printing.app.cardprinting.dto.Request;
import com.card.printing.app.cardprinting.service.ArchiveExtractor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class FileChooserController {

 String sourcePath="http://localhost:8080/extract";
    @FXML
    public Button btn1, btn2;
    @FXML
    public ListView listview;

//    @Autowired
//    ArchiveExtractor archive;

    /**D:\CardPrinting
     * select single pdf file
     */
    public void Button1Action(ActionEvent event) {
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
            }
    }
    @FXML
    private void handleZip(ActionEvent event) {
        String selectedPath = (String) listview.getSelectionModel().getSelectedItem();
        if (selectedPath != null) {
            File selectedFile = new File(selectedPath);
            if (!selectedFile.exists()) {
//                showAlert("File Error", "Selected file does not exist.");
                return;
            }

            // Prepare request object
            ArchiveExtractor archive = new ArchiveExtractor();
            archive.extract(selectedPath, "D:/output");// Adjust the output path as needed


//
//            // Send HTTP POST request to API
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeader headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            HttpEntity<Request> requestBody = new HttpEntity<>(request, headers);
//
//            try {
//                ResponseEntity<Void> response = restTemplate.postForEntity(API_URL, requestBody, Void.class);
//                if (response.getStatusCode().is2xxSuccessful()) {
//                    showAlert("Success", "Extraction initiated successfully.");
//                } else {
//                    showAlert("Error", "Failed to initiate extraction.");
//                }
//            } catch (Exception e) {
//                showAlert("Error", "Exception occurred: " + e.getMessage());
//                e.printStackTrace();
//            }
//        } else {
//            showAlert("Selection Error", "Please select a file to extract.");
//        }
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
