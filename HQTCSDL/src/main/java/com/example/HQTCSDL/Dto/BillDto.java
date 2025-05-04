package com.example.HQTCSDL.Dto;

import com.example.HQTCSDL.Entity.RoomEntity;
import com.example.HQTCSDL.Entity.UsedServiceEntity;
import com.example.HQTCSDL.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class BillDto {
    Integer id;
    String name;
    float paymentAmount;
    Date paymentDate;
    String paymentType;
    String note;
    RoomEntity room;
    UserEntity financeManager;
    UsedServiceEntity usedService;
}
