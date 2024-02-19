package com.simplesns.sns.service;

import com.simplesns.sns.exception.ErrorCode;
import com.simplesns.sns.exception.SnsApplicationException;
import com.simplesns.sns.model.User;
import com.simplesns.sns.model.entity.UserEntity;
import com.simplesns.sns.repository.UserEntityRepository;
import com.simplesns.sns.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserEntityRepository userEntityRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private long expiredTimeMs;

    public User loadUserByUserName(String userName){
        return userEntityRepository.findByUserName(userName).map(User::fromEntity).orElseThrow(()->
                new SnsApplicationException(ErrorCode.USER_NOT_FOUND,String.format("%s not founded",userName)));
    }


    @Transactional
    public User join(String userName, String password){

        //회원가입하려는 userName으로 회원가입된 user가 있는지 check
        userEntityRepository.findByUserName(userName).ifPresent(it->{
            throw new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME,
                    String.format("%s is duplicated",userName));
        });

        //회원가입 진행=user 등록
        UserEntity userEntity=userEntityRepository.save(UserEntity.of(userName,encoder.encode(password)));

        return User.fromEntity(userEntity);
    }

    public String login(String userName,String password){
        //login에 성공하면 jwt를 통한 토큰 반환

        //회원가입 여부 체크
        UserEntity userEntity=userEntityRepository.findByUserName(userName)
                .orElseThrow(()->new SnsApplicationException(ErrorCode.USER_NOT_FOUND,String.format("%s not found",userName)));

        //비밀번호 체크
        if(!encoder.matches(password,userEntity.getPassword())){
//        if(!userEntity.getPassword().equals(password)){
            throw new SnsApplicationException(ErrorCode.INVALID_PASSWORD,"");
        }

        //토큰 생성
        String token=JwtTokenUtils.generateToken(userName,secretKey,expiredTimeMs);

        return token;
    }
}
