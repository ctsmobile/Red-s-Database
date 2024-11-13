package com.cts.redplastring.redplastringapplication.request;

import com.cts.redplastring.redplastringapplication.constant.ValidationExceptionFEMessageConstant;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UpdateLocationRequest {
    @NotNull(message = ValidationExceptionFEMessageConstant.ADDRESS)
    private String address;
    @NotNull(message = ValidationExceptionFEMessageConstant.RANGE)
    private Double rangeVale;
    @NotNull(message = ValidationExceptionFEMessageConstant.JOB_SIDE)
    private String jobSide;
    @NotNull(message = ValidationExceptionFEMessageConstant.EMPTY_LATITUDE)
    private String latitude;
    @NotNull(message = ValidationExceptionFEMessageConstant.EMPTY_LONGITUDE)
    private String longitude;
    private String shiftStartTime;
    private String shiftEndTime;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    private Integer projectNumber;
    private String projectName;
}
