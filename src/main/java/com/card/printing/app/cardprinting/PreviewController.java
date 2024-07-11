package com.card.printing.app.cardprinting;

import com.card.printing.app.cardprinting.service.ArchiveExtractor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class PreviewController {

    @FXML
    private AnchorPane preview;

    @FXML
    private Label file;


    public void setFilePath(String selectedFileName){
        String fileNameWithoutExtension = selectedFileName.substring(0, selectedFileName.lastIndexOf('.'));
      File file = new File("E:\\output\\unzip\\"+fileNameWithoutExtension+"\\"+fileNameWithoutExtension+"\\"+selectedFileName);
//        file.setText(selectedFileName);
        ArchiveExtractor archiveExtractor = new ArchiveExtractor();
//        archiveExtractor.decryptPCN("E:\\output\\unzip\\12358\\12358\\12358.txt");
        archiveExtractor.decryptPCN(file);

        }
}
