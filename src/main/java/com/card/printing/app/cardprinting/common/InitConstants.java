package com.card.printing.app.cardprinting.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.*;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


@Configuration
public class InitConstants {

    public static String resourcePath;

    public static String outputPath;

    public static String processed = "processed";

    public static String completed = "completed";

    public static String unrar = "unrar";

    public static String unzip  = "unzip" ;

    public static String extractedZip = "extracted_files";

    Logger log = LoggerFactory.getLogger(InitConstants.class);

    @Bean
    public void readConfig() {
        ResourceBundle rb = null;

        try{
            String filepath = new FileSystemResource("").getFile().getAbsolutePath();
            InputStream is = new FileInputStream(new File(filepath + "/card_config.txt"));
            rb = new PropertyResourceBundle(is);

            resourcePath = rb.getString("resourcePath");
            outputPath = rb.getString("outputPath");

        } catch (MissingResourceException | FileNotFoundException e) {
            log.error("config.txt -> " + e.getMessage());
            System.exit(0);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
