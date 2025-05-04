package com.example.HQTCSDL.service;

import com.example.HQTCSDL.Dto.UsedServiceDto;
import org.springframework.http.ResponseEntity;

public interface UsedServiceService {
    ResponseEntity<?> addUsedService(int idResident, int idService, UsedServiceDto usedServiceDto);
    ResponseEntity<?> deleteUsedService(int idUsedService);
    ResponseEntity<?> updateUsedService(int idUsedService, UsedServiceDto usedServiceDto);
    ResponseEntity<?> getUsedService(int idUsedService);
    ResponseEntity<?> getAllUsedServices();
    ResponseEntity<?> getUsedServiceByResident(int idResident);
}
