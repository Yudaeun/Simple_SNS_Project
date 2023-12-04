package com.simplesns.sns.service;

import com.simplesns.sns.exception.SnsApplicationException;
import com.simplesns.sns.model.User;
import com.simplesns.sns.model.entity.UserEntity;
import com.simplesns.sns.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    //TODO: implement
    public User join(String userName, String password){

        //회원가입하려는 userName으로 회원가입된 user가 있는지 check
        userEntityRepository.findByUserName(userName).ifPresent(it->{
            throw new SnsApplicationException();
        });

        //회원가입 진행=user 등록
        UserEntity userEntity=userEntityRepository.save(UserEntity.of(userName,password));

        return User.fromEntity(userEntity);
    }

    public String login(String userName,String password){
        //login에 성공하면 jwt를 통한 토큰 반환

        //회원가입 여부 체크
        UserEntity userEntity=userEntityRepository.findByUserName(userName).orElseThrow(()->new SnsApplicationException());

        //비밀번호 체크
        if(!userEntity.getPassword().equals(password)){
            throw new SnsApplicationException();
        }

        //토큰 생성

        return "";
    }
}
