package com.cts.redplastring.redplastringapplication.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "config_details")
public class ConfigDetails {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private String value;
}
