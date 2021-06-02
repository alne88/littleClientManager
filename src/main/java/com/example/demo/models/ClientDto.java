package com.example.demo.models;

import lombok.Data;

@Data
public class ClientDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String address;
    private String country;
    private Long createdByUserId;

}
