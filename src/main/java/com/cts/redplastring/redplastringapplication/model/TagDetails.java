package com.cts.redplastring.redplastringapplication.model;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Table(name = "tag")
public class TagDetails {
     @Id
     @GeneratedValue
     @Column(name = "id")
     private Integer id;

     @Column(name = "tag_name")
    private String tagName;

     @Column(name = "task")
     private String task;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "updated_date")
    private String updatedDate;

    @Column(name = "created_by")
    private String createdBy;

}
