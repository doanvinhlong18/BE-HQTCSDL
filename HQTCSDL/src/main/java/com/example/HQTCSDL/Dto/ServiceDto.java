package com.example.HQTCSDL.Dto;

import com.example.HQTCSDL.Entity.UsedServiceEntity;
import com.example.HQTCSDL.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class ServiceDto {
    int id;
    String name;
    float price;
    String unit;
    String description;
    List<UsedServiceEntity> usedServices;
    UserEntity user;
}
