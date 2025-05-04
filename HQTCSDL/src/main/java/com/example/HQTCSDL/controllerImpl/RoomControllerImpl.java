package com.example.HQTCSDL.controllerImpl;

import com.example.HQTCSDL.Dto.AddResidentToRoomDto;
import com.example.HQTCSDL.Dto.RentalTimeDto;
import com.example.HQTCSDL.Dto.RoomDto;
import com.example.HQTCSDL.Entity.RoomEntity;
import com.example.HQTCSDL.controller.RoomController;
import com.example.HQTCSDL.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomControllerImpl implements RoomController {
    private final RoomService roomService;

    public RoomControllerImpl(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public ResponseEntity<?> editRoom(int idRoom, RoomEntity roomEntity) {
        return roomService.editRoom(idRoom, roomEntity);
    }

    @Override
    public ResponseEntity<?> deleteAllResidentsFromRoom(int idRoom) {
        return roomService.deleteAllResidentsFromRoom(idRoom);
    }

    @Override
    public ResponseEntity<?> deleteResidentFromRoom(int idResident) {
        return roomService.deleteResidentFromRoom(idResident);
    }

    @Override
    public ResponseEntity<?> addResidentToRoom(int idRoom, RentalTimeDto rentalTimeDto) {
        return roomService.addResidentToRoom(idRoom, rentalTimeDto);
    }

    @Override
    public ResponseEntity<?> getRoomById(int idRoom) {
        return roomService.getRoom(idRoom);
    }

    @Override
    public ResponseEntity<?> getAllRooms() {
        return roomService.getAllRooms();
    }

    @Override
    public ResponseEntity<?> addRoom(RoomDto room) {
        return roomService.addRoom(room);
    }
}
