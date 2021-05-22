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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle_movement")
public class VehicleMovement implements Serializable {
    private static final long serialVersionUID = 102L;

    @Id
    @Column(name = "vehicle_movement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Camera.class)
    @JoinColumn(name = "camera_id")
    private Camera camera;

    @ManyToOne(targetEntity = Location.class)
    @JoinColumn(name = "location_id")
    private Location location;

    @JsonManagedReference
    @ManyToOne(targetEntity = Vehicle.class)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;


    @JsonProperty("vehicle_captured_date_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "captured_time")
    private Date datetime = new Date();


    public VehicleMovement(Camera camera, Location location,Vehicle vehicle) {
        this.camera = camera;
        this.location = location;
        this.vehicle = vehicle;
    }
}
