package com.card.printing.app.cardprinting;

import com.card.printing.app.cardprinting.service.ArchiveExtractor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class PreviewController {

    private static final Logger log = LoggerFactory.getLogger(PreviewController.class);
    @FXML
    private AnchorPane preview;

    @FXML
    private Label file;


    public void setFilePath(String selectedFileName){
        String fileNameWithoutExtension = selectedFileName.substring(0, selectedFileName.lastIndexOf('.'));
        ArchiveExtractor archiveExtractor=new ArchiveExtractor();
        List<String> outputPath=archiveExtractor.getOutputPath();
        System.out.println("outUI"+outputPath);
        System.out.println("outUI"+ outputPath.get(0));
      File file = new File("D:\\output\\unzip\\batch_2\\batch_2\\"+selectedFileName);
        System.out.println("filedecrypt"+file);
//        file.setText(selectedFileName);
//        archiveExtractor.decryptPCN("E:\\output\\unzip\\12358\\12358\\12358.txt");
        archiveExtractor.decryptPCN(file);

        }
}
