package com.example.HQTCSDL.service;

import com.example.HQTCSDL.Dto.RentalTimeDto;
import org.springframework.http.ResponseEntity;

public interface RentalTimeService{
    ResponseEntity<?> addRentalTime(int idRoom, RentalTimeDto rentalTimeDto);
    ResponseEntity<?> removeRentalTime(int idRentalTime);
    ResponseEntity<?> updateRentalTime(int idRoom);
    ResponseEntity<?> getRentalTime(int idRentalTime);
    ResponseEntity<?> getAllRentalTimes();
}
