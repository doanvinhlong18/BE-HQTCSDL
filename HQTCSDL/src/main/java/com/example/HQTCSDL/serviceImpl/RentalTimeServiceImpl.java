package com.example.HQTCSDL.serviceImpl;

import com.example.HQTCSDL.Dto.RentalTimeDto;
import com.example.HQTCSDL.Entity.RentalTimeEntity;
import com.example.HQTCSDL.Entity.RoomEntity;
import com.example.HQTCSDL.repository.RentalTimeRepository;
import com.example.HQTCSDL.repository.RoomRepository;
import com.example.HQTCSDL.service.RentalTimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RentalTimeServiceImpl implements RentalTimeService {

    private final RoomRepository roomRepository;
    private final RentalTimeRepository rentalTimeRepository;

    public RentalTimeServiceImpl(RoomRepository roomRepository, RentalTimeRepository rentalTimeRepository) {
        this.roomRepository = roomRepository;
        this.rentalTimeRepository = rentalTimeRepository;
    }

    @Override
    public ResponseEntity<?> addRentalTime(int idRoom, RentalTimeDto rentalTimeDto) {
        Optional<RoomEntity> roomEntity = roomRepository.findById(idRoom);
        if(roomEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Room not found"), HttpStatus.NOT_FOUND);
        }
        System.out.println(rentalTimeDto);
        if (rentalTimeDto.getStartTime() == null || rentalTimeDto.getEndTime() == null) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Start date and end date are required"), HttpStatus.BAD_REQUEST);
        }
        if (rentalTimeDto.getStartTime().after(rentalTimeDto.getEndTime())) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Start date must be before end date"), HttpStatus.BAD_REQUEST);
        }
        RentalTimeEntity rentalTimeEntity = getRentalTimeFromDto(rentalTimeDto);
        rentalTimeEntity.setRoom(roomEntity.get());
        rentalTimeRepository.save(rentalTimeEntity);
        return new ResponseEntity<>(Collections.singletonMap("message", "Rental time added"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> removeRentalTime(int idRentalTime) {
        Optional<RentalTimeEntity> rentalTimeEntity = rentalTimeRepository.findById(idRentalTime);
        if(rentalTimeEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Rental time not found"), HttpStatus.NOT_FOUND);
        }
        RentalTimeEntity rentalTime = rentalTimeEntity.get();
        rentalTime.setRoom(null);
        rentalTime.setResident(null);
        rentalTimeRepository.delete(rentalTime);
        return new ResponseEntity<>(Collections.singletonMap("message", "Rental time removed"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateRentalTime(int idRentalTime, RentalTimeDto rentalTimeDto) {
        Optional<RentalTimeEntity> rentalTimeEntity = rentalTimeRepository.findById(idRentalTime);
        if(rentalTimeEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Rental time not found"), HttpStatus.NOT_FOUND);
        }
        RentalTimeEntity rentalTime = rentalTimeEntity.get();
        rentalTime.setEndDate(rentalTimeDto.getEndTime());
        rentalTime.setStartDate(rentalTimeDto.getStartTime());
        rentalTimeRepository.save(rentalTime);
        return new ResponseEntity<>(Collections.singletonMap("message", "Rental time updated"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getRentalTime(int idRentalTime) {
        Optional<RentalTimeEntity> rentalTimeEntity = rentalTimeRepository.findById(idRentalTime);
        if(rentalTimeEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Rental time not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rentalTimeEntity.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllRentalTimes() {
        List<RentalTimeEntity> rentalTimeEntities = rentalTimeRepository.findAll();
        if(rentalTimeEntities.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Rental time not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rentalTimeEntities, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateRentalTimeByRoom(int idRoom, RentalTimeDto rentalTimeDto) {
        Optional<RoomEntity> roomEntity = roomRepository.findById(idRoom);
        if(roomEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Room not found"), HttpStatus.NOT_FOUND);
        }
        RoomEntity room = roomEntity.get();
        for(RentalTimeEntity rentalTime: room.getRentalTimes()){
            rentalTime.setStartDate(rentalTimeDto.getStartTime());
            rentalTime.setEndDate(rentalTimeDto.getEndTime());
            rentalTimeRepository.save(rentalTime);
        }
        return new ResponseEntity<>(Collections.singletonMap("message", "Rental time updated"), HttpStatus.OK);
    }

    public RentalTimeEntity getRentalTimeFromDto(RentalTimeDto rentalTimeDto) {
        RentalTimeEntity rentalTimeEntity = new RentalTimeEntity();
        rentalTimeEntity.setEndDate(rentalTimeDto.getEndTime());
        rentalTimeEntity.setStartDate(rentalTimeDto.getStartTime());
        return rentalTimeEntity;
    }
}
