package com.simplesns.sns.controller;

import com.simplesns.sns.controller.request.UserJoinRequest;
import com.simplesns.sns.controller.response.Response;
import com.simplesns.sns.controller.response.UserJoinResponse;
import com.simplesns.sns.model.User;
import com.simplesns.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    //TODO: implement
    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request){
        User user=userService.join(request.getUserName(), request.getPassword());
        UserJoinResponse response=UserJoinResponse.fromUser(user);

        return Response.success(UserJoinResponse.fromUser(user));
    }
}
