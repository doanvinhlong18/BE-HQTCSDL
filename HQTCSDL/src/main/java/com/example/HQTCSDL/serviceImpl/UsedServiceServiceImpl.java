package com.example.HQTCSDL.serviceImpl;

import com.example.HQTCSDL.Dto.UsedServiceDto;
import com.example.HQTCSDL.Entity.ResidentEntity;
import com.example.HQTCSDL.Entity.ServiceEntity;
import com.example.HQTCSDL.Entity.UsedServiceEntity;
import com.example.HQTCSDL.repository.ResidentRepository;
import com.example.HQTCSDL.repository.ServiceRepository;
import com.example.HQTCSDL.repository.UsedServiceRepository;
import com.example.HQTCSDL.service.UsedServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsedServiceServiceImpl implements UsedServiceService {
    private final ResidentRepository residentRepository;
    private final ServiceRepository serviceRepository;
    private final UsedServiceRepository usedServiceRepository;

    public UsedServiceServiceImpl(ResidentRepository residentRepository, ServiceRepository serviceRepository, UsedServiceRepository usedServiceRepository) {
        this.residentRepository = residentRepository;
        this.serviceRepository = serviceRepository;
        this.usedServiceRepository = usedServiceRepository;
    }

    @Override
    public ResponseEntity<?> addUsedService(int idResident, int idService, UsedServiceDto usedServiceDto) {
        Optional<ResidentEntity> residentEntity = residentRepository.findById(idResident);
        if (residentEntity.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Resident not found"), HttpStatus.NOT_FOUND);
        }
        ResidentEntity resident = residentEntity.get();
        Optional<ServiceEntity> serviceEntity = serviceRepository.findById(idService);
        if (serviceEntity.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Service not found"), HttpStatus.NOT_FOUND);
        }
        ServiceEntity service = serviceEntity.get();
        UsedServiceEntity usedServiceEntity = new UsedServiceEntity();
        usedServiceEntity.setEndDateTime(usedServiceDto.getEndDateTime());
        usedServiceEntity.setStartDateTime(usedServiceDto.getStartDateTime());
        usedServiceEntity.setQuantity(usedServiceDto.getQuantity());
        usedServiceEntity.setStatus(usedServiceDto.getStatus());
        usedServiceEntity.setResident(resident);
        usedServiceEntity.setService(service);
        usedServiceRepository.save(usedServiceEntity);
        return new ResponseEntity<>(Collections.singletonMap("message", "Service added"), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> deleteUsedService(int idUsedService) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateUsedService(int idUsedService, UsedServiceDto usedServiceDto) {
        Optional<UsedServiceEntity> usedServiceEntity = usedServiceRepository.findById(idUsedService);
        if (usedServiceEntity.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("message", "UsedService not found"), HttpStatus.NOT_FOUND);
        }
        UsedServiceEntity usedService = usedServiceEntity.get();
        usedService.setStatus(usedServiceDto.getStatus());
        usedServiceRepository.save(usedService);
        return new ResponseEntity<>(Collections.singletonMap("message", "UsedService updated"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUsedService(int idUsedService) {
        Optional<UsedServiceEntity> usedServiceEntity = usedServiceRepository.findById(idUsedService);
        if (usedServiceEntity.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("message", "UsedService not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usedServiceEntity.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllUsedServices() {
        List<UsedServiceEntity> usedServiceEntities = usedServiceRepository.findAll();
        if (usedServiceEntities.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("message", "No usedService"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usedServiceEntities, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUsedServiceByResident(int idResident) {
        Optional<ResidentEntity> residentEntity = residentRepository.findById(idResident);
        if (residentEntity.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Resident not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(residentEntity.get().getUsedServices(), HttpStatus.OK);
    }
}
