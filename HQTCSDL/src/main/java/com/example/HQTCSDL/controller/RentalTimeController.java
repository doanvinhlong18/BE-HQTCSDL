package com.example.HQTCSDL.controller;

import com.example.HQTCSDL.Dto.RentalTimeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rentaltime")
public interface RentalTimeController {
    @PostMapping("/add/{idRoom}")
    ResponseEntity<?> addRentalTime(@PathVariable int idRoom,@RequestBody RentalTimeDto rentalTimeDto);
    @DeleteMapping("/remove/{idRentalTime}")
    ResponseEntity<?> removeRentalTime(@PathVariable int idRentalTime);
    @PutMapping("/update/{idRentalTime}")
    ResponseEntity<?> updateRentalTime(@PathVariable int idRentalTime, @RequestBody RentalTimeDto rentalTimeDto);
    @GetMapping("/{idRentalTime}")
    ResponseEntity<?> getRentalTime(@PathVariable int idRentalTime);
    @GetMapping("/all")
    ResponseEntity<?> getAllRentalTimes();
    @PutMapping("/update/all/{idRoom}")
    ResponseEntity<?> updateAllRentalTimes(@PathVariable int idRoom,@RequestBody RentalTimeDto rentalTimeDto);
}
