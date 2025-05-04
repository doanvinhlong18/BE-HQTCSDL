package com.example.HQTCSDL.service;

import com.example.HQTCSDL.Dto.AddResidentToRoomDto;
import com.example.HQTCSDL.Dto.RentalTimeDto;
import com.example.HQTCSDL.Dto.ResidentDto;
import com.example.HQTCSDL.Dto.RoomDto;
import com.example.HQTCSDL.Entity.RoomEntity;
import org.springframework.http.ResponseEntity;

public interface RoomService {
    ResponseEntity<?> editRoom(int idRoom, RoomEntity roomEntity);
    ResponseEntity<?> deleteAllResidentsFromRoom(int idRoom);
    ResponseEntity<?> deleteResidentFromRoom(int idResident);
    ResponseEntity<?> addResidentToRoom(int idRoom, RentalTimeDto rentalTimeDto);
    ResponseEntity<?> addRoom(RoomDto roomDto);
    ResponseEntity<?> getRoom(int idRoom);
    ResponseEntity<?> getAllRooms();
}
