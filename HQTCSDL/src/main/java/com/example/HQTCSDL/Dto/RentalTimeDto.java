package com.example.HQTCSDL.Dto;

import com.example.HQTCSDL.Entity.ResidentEntity;
import com.example.HQTCSDL.Entity.RoomEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RentalTimeDto {
    int id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date endTime;
    RoomEntity room;
    ResidentDto resident;
}
