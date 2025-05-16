package com.example.HQTCSDL.Dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UsedServiceResponseDto {
    int id;
    Date startDateTime;
    Date endDateTime;
    String status;
    Integer quantity;
    ServiceResponseDto service;
    ResidentResponse resident;
}
