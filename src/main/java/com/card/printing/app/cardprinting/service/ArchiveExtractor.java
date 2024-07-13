package com.card.printing.app.cardprinting.service;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.*;
import java.util.*;


import com.card.printing.app.cardprinting.common.EncryptData;
import com.card.printing.app.cardprinting.common.InitConstants;
import com.card.printing.app.cardprinting.dto.PathDto;
import com.card.printing.app.cardprinting.dto.ResidentDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import lombok.Getter;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchiveExtractor {


    EncryptData encryptData = new EncryptData();

    @Getter
    List<String> txtFile=new ArrayList<>();

    Logger log = LoggerFactory.getLogger(ArchiveExtractor.class);

    @Getter
    String OutputPath;

    public void extract(String source , String output ){
            String path = null;
            File file = new File(source);
            if(file.isFile()){
                String fileName = file.getName().toLowerCase();
                if(fileName.endsWith(".zip")){
                    String isTxtFile = fileName.replaceFirst("\\.zip$",".txt");
                    File txtFile = new File(file.getParent() , isTxtFile);
                    if (txtFile.exists() && txtFile.isFile()){
                        System.out.println(txtFile.getName());
                    }
                    extractZip(file, String.valueOf(Paths.get(output, InitConstants.unzip)) , txtFile);
//                    moveZiporRarFile(file , String.valueOf(Paths.get(output , InitConstants.extractedZip)));
                }else if(fileName.endsWith(".rar")){
                    extractRar(file, String.valueOf(Paths.get(output, InitConstants.unrar)));
//                    moveZiporRarFile(file , output+InitConstants.extractedZip);
                }else if(fileName.endsWith(".txt")){
                    processFiles(file);
                }
            }
    }

   public void moveZiporRarFile(File file , String outputDir){
        System.out.println("File " + file.toString());
        Path sourcePath = file.toPath();
        Path movingPath = new File(outputDir , file.getName()).toPath();
        try{
            if(!Files.exists(movingPath) && !(file.getName().endsWith(".txt"))){
                Files.createDirectories(movingPath);
            }
            Files.move(sourcePath , movingPath , StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

    private void extractZip(File file, String outputDir , File txtFilePath) {

        String outputPath = Paths.get(outputDir , file.getName().replace(".zip","")).toString();
        try {
            System.out.println("outputPath"+outputPath);
            File outputFile = new File(outputPath);
            outputFile.mkdirs();
//            moveZiporRarFile(txtFilePath , outputPath);
            ZipFile zipFile = new ZipFile(file);
            zipFile.extractAll(outputPath);
            PathDto.path=outputPath;
            File n = new File(outputPath);
            File[] fille = n.listFiles();
            for(File u :fille ){
                txtFile.add(u.getName());
                System.out.println( u.toString());
            }
//            processFiles(new File(outputPath));
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

    public void processFiles(File outputFolder) {

        File text = new File(outputFolder.getAbsolutePath());

        Path path = Paths.get(text.getAbsolutePath());
        Path txtPath = path.getParent();
        System.out.println("Text Path : "+txtPath);
        String tx = String.valueOf(new File(String.valueOf(txtPath), text.getName() + ".txt"));
        System.out.println("File Path "+ tx);
        List<String> pcns = readPcns(new File(String.valueOf(txtPath), outputFolder.getName() + ".txt"));
            File[] files = outputFolder.listFiles();
            StringBuilder builder = new StringBuilder();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        processFiles(file);
                    } else if (file.getName().endsWith(".txt")) {
                        String filename = file.getName().toLowerCase().replace(".txt","");

                        System.out.println("textFile"+ txtFile);
                        System.out.println("filename"+filename);

                        ResidentDetails details = decryptTextFile(file);
                        builder.append(filename).append(details.isProcessStatus() ? " - Processed " : " - Not Processed ").append("\n");
                        pcns.remove(filename);

                    }
                }
            }

            for(String remainingPcns : pcns){
                builder.append(remainingPcns).append(" - Not Found \n");
            }

            updateTextFile(builder.toString() , new File(String.valueOf(txtPath), text.getName() + ".txt" ));

//        }
    }

    private void updateTextFile(String content , File file){
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(file))){
                printWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> readPcns(File file){
        System.out.println("File Read Path : "+file.getAbsolutePath());
        System.out.println("File Read Parent : "+file.getParent());
        List<String> pcnList = new ArrayList<>();

        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine()) != null){
                line = line.trim();
                if(!line.isEmpty()){
                    pcnList.add(line);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return pcnList;
    }


    public ResidentDetails decryptTextFile(File file) {
        String decrptedContent = "";
        System.out.println("Decrypt "+file.getName());
        ObjectMapper mapper = new ObjectMapper();
        ResidentDetails details = new ResidentDetails();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null) {
                String content = line.split("~")[0];
                String img = line.split("~")[1];
                if (Objects.nonNull(content) && content.startsWith("{")) {
                    details.setProcessStatus(false);
                    return details;
                }
                byte[] imgArr = getBdb(img, "card");
                byte[] qrImg = getBdb(img, "qr");
                decrptedContent = encryptData.AESDecrypt(content);
                System.out.println("decrptedContent :" + decrptedContent);
                details = mapper.readValue(decrptedContent, ResidentDetails.class);
                details.setImg(imgArr);
                details.setQrFaceImg(qrImg);
                QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
                details.setQrImg(qrCodeGenerator.createQR(encodeImageBase64(qrImg)));
                details.setSplitedId(splitId(details.getId()));
                details.setProcessStatus(true);
            } else {
                System.out.println("File is empty");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (decrptedContent == null || decrptedContent.isEmpty()) {
            details.setProcessStatus(false);
            return details;
        }
        return details;
    }

    public String splitId(String id){
        System.out.println("Wants Split The Number : "+id);
        String newId = "";
        if(id == null || id.isEmpty()){
            return null;
        }
        StringBuilder builder  = new StringBuilder();
        for(int i=0; i<id.length(); i++){
            builder.append(id.charAt(i));
            if(((i+1) % 4 == 0) && i < id.length()-1 ){
                builder.append('-');
            }
        }
        System.out.println(builder);
        return builder.toString();
    }

    public String encodeImageBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public byte[] getBdb(String hexImage, String imageType) {
        ISO iso = new ISO();
        ImageProcessingService ImageProcessingService = new ImageProcessingService();
        try {
            return ImageProcessingService.ImageConvertor(hexImage, imageType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private byte[] toByteArray(String hexString) {
        int arrLength = hexString.length() >> 1;
        byte[] buf = new byte[arrLength];
        for (int ii = 0; ii < arrLength; ii++) {
            int index = ii << 1;
            String l_digit = hexString.substring(index, index + 2);
            buf[ii] = (byte) Integer.parseInt(l_digit, 16);
        }
        return buf;
    }


    private static void extractRar(File rarFile, String outputDir) {
        try (Archive archive = new Archive(new FileInputStream(rarFile))) {
            FileHeader fileHeader;
            while ((fileHeader = archive.nextFileHeader()) != null) {
                String filePath = outputDir + File.separator + fileHeader.getFileNameString();
                if (!fileHeader.isDirectory()) {
                    new File(filePath).getParentFile().mkdirs();
                    try (FileOutputStream fos = new FileOutputStream(filePath)) {
                        archive.extractFile(fileHeader, fos);
                    }
                }
            }
        } catch (RarException | IOException e) {
            e.printStackTrace();
            if (e.getMessage().contains("unsupportedRarArchive")) {
                System.err.println("Unsupported RAR archive: " + e.getMessage());
            } else {
                System.err.println("Error extracting RAR file: " + e.getMessage());
            }
        }
    }

    public void extractEncrypted(File input,String output,String password){
        try{
            ZipFile zipFile = new ZipFile(input);
            System.out.println("zip");
            if(zipFile.isEncrypted()){
                System.out.println("Encrypted");
                zipFile.setPassword(password.toCharArray());
            }
            zipFile.extractAll(output);
            System.out.println("Extraction Successfully");
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getTextFeild(){
        return txtFile;
    }
    public List<String> getOutputPath(){
        return Collections.singletonList(OutputPath);
    }

}
