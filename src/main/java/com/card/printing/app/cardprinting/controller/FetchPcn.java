package com.card.printing.app.cardprinting.controller;

import com.card.printing.app.cardprinting.dto.Request;
import com.card.printing.app.cardprinting.service.ArchiveExtractor;
import com.card.printing.app.cardprinting.service.ExtractText;
import com.card.printing.app.cardprinting.service.FetchPCN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/card")
public class FetchPcn {

    @Autowired
    private FetchPCN pcn;

    @Autowired
    private ArchiveExtractor extractor;

   @Autowired
   private ExtractText extractText;

    @GetMapping("/PRINT")
    public String printCards(){
//        return pcn.printCards();
//        ZebraPrinter printer = new ZebraPrinter();
        return null;
    }

    @PostMapping("/extract")
    public void extractZipOrRar(@RequestBody Request request) throws FileNotFoundException {
//        String zipFilePath = "D:/Suthan/Zebra/card/input";
//        String extractingFilePath = "D:/Suthan/Zebra/card/output";
//        System.out.println(" Req "+ request.zipFilePath + " output " + request.getExtracingFilePath());
        extractor.extract(request.zipFilePath, request.getExtracingFilePath());
    }

    @GetMapping("/encrypted/extract")
    public void encryptedExtract() throws FileNotFoundException {
        File zipFilePath = new File("D:/Suthan/Zebra/card/encrypted/pcns.zip");
        String extractingFilePath = "D:/Suthan/Zebra/card/encrypted-output";
        String password = "2002";
        extractor.extractEncrypted(zipFilePath, extractingFilePath,password);

    }

    @GetMapping("/update")
    public void update() throws IOException {
        String zipFilePath = "D:/Suthan/Zebra/card/output/pcn-2";
        String externalTxt = "D:/Suthan/Zebra/card/output/pcn.txt";
        String fileName = "pcn.txt";

        String pathOutput = "D:/Suthan/Zebra/card/output/pcn.txt";

//        Path path = Paths.get(externalTxt,fileName);

        extractText.checkAndUpdateFile(externalTxt,zipFilePath,pathOutput);
    }

}
