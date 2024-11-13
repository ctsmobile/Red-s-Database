package com.cts.redplastring.redplastringapplication.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name = "time_tracking")
public class TimeTracking {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "job_id")
    private Integer jobId;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "clock_in")
    private String clockIn;

    @Column(name="clock_in_location")
    private Integer clockInLocation;

    @Column(name = "clock_out")
    private String clockOut;

    @Column(name="clock_out_location")
    private Integer clockOutLocation;

    @Column(name = "break_in")
    private String breakIn;

    @Column(name = "break_out")
    private String breakOut;

    @Column(name = "travel_in")
    private String travelIn;

    @Column(name = "break_in_Location")
    private Integer breakInLocation;

    @Column(name = "travel_out")
    private String travelOut;

    @Column(name = "break_out_Location")
    private Integer breakOutLocation;

    @Column(name = "employee_state")
    private String employeeState;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "updated_date")
    private String updatedDate;


    @Column(name="reminder_40")
    private boolean reminder40 = false;


    @Column(name="reminder_50")
    private boolean reminder50 = false;
}
