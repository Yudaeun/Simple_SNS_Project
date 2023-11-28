package com.simplesns.sns.service;

import com.simplesns.sns.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //TODO: implement
    public User join(){
        return new User();
    }

    public String login(){
        //login에 성공하면 jwt를 통한 토큰 반환
        return "";
    }
}
