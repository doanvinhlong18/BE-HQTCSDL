package com.example.HQTCSDL.Dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResidentResponse {
    int id;
    String name;
    Date dateOfBirth;
    String idNumber;
    String phone;
    String email;
}
