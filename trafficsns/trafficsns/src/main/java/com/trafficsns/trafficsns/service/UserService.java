package com.trafficsns.trafficsns.service;

import com.trafficsns.trafficsns.exception.SnsApplicationException;
import com.trafficsns.trafficsns.model.User;
import com.trafficsns.trafficsns.model.entity.UserEntity;
import com.trafficsns.trafficsns.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserEntityRepository userEntityRepository;


    public User join(String userName, String password){
        // 회원가입하려는 userName으로 회원가입된 user가 있는지
        Optional<UserEntity> userEntity=userEntityRepository.findByUserName(userName);

        // 회원가입 진행=user를 등록
        userEntityRepository.save(new UserEntity());

        return new User();
    }

    public String login(String userName, String password){
        // 회원가입 여부 체크
        UserEntity userEntity=userEntityRepository.findByUserName(userName).orElseThrow(()->new SnsApplicationException());
        // 비밀번호 체크
        if(!userEntity.getPassword().equals(password)){
            throw new SnsApplicationException();
        }

        // 토큰 생성

        return "";
    }
}
