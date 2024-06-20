package com.trafficsns.trafficsns.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trafficsns.trafficsns.controller.request.UserJoinRequest;
import com.trafficsns.trafficsns.controller.request.UserLoginRequest;
import com.trafficsns.trafficsns.exception.SnsApplicationException;
import com.trafficsns.trafficsns.model.User;
import com.trafficsns.trafficsns.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void 회원가입() throws Exception {
        String userName="userName";
        String password="password";

        when(userService.join(userName,password)).thenReturn(mock(User.class));

        mockMvc.perform(post("/api/v1/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName,password)))
        ).andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void 회원가입시_이미_회원가입된_userName으로_회원가입_하면_에러반환() throws Exception {
        String userName="userName";
        String password="password";

        when(userService.join(userName,password)).thenThrow(new RuntimeException());

        mockMvc.perform(post("/api/v1/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName,password)))
                )
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void 로그인() throws Exception{
        String userName="userName";
        String password="password";

        when(userService.login()).thenReturn("test_token");

        mockMvc.perform(post("/api/v1/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName,password)))
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 로그인시_회원가입이_안된_userName_입력시_에러반환() throws Exception{
        String userName="userName";
        String password="password";

        when(userService.login()).thenReturn(new SnsApplicationException());

        mockMvc.perform(post("/api/v1/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName,password)))
        ).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void 로그인시_틀린_password를_입력하면_에러반환() throws Exception{
        String userName="userName";
        String password="password";

        when(userService.login()).thenReturn(new SnsApplicationException());

        mockMvc.perform(post("/api/v1/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName,password)))
        ).andDo(print())
                .andExpect(status().isUnauthorized());
    }
    
}
