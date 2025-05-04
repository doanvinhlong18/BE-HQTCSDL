package com.example.HQTCSDL.controller;

import com.example.HQTCSDL.Dto.BuildingDto;
import com.example.HQTCSDL.Entity.BuildingEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/building")
public interface BuildingController {
    @PostMapping("/add")
    ResponseEntity<?> addBuilding(@RequestBody BuildingDto building);
    @DeleteMapping("/remove/{idBuilding}")
    ResponseEntity<?> deleteBuilding(@PathVariable int idBuilding);
    @PutMapping("/update/{idBuilding}")
    ResponseEntity<?> updateBuilding(@PathVariable int idBuilding,@RequestBody BuildingDto building);
    @GetMapping("/{idBuilding}")
    ResponseEntity<?> getBuilding(@PathVariable int idBuilding);
    @GetMapping("/all")
    ResponseEntity<?> getAllBuildings();
    @PutMapping("/add-rtb/{idBuilding}/{idRoom}")
    ResponseEntity<?> addRoomToBuilding(@PathVariable int idRoom,@PathVariable int idBuilding);
}
