package com.example.HQTCSDL.EntityToResponse;

import com.example.HQTCSDL.Dto.ResponseDto.BuildingResponseDto;
import com.example.HQTCSDL.Entity.BuildingEntity;

public class Building {
    public static BuildingResponseDto getBuildingResponseDto(BuildingEntity buildingEntity) {
        BuildingResponseDto buildingResponseDto = new BuildingResponseDto();
        buildingResponseDto.setId(buildingEntity.getId());
        buildingResponseDto.setName(buildingEntity.getName());
        buildingResponseDto.setAddress(buildingEntity.getAddress());
        buildingResponseDto.setStatus(buildingEntity.getStatus());
        buildingResponseDto.setFloorCount(buildingEntity.getFloorCount());
        buildingResponseDto.setTotalRooms(buildingEntity.getTotalRooms());
        return buildingResponseDto;
    }
}
