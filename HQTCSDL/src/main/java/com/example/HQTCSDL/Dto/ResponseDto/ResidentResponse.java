package com.example.HQTCSDL.Dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ResidentResponse {
    int id;
    String name;
    Date dateOfBirth;
    String idNumber;
    String phone;
    String email;
    List<UsedServiceResponseDto> usedServices;
}
