package com.example.HQTCSDL.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tblUsedService")
public class UsedServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date startDateTime;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date endDateTime;

    @Column
    String status;

    @Column
    Integer quantity;
    @OneToMany(mappedBy = "usedService")
    List<BillEntity> bills;

    @ManyToOne
    @JoinColumn(name = "serviceID")
    ServiceEntity service;

    @ManyToOne
    @JoinColumn(name = "residentID")
    @JsonIgnore
    ResidentEntity resident;
}
