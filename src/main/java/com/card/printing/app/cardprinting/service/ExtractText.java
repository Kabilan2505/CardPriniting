/*
package com.card.printing.app.cardprinting.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExtractText {

   */
/* public void updateStatus(String zipFilePath, String externalTxt) {

    }*//*


    Logger log = LoggerFactory.getLogger(ExtractText.class);

    public static void checkAndUpdateFile(String inputFilePath, String folderPath, String outputFilePath) throws IOException {
        StringBuilder sb = new StringBuilder();

        String[] numbers;
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line = br.readLine();
            numbers = line != null ? line.split(",") : new String[0];
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (String number : numbers) {
                String trimmedNumber = number.trim();
                boolean exists = false;

                for (File file : files) {
                    if (file.isFile()) {
                        String fileNameWithoutExtension = file.getName().toLowerCase().replace(".txt", "");

                        if (fileNameWithoutExtension.equals(trimmedNumber)) {
                            exists = true;
                            break;
                        }
                    }
                }

                String result = trimmedNumber + (exists ? " - exists " : " - Not Exists ");
                sb.append(result).append("\n");
                System.out.println("result: " + result);
            }
        }
        updateExternalFile(sb.toString(), outputFilePath);
    }

    private boolean checkFiles(String number, String folder) {
        Path filePath  = Paths.get(folder,number);
        return Files.exists(filePath);
    }

    private static void updateExternalFile(String content, String file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(content);
        bw.close();
    }

}
*/
