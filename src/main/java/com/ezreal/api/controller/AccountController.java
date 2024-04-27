package com.ezreal.api.controller;

import com.ezreal.api.handler.AccountHandler;
import com.ezreal.model.response.Account;
import com.ezreal.model.response.AccountInfo;
import com.ezreal.service.AccountService;
import com.ezreal.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class AccountController {
    @Autowired private AccountService AccountService;
    @Autowired private JwtService jwtService;

    @GetMapping("/user/account/login/google")
    public void login(HttpServletResponse response) throws IOException {
         response.sendRedirect(AccountHandler.Login());
    }

    @GetMapping("/user/account/login/callback/google")
    public ResponseEntity<?> callback(String code) throws SQLException {
        String accessToken = AccountHandler.getAccessToken(code);

        String userInfoUrl = "https://www.googleapis.com/oauth2/v3/userinfo";
        //Config Header
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        // GET USER INFO GOOGLE
        ResponseEntity<AccountInfo> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, entity,AccountInfo.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            AccountInfo accountInfo = response.getBody();
            AccountInfo info = AccountService.getUser(accountInfo);

            String token = jwtService.generateToken(info);

            Account account = new Account(token,info);

            return ResponseEntity.ok().body(account);
        } else {
            return ResponseEntity.status(response.getStatusCode()).body("Error accessing user info");
        }
    }

    @GetMapping("/auth/helloworld")
    public ResponseEntity<?> testAuth(@RequestHeader(value = "Authorization",required = false) String token) {
        boolean checkedToken = jwtService.checkToken(token);
        if (!checkedToken) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is not valid!");
        }
        return ResponseEntity.ok("vcc");
    }


}
