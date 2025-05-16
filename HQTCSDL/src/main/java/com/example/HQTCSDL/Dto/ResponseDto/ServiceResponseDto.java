package com.example.HQTCSDL.Dto.ResponseDto;

import com.example.HQTCSDL.Entity.ServiceEntity;
import com.example.HQTCSDL.Entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServiceResponseDto {
    int id;
    String name;
    float price;
    String unit;
    String description;
    List<UsedServiceResponseDto> usedServices;
    UserEntity user;
}
