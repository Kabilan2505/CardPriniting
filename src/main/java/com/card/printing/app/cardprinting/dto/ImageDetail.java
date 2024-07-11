package com.card.printing.app.cardprinting.dto;

import lombok.Data;

@Data
public class ImageDetail {

    private int width;
    private int height;
    private byte[] data;
    private String format;
    private String imageType;
    private String subtype;

}
