package com.example.HQTCSDL.serviceImpl;

import com.example.HQTCSDL.Dto.BuildingDto;
import com.example.HQTCSDL.Entity.BuildingEntity;
import com.example.HQTCSDL.Entity.RoomEntity;
import com.example.HQTCSDL.repository.BuildingRepository;
import com.example.HQTCSDL.repository.RoomRepository;
import com.example.HQTCSDL.service.BuildingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository buildingRepository;
    private final RoomRepository roomRepository;

    public BuildingServiceImpl(BuildingRepository buildingRepository, RoomRepository roomRepository) {
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public ResponseEntity<?> addBuilding(BuildingDto building) {
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setName(building.getName());
        buildingEntity.setAddress(building.getAddress());
        buildingEntity.setStatus(building.getStatus());
        buildingEntity.setFloorCount(building.getFloorCount());
        buildingEntity.setTotalRooms(building.getTotalRooms());
        buildingRepository.save(buildingEntity);
        return new ResponseEntity<>(Collections.singletonMap("message", "Them thanh cong"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteBuilding(int idBuilding) {
        Optional<BuildingEntity> buildingEntity = buildingRepository.findById(idBuilding);
        if (buildingEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "There is nothing to delete"), HttpStatus.NOT_FOUND);
        }
        buildingRepository.deleteById(idBuilding);
        return new ResponseEntity<>(Collections.singletonMap("message", "Xoa thanh cong"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateBuilding(int idBuilding, BuildingDto building) {
        Optional<BuildingEntity> buildingEntity = buildingRepository.findById(idBuilding);
        if (buildingEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "There is nothing to update"), HttpStatus.NOT_FOUND);
        }
        BuildingEntity buildingEntity1 = buildingEntity.get();
        buildingEntity1.setName(building.getName());
        buildingEntity1.setAddress(building.getAddress());
        buildingEntity1.setStatus(building.getStatus());
        buildingEntity1.setFloorCount(building.getFloorCount());
        buildingEntity1.setTotalRooms(building.getTotalRooms());
        return new ResponseEntity<>(Collections.singletonMap("message", "Cap nhat thanh cong"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getBuilding(int idBuilding) {
        Optional<BuildingEntity> buildingEntity = buildingRepository.findById(idBuilding);
        if (buildingEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "There is nothing to view"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(buildingEntity.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllBuildings() {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll();
        if (buildingEntities.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "There is nothing to view"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(buildingEntities, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addRoomToBuilding(int idRoom, int idBuilding) {
        Optional<BuildingEntity> buildingEntity = buildingRepository.findById(idBuilding);
        if (buildingEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Khong tim thay toa nha"), HttpStatus.NOT_FOUND);
        }
        BuildingEntity building = buildingEntity.get();
        Optional<RoomEntity> roomEntity = roomRepository.findById(idRoom);
        if (roomEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Khong tim thay phong"), HttpStatus.NOT_FOUND);
        }
        RoomEntity room = roomEntity.get();
        // Gán quan hệ hai chiều nếu có
        room.setBuilding(building); // cần thiết nếu Room có @ManyToOne Building
        roomRepository.save(room);  // lưu Room là đủ nếu chỉ thay đổi phía Room
        return new ResponseEntity<>(Collections.singletonMap("message", "Them phong tro vao toa nha thanh cong"), HttpStatus.OK);
    }

    public BuildingEntity getBuildingEntity(BuildingDto buildingDto) {
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setTotalRooms(buildingDto.getTotalRooms());
        buildingEntity.setName(buildingDto.getName());
        buildingEntity.setAddress(buildingDto.getAddress());
        buildingEntity.setStatus(buildingDto.getStatus());
        buildingEntity.setFloorCount(buildingDto.getFloorCount());
        return buildingEntity;
    }
}
