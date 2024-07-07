package com.trafficsns.trafficsns.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    private Integer id;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

}
