package com.example.HQTCSDL.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tblUser")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    String username;
    @Column
    String password;
    @Column
    String fullName;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date dateOfBirth;
    @Column
    String phone;
    @Column
    String email;
    @Column
    String position;
    @OneToMany(mappedBy = "financeManager", cascade = CascadeType.ALL)
    List<BillEntity> bills;

    @OneToMany(mappedBy = "user")
    List<ServiceEntity> services;

    @OneToMany(mappedBy = "user")
    List<ResidentEntity> residents;

    @OneToMany(mappedBy = "user")
    List<RoomEntity> rooms;

    @OneToMany(mappedBy = "user")
    List<BuildingEntity> buildings;
}
