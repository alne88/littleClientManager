package com.example.demo.models;

import lombok.Data;

@Data
public class LookupValueDto {

    private LookupValueCode code;
    private String value;

    public enum LookupValueCode {
        COUNTRY
    }
}
