package com.card.printing.app.cardprinting.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

@Service
public class QRCodeGenerator {


    /*public byte[] createQR(String img){
            try{
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
                hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

                BitMatrix bitMatrix = qrCodeWriter.encode(img, BarcodeFormat.QR_CODE,150,200,hints);

//                MatrixToImageWriter.writeToPath(bitMatrix,"png", Paths.get(qrFile));

//                BitMatrix matrix = new MultiFormatWriter().encode(new String("Naveen Bot".getBytes("UTF-8"), "UTF-8"), BarcodeFormat.QR_CODE, 400, 400);
//                MatrixToImageWriter.writeToFile(matrix, qrFile.substring(qrFile.lastIndexOf('.') + 1), new File(qrFile));

//                BitMatrix bitMatrix = qrCodeWriter.encode(img, BarcodeFormat.QR_CODE, 700, 700, hints);
                BufferedImage qrImage = new BufferedImage(bitMatrix.getWidth(), bitMatrix.getHeight(), BufferedImage.TYPE_INT_RGB);
                qrImage.createGraphics();

                Graphics2D graphics = qrImage.createGraphics();
                graphics.setColor(Color.WHITE);
                graphics.fillRect(0, 0, qrImage.getWidth(), qrImage.getHeight());
                graphics.setColor(Color.BLACK);

                for (int x = 0; x < bitMatrix.getWidth(); x++) {
                    for (int y = 0; y < bitMatrix.getHeight(); y++) {
                        if (bitMatrix.get(x, y)) {
                            graphics.fillRect(x, y, 1, 1);
                        }
                    }
                }



                ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
                ImageIO.write(qrImage,"PNG",byteArrayInputStream);
                MatrixToImageWriter.writeToStream(bitMatrix,"png",byteArrayInputStream);

                System.out.println("Qr Code generated Successfully");
                return byteArrayInputStream.toByteArray();
            } catch (WriterException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }*/



    public byte[] createQR(String img) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); // Change to L, M, Q, H based on needs

            // Increase size for better scanning
            int qrWidth = 300;
            int qrHeight = 300;

            BitMatrix bitMatrix = qrCodeWriter.encode(img, BarcodeFormat.QR_CODE, qrWidth, qrHeight, hints);

            // Convert BitMatrix to BufferedImage
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            // Remove unnecessary white background
            int top = 0;
            int bottom = qrImage.getHeight() - 1;
            int left = 0;
            int right = qrImage.getWidth() - 1;

            // Find the top boundary
            outerTopLoop:
            for (int y = 0; y < qrImage.getHeight(); y++) {
                for (int x = 0; x < qrImage.getWidth(); x++) {
                    if (qrImage.getRGB(x, y) == Color.BLACK.getRGB()) {
                        top = y;
                        break outerTopLoop;
                    }
                }
            }

            // Find the bottom boundary
            outerBottomLoop:
            for (int y = qrImage.getHeight() - 1; y >= 0; y--) {
                for (int x = 0; x < qrImage.getWidth(); x++) {
                    if (qrImage.getRGB(x, y) == Color.BLACK.getRGB()) {
                        bottom = y;
                        break outerBottomLoop;
                    }
                }
            }

            // Find the left boundary
            outerLeftLoop:
            for (int x = 0; x < qrImage.getWidth(); x++) {
                for (int y = 0; y < qrImage.getHeight(); y++) {
                    if (qrImage.getRGB(x, y) == Color.BLACK.getRGB()) {
                        left = x;
                        break outerLeftLoop;
                    }
                }
            }

            // Find the right boundary
            outerRightLoop:
            for (int x = qrImage.getWidth() - 1; x >= 0; x--) {
                for (int y = 0; y < qrImage.getHeight(); y++) {
                    if (qrImage.getRGB(x, y) == Color.BLACK.getRGB()) {
                        right = x;
                        break outerRightLoop;
                    }
                }
            }

            // Crop the image
            BufferedImage croppedImage = qrImage.getSubimage(left, top, right - left + 1, bottom - top + 1);

            // Convert to byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(croppedImage, "PNG", byteArrayOutputStream);

            System.out.println("QR Code generated successfully");
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
