package com.example.HQTCSDL.controllerImpl;

import com.example.HQTCSDL.Dto.BuildingDto;
import com.example.HQTCSDL.Entity.BuildingEntity;
import com.example.HQTCSDL.controller.BuildingController;
import com.example.HQTCSDL.service.BuildingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingControllerImpl implements BuildingController {
    private final BuildingService buildingService;

    public BuildingControllerImpl(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @Override
    public ResponseEntity<?> addBuilding(BuildingDto building) {
        return buildingService.addBuilding(building);
    }

    @Override
    public ResponseEntity<?> deleteBuilding(int idBuilding) {
        return buildingService.deleteBuilding(idBuilding);
    }

    @Override
    public ResponseEntity<?> updateBuilding(int idBuilding, BuildingDto building) {
        return buildingService.updateBuilding(idBuilding, building);
    }

    @Override
    public ResponseEntity<?> getBuilding(int idBuilding) {
        return buildingService.getBuilding(idBuilding);
    }

    @Override
    public ResponseEntity<?> getAllBuildings() {
        return buildingService.getAllBuildings();
    }

    @Override
    public ResponseEntity<?> addRoomToBuilding(int idRoom, int idBuilding) {
        return buildingService.addRoomToBuilding(idRoom, idBuilding);
    }
}
