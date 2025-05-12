package com.example.HQTCSDL.Dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RoomResponseDto {
    int id;
    String name;
    String type;
    float area;
    float rentPrice;
    String rentStatus;
    List<RentalTimeResponseDto> rentalTimes;
}
