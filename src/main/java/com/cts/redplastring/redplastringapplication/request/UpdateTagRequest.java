package com.cts.redplastring.redplastringapplication.request;

import com.cts.redplastring.redplastringapplication.constant.ValidationExceptionFEMessageConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UpdateTagRequest {
    @NotNull(message = ValidationExceptionFEMessageConstant.TAG_NAME)
    private String tagName;
    @NotNull(message = ValidationExceptionFEMessageConstant.TASK)
    private String task;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
}
