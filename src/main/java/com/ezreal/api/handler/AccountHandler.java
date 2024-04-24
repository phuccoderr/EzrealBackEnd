package com.ezreal.api.handler;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriBuilder;

import java.net.URI;

@Component
public class AccountHandler {
    @Autowired
    Dotenv dotenv;

    public ResponseEntity<?> Login() {
        return ResponseEntity.status(HttpStatus.SEE_OTHER).location(URI.create(dotenv.get("GG_PAGE_LOGIN_URL"))).build();
    }

    public ResponseEntity<?> LoginCallback(HttpServletRequest request) {
       try{

       } catch (Exception e) {

       }
    }
}
