package com.cts.redplastring.redplastringapplication.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "emp_location")
public class LocationInfo{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name="project_number")
    private Integer projectNumber;

    @Column(name="project_name")
    private String projectName;

    @Column(name = "address")
    private String address;

    @Column(name = "range_value")
    private Double rangeVale;

    @Column(name = "job_site")
    private String jobSide;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "shift_start_time")
    private String shiftStartTime;

    @Column(name = "shift_end_time")
    private String shiftEndTime;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "updated_date")
    private String updatedDate;

    @Column(name = "deleted")
    private Boolean deleted = false;

}
