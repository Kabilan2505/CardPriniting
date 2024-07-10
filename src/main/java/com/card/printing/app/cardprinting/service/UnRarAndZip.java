package com.card.printing.app.cardprinting.service;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnRarAndZip {

    public static void unZipFolder(Path input , Path output){
        try{
            ZipInputStream zip = new ZipInputStream(new FileInputStream(input.getFileName().toString()));
            ZipEntry zipEntry = zip.getNextEntry();
            while(zipEntry != null){
                Path newFilePath = output.resolve(zipEntry.getName());
                System.out.println("Extracting: " + newFilePath);
                if(zipEntry.isDirectory()){
                    Files.createDirectories(newFilePath);
                }else{
                    Files.createDirectories(newFilePath.getParent());
                    try(BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(newFilePath.toFile()))){
                        byte[] buffer = new byte[1024];
                        int length;
                        while((length = zip.read(buffer)) != -1){
                            out.write(buffer, 0, length);
                        }
                    }
                }
                zipEntry = zip.getNextEntry();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void unrarFolder(Path rarFilePath, Path destDir) throws IOException {
        try (Archive archive = new Archive(new FileInputStream(rarFilePath.toFile()))) {
            if (archive == null) {
                throw new IOException("Failed to open archive: " + rarFilePath);
            }
            FileHeader fileHeader = archive.nextFileHeader();
            while (fileHeader != null) {
                Path newFilePath = destDir.resolve(fileHeader.getFileNameString().trim());
                System.out.println("Extracting: " + newFilePath);
                if (fileHeader.isDirectory()) {
                    Files.createDirectories(newFilePath);
                } else {
                    Files.createDirectories(newFilePath.getParent());
                    try (OutputStream os = Files.newOutputStream(newFilePath)) {
                        archive.extractFile(fileHeader, os);
                    } catch (Exception e) {
                        System.err.println("Failed to extract file: " + newFilePath);
                        e.printStackTrace();
                        if (e.getMessage().contains("unsupportedRarArchive")) {
                            System.err.println("Unsupported RAR archive: " + e.getMessage());
                        } else {
                            System.err.println("Error extracting RAR file: " + e.getMessage());
                        }
                    }
                }
                fileHeader = archive.nextFileHeader();
            }
        } catch (Exception e) {
            System.err.println("Failed to process archive: " + rarFilePath);
            e.printStackTrace();
        }
    }
}
