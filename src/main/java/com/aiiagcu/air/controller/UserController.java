package com.aiiagcu.air.controller;

import com.aiiagcu.air.dto.*;
import com.aiiagcu.air.entity.User;
import com.aiiagcu.air.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public void signup(@RequestBody @Validated SignupInput signupInput) {
        userService.signup(signupInput);
    }

    @PostMapping("/user/freshman")
    public void freshmanSignup(@RequestBody @Validated FreshmanSignupInput signupInput) {
        userService.freshmanSignup(signupInput);
    }

    @PostMapping("/user/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Validated LoginInput loginInput, HttpServletRequest servletRequest) {
        return ResponseEntity.ok(userService.login(loginInput, servletRequest));
    }

    @DeleteMapping("/user")
    public void email(@SessionAttribute User loginUser) {
        userService.removeUser(loginUser);
    }
}
