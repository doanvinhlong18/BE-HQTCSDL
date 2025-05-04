package com.example.HQTCSDL.controllerImpl;

import com.example.HQTCSDL.Dto.UsedServiceDto;
import com.example.HQTCSDL.controller.UsedServiceController;
import com.example.HQTCSDL.service.UsedServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsedServiceControllerImpl implements UsedServiceController {
    private final UsedServiceService usedServiceService;

    public UsedServiceControllerImpl(UsedServiceService usedServiceService) {
        this.usedServiceService = usedServiceService;
    }

    @Override
    public ResponseEntity<?> addUsedService(int idResident, int idService, UsedServiceDto usedServiceDto) {
        return usedServiceService.addUsedService(idResident, idService, usedServiceDto);
    }

    @Override
    public ResponseEntity<?> updateUsedService(int idUsedService, UsedServiceDto usedServiceDto) {
        return usedServiceService.updateUsedService(idUsedService, usedServiceDto);
    }

    @Override
    public ResponseEntity<?> getUsedService(int idUsedService) {
        return usedServiceService.getUsedService(idUsedService);
    }

    @Override
    public ResponseEntity<?> getAllUsedServices() {
        return usedServiceService.getAllUsedServices();
    }

    @Override
    public ResponseEntity<?> getUsedServiceByResident(int idResident) {
        return usedServiceService.getUsedServiceByResident(idResident);
    }
}
