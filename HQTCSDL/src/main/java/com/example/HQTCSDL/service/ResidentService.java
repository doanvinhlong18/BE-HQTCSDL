package com.example.HQTCSDL.service;

import com.example.HQTCSDL.Dto.ResidentDto;
import org.springframework.http.ResponseEntity;

public interface ResidentService {
    ResponseEntity<?> addNewResident(ResidentDto residentDto);
    ResponseEntity<?> updateResident(int idResident, ResidentDto residentDto);
    ResponseEntity<?> deleteResident(int idResident);
    ResponseEntity<?> getResident(int idResident);
    ResponseEntity<?> getAllResident();
}
