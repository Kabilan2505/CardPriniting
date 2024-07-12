package com.card.printing.app.cardprinting.service;

import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.xml.bind.DatatypeConverter;
import java.util.Base64;

//@Service
public class ImageProcessingService {
    static {
        nu.pattern.OpenCV.loadLocally();
    }
    public  byte[] ImageConvertor(String hexString , String imageType) {
        try {

            StringBuffer faceBuffer = new StringBuffer(hexString);
            MatOfByte faceMatOfBytesArr = new MatOfByte(DatatypeConverter.parseHexBinary(hexString));

            // Decode image to Mat
            Mat faceMat = Imgcodecs.imdecode(faceMatOfBytesArr, Imgcodecs.IMREAD_ANYCOLOR);

            // Resize image
            Mat resizedMat = new Mat();
            int height = 150;
            int width = (faceMat.width() * height) / faceMat.height();
            Imgproc.resize(faceMat, resizedMat, new Size(width, height));

            MatOfByte jpegMatOfByte = new MatOfByte();
            Imgcodecs.imencode(".jpg", resizedMat, jpegMatOfByte);
            if(imageType.equals("qr")) {
                return compressQR(jpegMatOfByte.toArray(), resizedMat);
            }else{
                return jpegMatOfByte.toArray();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while converting image: " + e.getMessage(), e);
        }
    }

    private String asHexString(byte[] buf) {
        StringBuilder strbuf = new StringBuilder(buf.length * 2);
        for (byte b : buf) {
            strbuf.append(String.format("%02x", b & 0xff));
        }
        return strbuf.toString();
    }

    private byte[] compressQR(byte[] image,Mat resizedImage) {
        int quality = 5;
        MatOfByte matOfByte = new MatOfByte();
//        && quality <= 100
        System.out.println("imag :"+image.length);
        byte[] imageByte = new byte[0];
        while (imageByte.length <= 1300 ) {
            MatOfInt mat = new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, quality);
            Imgcodecs.imencode(".jpg", resizedImage, matOfByte,mat);
            imageByte = matOfByte.toArray();
            System.out.println("imag :"+imageByte.length);
            quality += 5;

        }
        return imageByte;
    }
}
