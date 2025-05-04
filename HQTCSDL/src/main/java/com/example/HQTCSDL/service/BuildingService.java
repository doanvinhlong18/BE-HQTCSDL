package com.example.HQTCSDL.service;

import com.example.HQTCSDL.Dto.BuildingDto;
import com.example.HQTCSDL.Entity.BuildingEntity;
import org.springframework.http.ResponseEntity;

public interface BuildingService {
    ResponseEntity<?> addBuilding(BuildingDto building);
    ResponseEntity<?> deleteBuilding(int idBuilding);
    ResponseEntity<?> updateBuilding(int idBuilding, BuildingDto building);
    ResponseEntity<?> getBuilding(int idBuilding);
    ResponseEntity<?> getAllBuildings();
    ResponseEntity<?> addRoomToBuilding(int idRoom, int idBuilding);
}
