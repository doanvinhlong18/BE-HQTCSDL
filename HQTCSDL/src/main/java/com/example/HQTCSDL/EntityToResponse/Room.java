package com.example.HQTCSDL.EntityToResponse;

import com.example.HQTCSDL.Dto.ResponseDto.RoomResponseDto;
import com.example.HQTCSDL.Entity.RoomEntity;

public class Room {
    public static RoomResponseDto getRoomResponseDto(RoomEntity roomEntity) {
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        roomResponseDto.setId(roomEntity.getId());
        roomResponseDto.setName(roomEntity.getName());
        roomResponseDto.setArea(roomEntity.getArea());
        roomResponseDto.setType(roomEntity.getType());
        roomResponseDto.setRentPrice(roomEntity.getRentPrice());
        roomResponseDto.setRentStatus(roomEntity.getRentStatus());
        return roomResponseDto;
    }
}
