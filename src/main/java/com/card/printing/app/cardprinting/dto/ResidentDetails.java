package com.card.printing.app.cardprinting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidentDetails {

    @JsonProperty("id")
    private String id;

    @JsonProperty("psn")
    private String psn;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("middlename")
    private String middlename;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("suffix")
    private String suffix;

    @JsonProperty("sex")
    private String sex;

    @JsonProperty("mobileno")
    private String mobileno;

    @JsonProperty("email")
    private String email;

    @JsonProperty("bloodtype")
    private String bloodtype;

    @JsonProperty("birthdate")
    private String birthdate;

    @JsonProperty("birthcity")
    private String birthcity;

    @JsonProperty("birthprovince")
    private String birthprovince;

    @JsonProperty("birthcountry")
    private String birthcountry;

    @JsonProperty("maritalstatus")
    private String maritalstatus;

    @JsonProperty("presentBarangay")
    private String presentBarangay;

    @JsonProperty("presentProvince")
    private String presentProvince;

    @JsonProperty("presentCity")
    private String presentCity;

    @JsonProperty("presentAddressLine1")
    private String presentAddressLine1;

    @JsonProperty("presentCountry")
    private String presentCountry;

    @JsonProperty("presentZipcode")
    private String presentZipcode;

    @JsonProperty("permanentBarangay")
    private String permanentBarangay;

    @JsonProperty("permanentProvince")
    private String permanentProvince;

    @JsonProperty("permanentCity")
    private String permanentCity;

    @JsonProperty("permanentAddressLine1")
    private String permanentAddressLine1;

    @JsonProperty("permanentCountry")
    private String permanentCountry;

    @JsonProperty("permanentZipcode")
    private String permanentZipcode;

    @JsonProperty("modeOfClaim")
    private String modeOfClaim;

    @JsonProperty("introducerFirstName")
    private String introducerFirstName;

    @JsonProperty("introducerSuffix")
    private String introducerSuffix;

    @JsonProperty("introducerMiddleName")
    private String introducerMiddleName;

    @JsonProperty("introducerLastName")
    private String introducerLastName;

    @JsonProperty("registrationId")
    private String registrationId;

    @JsonProperty("residenceStatus")
    private String residenceStatus;

    @JsonProperty("centerId")
    private String centerId;

    @JsonProperty("creationDate")
    private String creationDate;

    @JsonProperty("BF1")
    private String BF1;

    @JsonProperty("BF2")
    private String BF2;

    @JsonProperty("processStatus")
    private boolean processStatus;

    @JsonProperty("img")
    private byte[] img;

    @JsonProperty("qrImg")
    private byte[] qrImg;

    @JsonProperty("qrFaceImg")
    private byte[] qrFaceImg;

}
