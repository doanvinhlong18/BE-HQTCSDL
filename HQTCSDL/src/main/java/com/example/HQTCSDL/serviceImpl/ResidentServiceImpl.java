package com.example.HQTCSDL.serviceImpl;

import com.example.HQTCSDL.Dto.ResidentDto;
import com.example.HQTCSDL.Dto.ResponseDto.ResidentResponse;
import com.example.HQTCSDL.Entity.ResidentEntity;
import com.example.HQTCSDL.EntityToResponse.Resident;
import com.example.HQTCSDL.repository.ResidentRepository;
import com.example.HQTCSDL.service.ResidentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;

    public ResidentServiceImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Override
    public ResponseEntity<?> addNewResident(ResidentDto residentDto) {
        ResidentEntity residentEntity = getResidentFromDto(residentDto);
        return new ResponseEntity<>(residentRepository.save(residentEntity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateResident(int idResident, ResidentDto residentDto) {
        Optional<ResidentEntity> residentEntity = residentRepository.findById(idResident);
        if (residentEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Khong tim thay cu dan"), HttpStatus.NOT_FOUND);
        }
        ResidentEntity resident = residentEntity.get();
        resident.setIdNumber(residentDto.getIdNumber());
        resident.setName(residentDto.getName());
        resident.setEmail(residentDto.getEmail());
        resident.setPhone(residentDto.getPhone());
        resident.setDateOfBirth(residentDto.getDateOfBirth());
        residentRepository.save(resident);
        return new ResponseEntity<>(Collections.singletonMap("message", "Chinh sua thong tin thanh cong"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteResident(int idResident) {
        Optional<ResidentEntity> residentEntity = residentRepository.findById(idResident);
        if (residentEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Khong tim thay cu dan"), HttpStatus.NOT_FOUND);
        }
        residentRepository.deleteById(idResident);
        return new ResponseEntity<>(Collections.singletonMap("message", "Xoa thanh cong"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getResident(int idResident) {
        Optional<ResidentEntity> residentEntity = residentRepository.findById(idResident);
        if (residentEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "khong tim thay cu dan"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Resident.getResidentResponse(residentEntity.get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllResident() {
        List<ResidentEntity> residents = residentRepository.findAll();
        if (residents.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Chua co cu dan nao"), HttpStatus.NOT_FOUND);
        }
        List<ResidentResponse> residentResponses = new ArrayList<>();
        for(ResidentEntity resident : residents){
            residentResponses.add(Resident.getResidentResponse(resident));
        }
        return new ResponseEntity<>(residentResponses, HttpStatus.OK);
    }
    public ResidentEntity getResidentFromDto(ResidentDto residentDto) {
        ResidentEntity residentEntity = new ResidentEntity();
        residentEntity.setName(residentDto.getName());
        residentEntity.setEmail(residentDto.getEmail());
        residentEntity.setPhone(residentDto.getPhone());
        residentEntity.setDateOfBirth(residentDto.getDateOfBirth());
        residentEntity.setIdNumber(residentDto.getIdNumber());
        return residentEntity;
    }
}
