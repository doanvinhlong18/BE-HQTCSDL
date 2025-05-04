package com.example.HQTCSDL.Dto;
import lombok.Data;

import java.util.Date;

@Data
public class ResidentDto {
    int id;
    String name;
    Date dateOfBirth;
    String idNumber;
    String phone;
    String email;
}
