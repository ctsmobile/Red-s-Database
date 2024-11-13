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
public class UpdateJobType {

    @NotNull(message = ValidationExceptionFEMessageConstant.JOB_NAME)
    private String jobName;
    @NotNull(message = ValidationExceptionFEMessageConstant.IMAGE_NAME)
    private String imageName;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
}
