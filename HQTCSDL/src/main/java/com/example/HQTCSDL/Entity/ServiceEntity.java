package com.example.HQTCSDL.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "tblService")
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    String name;
    @Column
    float price;
    @Column
    String unit;
    @Column
    String description;

    @OneToMany(mappedBy = "service")
    @JsonIgnore
    List<UsedServiceEntity> usedServices;

    @ManyToOne
    @JoinColumn(name = "userID")
    UserEntity user;
}
