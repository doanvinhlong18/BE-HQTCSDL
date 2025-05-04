package com.example.HQTCSDL.service;

import com.example.HQTCSDL.Dto.ServiceDto;
import org.springframework.http.ResponseEntity;

public interface ServiceService {
    ResponseEntity<?> addService(ServiceDto serviceDto);
    ResponseEntity<?> deleteService(int idService);
    ResponseEntity<?> updateService(int idService, ServiceDto serviceDto);
    ResponseEntity<?> getService(int idService);
    ResponseEntity<?> getAllServices();
}
