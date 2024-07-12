package com.card.printing.app.cardprinting;

import com.card.printing.app.cardprinting.dto.PathDto;
import com.card.printing.app.cardprinting.dto.ResidentDetails;
import com.card.printing.app.cardprinting.service.ArchiveExtractor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

public class PreviewController {


//    public void setFilePath(String selectedFileName) {
//        String fileNameWithoutExtension = selectedFileName.substring(0, selectedFileName.lastIndexOf('.'));
//        ArchiveExtractor archiveExtractor = new ArchiveExtractor();
//        List<String> outputPath = archiveExtractor.getOutputPath();
//        System.out.println("outUI" + outputPath);
//        System.out.println("outUI" + outputPath.get(0));
////        File file = new File("E:\\output\\unzip\\naveen", selectedFileName);
//        File file = new File(PathDto.path, selectedFileName);
//        System.out.println("Absolute Path " + file.getAbsolutePath());
//        System.out.println("filedecrypt" + file);
////        file.setText(selectedFileName);
////        archiveExtractor.decryptPCN("E:\\output\\unzip\\12358\\12358\\12358.txt");
//        ResidentDetails res = archiveExtractor.decryptTextFile(file);
//        System.out.println("First Name " + res.getFirstname());
//        lastname.setText(res.getLastname());
//        firstname.setText(res.getFirstname());
//        middlename.setText(res.getMiddlename());
//        dob.setText(res.getBirthdate());
//        InputStream is=new ByteArrayInputStream(res.getImg());
//        Image imgae=new Image(is);
//        System.out.println(res);
//        colorphoto.setImage(imgae);
//        Image blackNwhite=convertToGrayscale(imgae);
//        bwphoto.setImage(blackNwhite);
//        id.setText(res.getId());
//
//
//
//        creationDate.setText(res.getCreationDate());
//        sex.setText(res.getSex());
//        bloodtype.setText(res.getBloodtype());
//        maritalstatus.setText(res.getMaritalstatus());
//        birthprovince.setText(res.getBirthprovince());
//        InputStream is1=new ByteArrayInputStream(res.getQrImg());
//        Image imgae1=new Image(is1);
//        qrcode.setImage(imgae1);
//        id1.setText(res.getId());
//    }
//
//    public static Image convertToGrayscale(Image image) {
//        ImageView imageView = new ImageView(image);
//
//        // Create a ColorAdjust effect
//        ColorAdjust colorAdjust = new ColorAdjust();
//        colorAdjust.setSaturation(-1); // Set saturation to -1 to remove color (grayscale effect)
//
//        // Apply the effect to the ImageView
//        imageView.setEffect(colorAdjust);
//
//        // Create a WritableImage to hold the processed image
//        WritableImage grayscaleImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
//
//        // Snapshot the ImageView with the effect applied to the WritableImage
//        imageView.snapshot(null, grayscaleImage);
//
//        return grayscaleImage;
//    }
}
