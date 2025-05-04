package com.example.HQTCSDL.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@Entity
@Table(name = "tblRentalTime")
@ToString(exclude = "room")
public class RentalTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date startDate;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date endDate;

    @ManyToOne
    @JoinColumn(name = "roomID")
    @JsonBackReference
    RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "residentID")
    ResidentEntity resident;
}
