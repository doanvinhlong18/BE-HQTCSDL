package com.example.HQTCSDL.Dto;

import lombok.Data;

@Data
public class BuildingDto {
    int id;
    String name;
    String address;
    int floorCount;
    int totalRooms;
    String status;
}
