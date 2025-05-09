package com.example.HQTCSDL.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tblBill")
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    String name;
    @Column
    float paymentAmount;
    @Column
    Date paymentDate;
    @Column
    String paymentType;
    @Column
    String note;

    @ManyToOne
    @JoinColumn(name = "roomID")
    @JsonIgnore
    RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "financeManagerID")
    UserEntity financeManager;

    @ManyToOne
    @JoinColumn(name = "usedServiceID")
    @JsonIgnore
    UsedServiceEntity usedService;
}
