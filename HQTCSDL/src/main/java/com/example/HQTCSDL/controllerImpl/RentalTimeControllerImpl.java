package com.example.HQTCSDL.controllerImpl;

import com.example.HQTCSDL.Dto.RentalTimeDto;
import com.example.HQTCSDL.controller.RentalTimeController;
import com.example.HQTCSDL.service.RentalTimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalTimeControllerImpl implements RentalTimeController {
    private final RentalTimeService rentalTimeService;

    public RentalTimeControllerImpl(RentalTimeService rentalTimeService) {
        this.rentalTimeService = rentalTimeService;
    }

    @Override
    public ResponseEntity<?> addRentalTime(int idRoom, RentalTimeDto rentalTimeDto) {
        return rentalTimeService.addRentalTime(idRoom, rentalTimeDto);
    }

    @Override
    public ResponseEntity<?> removeRentalTime(int idRentalTime) {
        return rentalTimeService.removeRentalTime(idRentalTime);
    }

    @Override
    public ResponseEntity<?> updateRentalTime(int idRentalTime, RentalTimeDto rentalTimeDto) {
        return rentalTimeService.updateRentalTime(idRentalTime, rentalTimeDto);
    }

    @Override
    public ResponseEntity<?> getRentalTime(int idRentalTime) {
        return rentalTimeService.getRentalTime(idRentalTime);
    }

    @Override
    public ResponseEntity<?> getAllRentalTimes() {
        return rentalTimeService.getAllRentalTimes();
    }
}
