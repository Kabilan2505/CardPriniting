package com.card.printing.app.cardprinting.service;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;


import com.card.printing.app.cardprinting.common.EncryptData;
import com.card.printing.app.cardprinting.common.InitConstants;
import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchiveExtractor {


    EncryptData encryptData = new EncryptData();

    Logger log = LoggerFactory.getLogger(ArchiveExtractor.class);

    public void extract(String source , String output ){
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
                    decryptPCN(file);
                }
            }

        /*if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName().toLowerCase();
                    if (fileName.endsWith(".zip")) {
                        extractZip(file, String.valueOf(Paths.get(outputDir, InitConstants.unzip)));
                        moveZiporRarFile(file , String.valueOf(Paths.get(outputDir , InitConstants.extractedZip)));
                    } else if (fileName.endsWith(".rar")) {
                        extractRar(file, outputDir+InitConstants.unzip);
                        moveZiporRarFile(file , outputDir+InitConstants.extractedZip);
                    }
                }
            }
        }*/
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
        try {
            String outputPath = Paths.get(outputDir , file.getName().replace(".zip","")).toString();
            File outputFile = new File(outputPath);
            outputFile.mkdirs();
//            moveZiporRarFile(txtFilePath , outputPath);
            ZipFile zipFile = new ZipFile(file);
            zipFile.extractAll(outputPath);
            processFiles(new File(outputPath , file.getName().replaceFirst("\\.zip$","")));
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

    private void processFiles(File outputFolder) {
        System.out.println(outputFolder + " ----- ");
//        if (outputFolder.isDirectory()) {
            File text = new File(outputFolder.getAbsolutePath());
            File parentFile = text.getParentFile();
//            System.out.println("text File " + text.getName());
//
            List<String> pcns = readPcns(new File(parentFile , text.getName() + ".txt" ));

            File[] files = outputFolder.listFiles();
            StringBuilder builder = new StringBuilder();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        processFiles(file);
                    } else if (file.getName().endsWith(".txt")) {

                        String filename = file.getName().toLowerCase().replace(".txt","");

//                        boolean exists = pcns.contains(filename);

                        System.out.println(filename);

                        boolean isDecrypted =  decryptPCN(file);

//                        builder.append(filename).append(exists ? " - Exists " : " - Not Exists ").append("\n");

                        builder.append(filename).append(isDecrypted ? " - Processed " : " - Not Processed ").append("\n");

                        pcns.remove(filename);

                    }
                }
            }

            for(String remainingPcns : pcns){
                builder.append(remainingPcns).append(" - Not Found \n");
            }

            updateTextFile(builder.toString() , new File(parentFile , text.getName() + ".txt" ));

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


    private boolean decryptPCN(File file){
        System.out.println(file.getName());
        String decrpt = null;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            String[] diltSimpleAfter = line.split("~");
            if(diltSimpleAfter.length > 0) {
                String afterDelimiter = diltSimpleAfter[0].trim();
                decrpt = encryptData.AESDecrypt(afterDelimiter);
                System.out.println(decrpt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(decrpt.isEmpty() || decrpt == null){
            return false;
        }

        return true;
    }

    public void processTextFile(File file , String content){

    }

    /*private static void extractZip(File zipFile, String outputDir) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String filePath = outputDir + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    new File(filePath).getParentFile().mkdirs();

                    try (FileOutputStream fos = new FileOutputStream(filePath)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zis.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

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


}
