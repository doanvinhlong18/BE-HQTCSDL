package com.example.HQTCSDL.controllerImpl;

import com.example.HQTCSDL.Dto.ServiceDto;
import com.example.HQTCSDL.controller.ServiceController;
import com.example.HQTCSDL.service.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceControllerImpl implements ServiceController {
    private final ServiceService serviceService;

    public ServiceControllerImpl(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @Override
    public ResponseEntity<?> addService(ServiceDto serviceDto) {
        return serviceService.addService(serviceDto);
    }

    @Override
    public ResponseEntity<?> removeService(int idService) {
        return serviceService.deleteService(idService);
    }

    @Override
    public ResponseEntity<?> updateService(int idService, ServiceDto serviceDto) {
        return serviceService.updateService(idService, serviceDto);
    }

    @Override
    public ResponseEntity<?> getServiceById(int idService) {
        return serviceService.getService(idService);
    }

    @Override
    public ResponseEntity<?> getAllService() {
        return serviceService.getAllServices();
    }
}
