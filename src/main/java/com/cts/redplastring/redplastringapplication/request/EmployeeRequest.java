package com.cts.redplastring.redplastringapplication.request;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class EmployeeRequest {
    private Integer id;
    private String notes;
    private String userId;
    private String userType;
    private String employeeId;
    private String deviceId;
    private String employeeName;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String position;
    private String email;
    private String tags;
    private String foreman;
    private Double rateOfPay;
    private String username;
    private String password;
    private String fcmKey;
    private String createdBy;

    private Integer notificationId;
}
