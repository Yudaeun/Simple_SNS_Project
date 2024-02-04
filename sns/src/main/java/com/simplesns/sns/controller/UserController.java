package com.simplesns.sns.controller;

import com.simplesns.sns.controller.request.UserJoinRequest;
import com.simplesns.sns.controller.request.UserLoginRequest;
import com.simplesns.sns.controller.response.Response;
import com.simplesns.sns.controller.response.UserJoinResponse;
import com.simplesns.sns.controller.response.UserLoginResponse;
import com.simplesns.sns.model.User;
import com.simplesns.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request){
        User user=userService.join(request.getUserName(), request.getPassword());
        UserJoinResponse response=UserJoinResponse.fromUser(user);

        return Response.success(response);
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest request){
        String token=userService.login(request.getUserName(),request.getPassword());

        return Response.success(new UserLoginResponse(token));
    }
}
