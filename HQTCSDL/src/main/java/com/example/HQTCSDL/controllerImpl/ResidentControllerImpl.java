package com.example.HQTCSDL.controllerImpl;

import com.example.HQTCSDL.Dto.ResidentDto;
import com.example.HQTCSDL.controller.ResidentController;
import com.example.HQTCSDL.repository.ResidentRepository;
import com.example.HQTCSDL.service.ResidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResidentControllerImpl implements ResidentController {


    private final ResidentService residentService;

    public ResidentControllerImpl(ResidentService residentService) {
        this.residentService = residentService;
    }

    @Override
    public ResponseEntity<?> addResident(ResidentDto residentDto) {
        return residentService.addNewResident(residentDto);
    }

    @Override
    public ResponseEntity<?> editResident(int idResident, ResidentDto residentDto) {
        return residentService.updateResident(idResident, residentDto);
    }

    @Override
    public ResponseEntity<?> deleteResident(int idResident) {
        return residentService.deleteResident(idResident);
    }

    @Override
    public ResponseEntity<?> getResidentById(int idResident) {
        return residentService.getResident(idResident);
    }

    @Override
    public ResponseEntity<?> getAllResident() {
        return residentService.getAllResident();
    }
}
