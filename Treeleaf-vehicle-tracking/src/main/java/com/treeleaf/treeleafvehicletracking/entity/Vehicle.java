package com.treeleaf.treeleafvehicletracking.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 100L;

    @Id
    @Column(name = "vehicle_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("vehicle_name")
    @Column(name = "vehicle_name")
    private String vehicleName;

    @JsonProperty("vehicle_number")
    @Column(name = "vehicle_number",unique = true)
    private String vehicleNumber;

    @JsonProperty("vehicle_type")
    @Column(name = "vehicle_type")
    private String vehicleType;

    @JsonProperty("vehicle_added_date_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "added_date_time")
    private Date adedDateTime = new Date();

    @JsonBackReference
    @JsonProperty("vehicle_movements")
    @OneToMany(mappedBy = "vehicle",targetEntity = VehicleMovement.class)
    private List<VehicleMovement> vehicleMovements;

    public Vehicle(String vehicleName, String vehicleNumber, String vehicleType) {
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }
}
