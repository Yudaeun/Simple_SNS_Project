package com.simplesns.sns.model.entity;

import com.simplesns.sns.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name="\"user\"")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@SQLDelete(sql="UPDATED user SET deleted_at=NOW() where id=?")
@Where(clause="deleted_at is NULL")
public class UserEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private UserRole role=UserRole.USER;

    @Column(name="registered_at")
    private Timestamp registeredAt;

    @Column(name="updated_at")
    private Timestamp updatedAt;

    @Column(name="deleted_at")
    private Timestamp deletedAt; // Soft_delete

    @PrePersist
    void registeredAt(){
        this.registeredAt=Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt(){
        this.updatedAt=Timestamp.from(Instant.now());
    }

    public static UserEntity of(String userName,String password){
        UserEntity userEntity=new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setPassword(password);

        return userEntity;
    }
}
