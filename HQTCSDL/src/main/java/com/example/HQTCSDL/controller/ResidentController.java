package com.example.HQTCSDL.controller;

import com.example.HQTCSDL.Dto.ResidentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/resident")
public interface ResidentController {
    @PostMapping("/add")
    ResponseEntity<?> addResident(@RequestBody ResidentDto residentDto);

    @PutMapping("/edit/{idResident}")
    ResponseEntity<?> editResident(@PathVariable int idResident, @RequestBody ResidentDto residentDto);

    @DeleteMapping("/remove/{idResident}")
    ResponseEntity<?> deleteResident(@PathVariable int idResident);

    @GetMapping("/{idResident}")
    ResponseEntity<?> getResidentById(@PathVariable int idResident);

    @GetMapping("/all")
    ResponseEntity<?> getAllResident();

}
