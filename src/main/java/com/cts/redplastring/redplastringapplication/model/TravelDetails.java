package com.cts.redplastring.redplastringapplication.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "travel_details")
public class TravelDetails {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "travel_in")
    private String travelIn;

    @Column(name = "travel_in_locations")
    private Integer travelInLocations;

    @Column(name = "travel_out")
    private String travelOut;

    @Column(name = "travel_out_locations")
    private Integer travelOutLocations;

    @Column(name = "created_date")
    private String createdDate;
}
