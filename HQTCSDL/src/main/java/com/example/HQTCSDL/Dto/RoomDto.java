package com.example.HQTCSDL.Dto;

import lombok.Data;

@Data
public class RoomDto {
    int id;
    String name;
    String type;
    float area;
    float rentPrice;
    String rentStatus;
}
