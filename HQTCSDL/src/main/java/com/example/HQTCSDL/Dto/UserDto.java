package com.example.HQTCSDL.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    int id;
    String username;
    String password;
    String fullName;
    Date dateOfBirth;
    String phone;
    String email;
    String position;
}
