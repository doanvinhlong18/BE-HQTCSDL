package com.example.HQTCSDL.EntityToResponse;

import com.example.HQTCSDL.Dto.ResponseDto.UsedServiceResponseDto;
import com.example.HQTCSDL.Entity.UsedServiceEntity;

public class UsedService {
    public static UsedServiceResponseDto getUsedServiceResponseDto(UsedServiceEntity usedService){
        UsedServiceResponseDto usedServiceResponseDto = new UsedServiceResponseDto();
        usedServiceResponseDto.setId(usedService.getId());
        usedServiceResponseDto.setStatus(usedService.getStatus());
        usedServiceResponseDto.setQuantity(usedService.getQuantity());
        usedServiceResponseDto.setEndDateTime(usedService.getEndDateTime());
        usedServiceResponseDto.setStartDateTime(usedService.getStartDateTime());
        return usedServiceResponseDto;
    }
}
