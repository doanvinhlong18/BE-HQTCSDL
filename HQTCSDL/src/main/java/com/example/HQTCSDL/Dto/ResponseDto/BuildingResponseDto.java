package com.example.HQTCSDL.Dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingResponseDto {
    int id;
    String name;
    String address;
    int floorCount;
    int totalRooms;
    String status;
}
