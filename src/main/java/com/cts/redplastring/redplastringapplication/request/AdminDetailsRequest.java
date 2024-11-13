package com.cts.redplastring.redplastringapplication.request;

import com.cts.redplastring.redplastringapplication.exception.ValidationExceptionCodesConstants;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class AdminDetailsRequest {
    private String userId;
    private String email;
    private String password;
    private String name;
    private String profilePhoto;
    private String mobile;
}
