package com.example.HQTCSDL.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tblBuilding")
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    String name;
    @Column
    String address;
    @Column
    int floorCount;
    @Column
    int totalRooms;
    @Column
    String status;

    @OneToMany(mappedBy = "building")
    @JsonManagedReference
    List<RoomEntity> rooms;

    @ManyToOne
    @JoinColumn(name = "userID")
    UserEntity user;
}
