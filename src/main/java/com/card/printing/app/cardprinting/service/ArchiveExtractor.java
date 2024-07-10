/*
package com.card.printing.app.cardprinting.service;

import com.card.printing.app.cardprinting.common.EncryptData;

import java.io.*;
import java.nio.file.*;

public class ArchiveExtractor {


    EncryptData encryptData = new EncryptData();

    public void extract(String source , String output ){
            File file = new File(source);
            if(file.isFile()){
                String fileName = file.getName().toLowerCase();
                if(fileName.endsWith(".zip")){
                    extractZip(file, String.valueOf(Paths.get(output, "unzip")));
                    moveZiporRarFile(file , String.valueOf(Paths.get(output , "extracted_files")));
                }else if(fileName.endsWith(".rar")){
                    extractRar(file, String.valueOf(Paths.get(output, "unrar")));
                    moveZiporRarFile(file , output+"extracted_files");
                }else if(fileName.endsWith(".txt")){
                    decryptPCN(file);
                }
            }

        */
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
        }*//*

    }

   public void moveZiporRarFile(File file , String outputDir){
        System.out.println("File "+file.toString());
        Path sourcePath = file.toPath();
        Path movingPath = new File(outputDir , file.getName()).toPath();
        try{
            if(!Files.exists(movingPath)){
                Files.createDirectories(movingPath);
            }
            Files.move(sourcePath , movingPath , StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

    private void extractZip(File file, String outputDir) {
        try {
            String outputPath = Paths.get(outputDir , file.getName().replace(".zip","")).toString();
            File outputFile = new File(outputPath);
            outputFile.mkdirs();
            ZipFile zipFile = new ZipFile(file);
            zipFile.extractAll(outputPath);
            processFiles(new File(outputDir));
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

    private void processFiles(File outputFolder) {
        if (outputFolder.isDirectory()) {
            File[] files = outputFolder.listFiles();
            if (files != null) {
                StringBuilder builder = new StringBuilder();
                for (File file : files) {
                    if (file.isDirectory()) {
                        processFiles(file);
                    } else if (file.getName().endsWith(".txt")) {
                        System.out.println("Processing file: " + file.getAbsolutePath());
                        decryptPCN(file);
                    }
                }
            }
        }
    }


    private void decryptPCN(File file){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            String[] diltSimpleAfter = line.split("~");
            if(diltSimpleAfter.length > 1) {
                String afterDelimiter = diltSimpleAfter[0].trim();
                String decrpt = encryptData.AESDecrypt(afterDelimiter);
                System.out.println(decrpt);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    */
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
    }*//*


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
*/
