package com.trafficsns.trafficsns.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class UserEntity {
    @Id
    private Integer id;

    @Column(name="user_name")
    private String userName;


}
