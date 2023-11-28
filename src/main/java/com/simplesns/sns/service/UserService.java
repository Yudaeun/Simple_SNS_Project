package com.simplesns.sns.service;

import com.simplesns.sns.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User join(){
        return new User();
    }

}
