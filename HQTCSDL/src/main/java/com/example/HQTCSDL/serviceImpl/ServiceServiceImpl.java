package com.example.HQTCSDL.serviceImpl;

import com.example.HQTCSDL.Dto.ServiceDto;
import com.example.HQTCSDL.Entity.ServiceEntity;
import com.example.HQTCSDL.repository.ServiceRepository;
import com.example.HQTCSDL.service.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public ResponseEntity<?> addService(ServiceDto serviceDto) {
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setName(serviceDto.getName());
        serviceEntity.setDescription(serviceDto.getDescription());
        serviceEntity.setPrice(serviceDto.getPrice());
        serviceEntity.setUnit(serviceDto.getUnit());
        serviceRepository.save(serviceEntity);
        return new ResponseEntity<>(Collections.singletonMap("message", "Them dich vu thanh cong"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteService(int idService) {
        Optional<ServiceEntity> serviceEntity = serviceRepository.findById(idService);
        if (serviceEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "No such service"), HttpStatus.NOT_FOUND);
        }
        serviceRepository.deleteById(idService);
        return new ResponseEntity<>(Collections.singletonMap("message", "Service deleted"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateService(int idService, ServiceDto serviceDto) {
        Optional<ServiceEntity> serviceEntity = serviceRepository.findById(idService);
        if (serviceEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "No such service"), HttpStatus.NOT_FOUND);
        }
        ServiceEntity service = serviceEntity.get();
        service.setName(serviceDto.getName());
        service.setDescription(serviceDto.getDescription());
        service.setPrice(serviceDto.getPrice());
        service.setUnit(serviceDto.getUnit());
        serviceRepository.save(service);
        return new ResponseEntity<>(Collections.singletonMap("message", "Service updated"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getService(int idService) {
        ServiceEntity serviceEntity = serviceRepository.findById(idService).orElseThrow(()
        -> new RuntimeException("No such service"));
        return new ResponseEntity<>(serviceEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllServices() {
        List<ServiceEntity> serviceEntities = serviceRepository.findAll();
        if (serviceEntities.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "No service"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(serviceEntities, HttpStatus.OK);
    }
}
