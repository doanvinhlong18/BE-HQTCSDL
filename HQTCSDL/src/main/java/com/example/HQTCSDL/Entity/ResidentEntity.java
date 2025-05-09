package com.example.HQTCSDL.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "tblResident")
@ToString(exclude = "rentalTimes")
public class ResidentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    String name;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date dateOfBirth;
    @Column
    String idNumber;
    @Column
    String phone;
    @Column
    String email;

    @OneToMany(mappedBy = "resident")
    List<UsedServiceEntity> usedServices;


    @OneToMany(mappedBy = "resident")
    List<RentalTimeEntity> rentalTimes;
    @JsonProperty("rentalTimes")
    public List<Integer> getAllHopDongIds() {
        return rentalTimes != null
                ? rentalTimes.stream().map(RentalTimeEntity::getId).collect(Collectors.toList())
                : Collections.emptyList();
    }
    @ManyToOne
    @JoinColumn(name = "userID")
    UserEntity user;
}
