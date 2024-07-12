package com.card.printing.app.cardprinting;

import com.card.printing.app.cardprinting.dto.PathDto;
import com.card.printing.app.cardprinting.dto.ResidentDetails;
import com.card.printing.app.cardprinting.service.ArchiveExtractor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

public class PreviewController {

    private static final Logger log = LoggerFactory.getLogger(PreviewController.class);
    public ImageView colorphoto;
    public Label lastname;
    public Label firstname;
    public Label middlename;
    public Label dob;
    public Label birthprovince;

    public Label id;
    public Label maritalstatus;
    public Label bloodtype;
    public Label sex;
    public Label creationDate;
    public ImageView qrcode;
    public Label id1;
    public ImageView bwphoto;
    @FXML
    private AnchorPane preview;

    @FXML
    private Label file;


    public void setFilePath(String selectedFileName) {
        String fileNameWithoutExtension = selectedFileName.substring(0, selectedFileName.lastIndexOf('.'));
        ArchiveExtractor archiveExtractor = new ArchiveExtractor();
        List<String> outputPath = archiveExtractor.getOutputPath();
        System.out.println("outUI" + outputPath);
        System.out.println("outUI" + outputPath.get(0));
//        File file = new File("E:\\output\\unzip\\naveen", selectedFileName);
        File file = new File(PathDto.path, selectedFileName);
        System.out.println("Absolute Path " + file.getAbsolutePath());
        System.out.println("filedecrypt" + file);
//        file.setText(selectedFileName);
//        archiveExtractor.decryptPCN("E:\\output\\unzip\\12358\\12358\\12358.txt");
        ResidentDetails res = archiveExtractor.decryptTextFile(file);
        System.out.println("First Name " + res.getFirstname());
        lastname.setText(res.getLastname());
        firstname.setText(res.getFirstname());
        middlename.setText(res.getMiddlename());
        dob.setText(res.getBirthdate());
        InputStream is=new ByteArrayInputStream(res.getImg());
        Image imgae=new Image(is);
        System.out.println(res);
        colorphoto.setImage(imgae);
//        id.setText(res.getId());


        creationDate.setText(res.getCreationDate());
        sex.setText(res.getSex());
        bloodtype.setText(res.getBloodtype());
        maritalstatus.setText(res.getMaritalstatus());
        birthprovince.setText(res.getBirthprovince());
        InputStream is1=new ByteArrayInputStream(res.getQrImg());
        Image imgae1=new Image(is1);
        qrcode.setImage(imgae1);
        id1.setText(res.getId());
    }
}
