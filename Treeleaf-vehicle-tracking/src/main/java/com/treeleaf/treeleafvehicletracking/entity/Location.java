package com.treeleaf.treeleafvehicletracking.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "location")
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("location_name")
    @Column(name = "location_name")
    private String locationName;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="camera_location",
            joinColumns=@JoinColumn(name="location_id"),
            inverseJoinColumns=@JoinColumn(name="camera_id")
    )
    private List<Camera> cameras;

    public Location(String locationName, String state,String country) {
        this.locationName = locationName;
        this.state = state;
        this.country = country;
    }

    public void addCamera(Camera camera){
        if(cameras == null) cameras = new ArrayList<>();

        cameras.add(camera);
    }
}



