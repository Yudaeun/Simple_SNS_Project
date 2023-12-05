package com.simplesns.sns.model;


import com.simplesns.sns.model.entity.UserEntity;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class User {

    private String userName;
    private String password;
    private Integer id;
    private UserRole userRole;
    private Timestamp registeredAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    public User(Integer id, String userName, String password, UserRole role, Timestamp registeredAt, Timestamp updatedAt, Timestamp deletedAt) {
    }

    public static User fromEntity(UserEntity entity){
        return new User(
                entity.getId(),
                entity.getUserName(),
                entity.getPassword(),
                entity.getRole(),
                entity.getRegisteredAt(),
                entity.getUpdatedAt(),
                entity.getDeletedAt()
        );
    }
}
