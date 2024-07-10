package com.card.printing.app.cardprinting.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Service
public class FetchPCN {

    private String printerIp = "";

    private int port = 9092;

    public void zebraPri(){

    }

    Logger log = LoggerFactory.getLogger(FetchPCN.class);

    public void extractZipOrRar(String zipFilePath , String extractingFilePath){
        Path sourceZiporRar = Paths.get(zipFilePath);
        Path outputFolder  = Paths.get(extractingFilePath);

        if(Files.notExists(outputFolder)){
            try{
                Files.createDirectories(outputFolder);
            }catch(IOException e){
                e.printStackTrace();
            }
        }


        try{
            extractAllZipRarFiles(sourceZiporRar,outputFolder);
            log.info("Extracted All Rar or Zip Files SuccessFully");
        }catch(Exception e){
            e.printStackTrace();
        }

    }


   public static void extractAllZipRarFiles(Path sourceZiporRar , Path outputFolder){
        try{
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(sourceZiporRar , "*.{zip,rar}");
            for(Path path : directoryStream){
                Path outDir = outputFolder.resolve(path.getFileName().toString().replaceFirst("[.][^.]+$",""));
                Path tempDir = Files.createTempDirectory("extract_");
                if(Files.notExists(outDir)){
                    Files.createDirectories(outDir);
                }if(path.endsWith(".zip")){
                    UnRarAndZip.unZipFolder(path , outDir);
                } else if (path.endsWith(".rar")) {
                    UnRarAndZip.unrarFolder(path , outDir);
                }

                moveContentsToNewDir(tempDir, outputFolder);
                deleteDirectory(tempDir.toFile());

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
   }

    private static void moveContentsToNewDir(Path sourceDir, Path destDir) throws IOException {
        if (Files.notExists(destDir)) {
            Files.createDirectories(destDir);
        }
        Files.walk(sourceDir)
                .forEach(source -> {
                    try {
                        Path destination = destDir.resolve(sourceDir.relativize(source));
                        if (Files.isDirectory(source)) {
                            Files.createDirectories(destination);
                        } else {
                            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
                        }
                    } catch (IOException e) {
                        System.out.println("Failed to move file: " + source);
                    }
                });
    }

    private static void deleteDirectory(File dir) throws IOException {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        dir.delete();
    }



    /*public String printCards(){
        DiscoveredPrinter  discoveredPrinter = discoverPrinter();
        return null;
    }*/
}
