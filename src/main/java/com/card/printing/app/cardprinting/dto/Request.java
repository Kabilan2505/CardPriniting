package com.card.printing.app.cardprinting.dto;


import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    public String zipFilePath;

    public String extracingFilePath;
}
