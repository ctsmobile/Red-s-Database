package com.cts.redplastring.redplastringapplication.request;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class TimeTrackingRequest {
    private String employeeId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date clockIn;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date clockOut;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date breakIn;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date breakOut;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
}
