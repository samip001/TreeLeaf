package com.treeleaf.treeleafvehicletracking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "camera")
public class Camera implements Serializable {

    private static final long serialVersionUID = 102L;

    @Id
    @Column(name = "camera_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("camera_name")
    @Column(name = "camera_name")
    private String cameraName;

    @JsonProperty("camera_model")
    @Column(name = "camera_model")
    private String cameraModel;

    @JsonProperty("camera_type")
    @Column(name = "camera_type")
    private String cameraType;


    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="camera_location",
            joinColumns=@JoinColumn(name="camera_id"),
            inverseJoinColumns=@JoinColumn(name="location_id")
    )
    private List<Location> locations;

    public Camera(String cameraName, String cameraModel, String cameraType) {
        this.cameraName = cameraName;
        this.cameraModel = cameraModel;
        this.cameraType = cameraType;
    }

    public void addLocation(Location location){
        if(locations == null) locations = new ArrayList<>();

        locations.add(location);
    }
}
