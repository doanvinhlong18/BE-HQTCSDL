package com.example.HQTCSDL.Dto;

import com.example.HQTCSDL.Entity.BillEntity;
import com.example.HQTCSDL.Entity.ResidentEntity;
import com.example.HQTCSDL.Entity.ServiceEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UsedServiceDto {
    int id;
    Date startDateTime;
    Date endDateTime;
    String status;
    Integer quantity;
    List<BillEntity> bills;
    ServiceEntity service;
    ResidentEntity resident;
}
