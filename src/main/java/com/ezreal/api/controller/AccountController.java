package com.ezreal.api.controller;

import com.ezreal.api.handler.AccountHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @GetMapping("/user/account/login/google")
    public ResponseEntity<?> Login(HttpServletRequest request) {
        var ah = new AccountHandler();
        return ah.Login();
    }
    @GetMapping("/user/account/login/callback/google")
    public ResponseEntity<?> LoginCallback(HttpServletRequest request) {
        var ah = new AccountHandler();
        return ah.LoginCallback(request);
    }
}
