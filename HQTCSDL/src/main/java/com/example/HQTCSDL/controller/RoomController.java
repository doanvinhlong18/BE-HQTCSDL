package com.example.HQTCSDL.controller;

import com.example.HQTCSDL.Dto.AddResidentToRoomDto;
import com.example.HQTCSDL.Dto.RentalTimeDto;
import com.example.HQTCSDL.Dto.RoomDto;
import com.example.HQTCSDL.Entity.RoomEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/room")
public interface RoomController {
    @PutMapping("/edit/{idRoom}")
    ResponseEntity<?> editRoom(@PathVariable int idRoom,@RequestBody RoomEntity roomEntity);
    @PutMapping("/remove/{idRoom}")
    ResponseEntity<?> deleteAllResidentsFromRoom(@PathVariable int idRoom);
    @PutMapping("/break/{idResident}")
    ResponseEntity<?> deleteResidentFromRoom(@PathVariable int idResident);
    @PutMapping("/add/resident/{idRoom}")
    ResponseEntity<?> addResidentToRoom(@PathVariable int idRoom, @RequestBody RentalTimeDto rentalTimeDto);
    @GetMapping("/{idRoom}")
    ResponseEntity<?> getRoomById(@PathVariable int idRoom);
    @GetMapping("/all")
    ResponseEntity<?> getAllRooms();
    @PostMapping("/add")
    ResponseEntity<?> addRoom(@RequestBody RoomDto room);
}
