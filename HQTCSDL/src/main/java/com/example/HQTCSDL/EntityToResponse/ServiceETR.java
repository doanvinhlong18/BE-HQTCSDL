package com.example.HQTCSDL.EntityToResponse;

import com.example.HQTCSDL.Dto.ResponseDto.ServiceResponseDto;
import com.example.HQTCSDL.Entity.ServiceEntity;

public class ServiceETR {
    public static ServiceResponseDto getServiceResponseDto(ServiceEntity serviceEntity) {
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        serviceResponseDto.setId(serviceEntity.getId());
        serviceResponseDto.setName(serviceEntity.getName());
        serviceResponseDto.setDescription(serviceEntity.getDescription());
        serviceResponseDto.setUnit(serviceEntity.getUnit());
        serviceResponseDto.setPrice(serviceEntity.getPrice());
        return serviceResponseDto;
    }
}
