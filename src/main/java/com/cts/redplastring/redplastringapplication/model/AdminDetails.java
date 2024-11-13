package com.cts.redplastring.redplastringapplication.model;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "admin_details")
public class AdminDetails {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="user_id")
    private String userId;

    @Column(name="email")
    private String email;

    @Column(name="mobile")
    private String mobile;

    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

    @Column(name="profile_photo")
    private String profilePhoto;

    @Column(name="created_date")
    private String createdDate;

    @Column(name="updated_date")
    private String updatedDate;
}
