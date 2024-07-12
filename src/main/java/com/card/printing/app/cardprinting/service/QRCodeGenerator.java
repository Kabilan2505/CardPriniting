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


    public byte[] createQR(String img){
            try{
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
                hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

                BitMatrix bitMatrix = qrCodeWriter.encode(img, BarcodeFormat.QR_CODE,150,200,hints);

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
    }


}
