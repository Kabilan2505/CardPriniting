/*
package com.card.printing.app.cardprinting;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OnDemandExtractController {


    public VBox onedemandtextview;
    public ListView<String> ondemandtextlistview;

    public void setFilePath(String selectedFileName, List<String> txtFiles) {
        System.out.println("textFiles"+txtFiles);
        if (txtFiles != null) {
            // Clear previous items
            ondemandtextlistview.getItems().clear();
            // Add file names to the ListView
            for ( String file : txtFiles) {
                ondemandtextlistview.getItems().add(file);
                ondemandtextlistview.setStyle("-fx-font-size: 20px");

            }
        } else {
//            System.err.println("Failed to read files from the folder: " + folderPath);
        }
    }
    @FXML
    public void previewText1(ActionEvent actionEvent) {

        String selectedFileName = (String) ondemandtextlistview.getSelectionModel().getSelectedItem();
        System.out.println(" preview "+selectedFileName);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("preview.fxml"));
        try {
            Parent newContent = loader.load();
            PreviewController previewController = loader.getController();
//            previewController.setFilePath(selectedFileName); // Pass the selected file name
            onedemandtextview.getChildren().setAll(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/

//----------------------------------------------------------------------------------------------------------->
        package com.card.printing.app.cardprinting;

import com.card.printing.app.cardprinting.dto.PathDto;
import com.card.printing.app.cardprinting.dto.ResidentDetails;
import com.card.printing.app.cardprinting.service.ArchiveExtractor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OnDemandExtractController {
    @FXML
    public ListView<String> textlistview;

    @FXML
    public HBox hBox;

    public void setFilePath(String selected, List<String> txtFiles) {
        System.out.println("textFiles"+txtFiles);
        if (txtFiles != null) {
            System.out.println("txtFiles -- true "+txtFiles);
            System.out.println("txtFiles -- true -- getItem  "+textlistview.getItems());
            for (String file : txtFiles) {
                System.out.println("txtFiles -- true -- loop "+file);
                textlistview.getItems().add(file);
                textlistview.setStyle("-fx-font-size: 20px");
            }
        } else {
        }
        System.out.println("txtFiles -- false "+textlistview);
    }
    @FXML
    public void previewText(ActionEvent actionEvent) {

        String selectedFileName = textlistview.getSelectionModel().getSelectedItem();
        System.out.println(" preview "+selectedFileName);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("extracted-file.fxml"));
        setFilePathSelectedFile(selectedFileName); // Pass the selected file name
    }



    private static final Logger log = LoggerFactory.getLogger(com.card.printing.app.cardprinting.ExtractedFileController.class);
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


    public void setFilePathSelectedFile(String selectedFileName) {
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
        Image blackNwhite=convertToGrayscale(imgae);
        bwphoto.setImage(blackNwhite);
        id.setText(res.getId());



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

    public static Image convertToGrayscale(Image image) {
        ImageView imageView = new ImageView(image);

        // Create a ColorAdjust effect
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(-1); // Set saturation to -1 to remove color (grayscale effect)

        // Apply the effect to the ImageView
        imageView.setEffect(colorAdjust);

        // Create a WritableImage to hold the processed image
        WritableImage grayscaleImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());

        // Snapshot the ImageView with the effect applied to the WritableImage
        imageView.snapshot(null, grayscaleImage);

        return grayscaleImage;
    }


}
