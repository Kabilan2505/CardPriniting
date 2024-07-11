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
//    private static final Logger logger = LoggerFactory.getLogger(ImageProcessingService.class);

    static {
        nu.pattern.OpenCV.loadLocally();
    }

    // Load OpenCV on service instantiation



    // Method to convert image byte array to resized JPEG byte array
    public  byte[] ImageConvertor(String hexString , String imageType) {
        try {
            // Convert byte array to hexadecimal string representation
//            String hexString = asHexString(face);
//            OpenCV.loadShared();


            // Remove initial bytes (assuming metadata) and parse as MatOfByte
            StringBuffer faceBuffer = new StringBuffer(hexString);
//            MatOfByte faceMatOfBytesArr = new MatOfByte(DatatypeConverter.parseHexBinary(faceBuffer.delete(0, 136).toString()));
            MatOfByte faceMatOfBytesArr = new MatOfByte(DatatypeConverter.parseHexBinary(hexString));

            // Decode image to Mat
            Mat faceMat = Imgcodecs.imdecode(faceMatOfBytesArr, Imgcodecs.IMREAD_ANYCOLOR);

            // Resize image
            Mat resizedMat = new Mat();
            int height = 150;
            int width = (faceMat.width() * height) / faceMat.height();
            Imgproc.resize(faceMat, resizedMat, new Size(width, height));

            // Encode resized image to JPEG format
            MatOfByte jpegMatOfByte = new MatOfByte();
            Imgcodecs.imencode(".jpg", resizedMat, jpegMatOfByte);

            // Compress JPEG with increasing quality until size constraint is met
            if(imageType.equals("qr")) {
                return compressQR(jpegMatOfByte.toArray(), resizedMat);
            }else{
                return jpegMatOfByte.toArray();
            }
//            return compressedImage;
        } catch (Exception e) {
//            logger.error("ImageConvertor: error while converting image", e);
            throw new RuntimeException("Error while converting image: " + e.getMessage(), e);
        }
    }

    // Method to convert byte array to hexadecimal string
    private String asHexString(byte[] buf) {
        StringBuilder strbuf = new StringBuilder(buf.length * 2);
        for (byte b : buf) {
            strbuf.append(String.format("%02x", b & 0xff));
        }
        return strbuf.toString();
    }

    // Method to compress image with increasing JPEG quality
    private byte[] compressQR(byte[] image,Mat resizedImage) {
        int quality = 5;
        MatOfByte matOfByte = new MatOfByte();
//        && quality <= 100
        System.out.println("imag :"+image.length);
        byte[] imageByte = new byte[0];
        while (imageByte.length <= 1300 ) {
            MatOfInt mat = new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, quality);
//            Imgcodecs.imencode(".jpg", new Mat(image.length, 1, CvType.CV_8UC1), matOfByte, mat);
            Imgcodecs.imencode(".jpg", resizedImage, matOfByte,mat);
//            Imgcodecs.imencode(".jpg", new Mat(image.length, 1, CvType.CV_8UC1), matOfByte, mat);
            imageByte = matOfByte.toArray();
            System.out.println("imag :"+imageByte.length);
            quality += 5;
//            quality += 1;

        }
        return imageByte;
    }
}
