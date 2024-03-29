package com.example.FlightBooking.Models;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Time;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long id;


    @OneToOne
    private Airlines airlines;

    private Long passengers;

    private Double price;
    private String departLocation;
    private String arrivalLocation;

    private Timestamp departTime;
    private Timestamp returnTime;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
