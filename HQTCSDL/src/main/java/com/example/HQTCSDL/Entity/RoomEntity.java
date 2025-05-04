package com.example.HQTCSDL.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tblRoom")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    String name;
    @Column
    String type;
    @Column
    float area;
    @Column
    float rentPrice;
    @Column
    String rentStatus;

    @OneToMany(mappedBy = "room")
    List<BillEntity> bills;

    @OneToMany(mappedBy = "room")
    @JsonManagedReference
    List<RentalTimeEntity> rentalTimes;

    @ManyToOne
    @JoinColumn(name = "buildingID")
    @JsonBackReference
    BuildingEntity building;

    @ManyToOne
    @JoinColumn(name = "userID")
    UserEntity user;
}
