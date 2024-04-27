package com.ezreal.api.handler;


import com.ezreal.config.Dotenv;
import com.ezreal.model.response.AccountInfo;
import com.ezreal.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class AccountHandler {
    private static final String  clientId = Dotenv.getProperty("GG_CLIENT_ID");
    private static final String  clientSecret = Dotenv.getProperty("GG_CLIENT_SECRET");
    private static final String redirectUrlGoogle = Dotenv.getProperty("GG_LOCAL_CALLBACK");
    private static final String scope = "openid email profile";

    private static JwtService jwtService;


    public static final String Login() {

        URI loginURI = UriComponentsBuilder.fromHttpUrl("https://accounts.google.com/o/oauth2/v2/auth")
                .queryParam("client_id",clientId)
                .queryParam("response_type", "code")
                .queryParam("redirect_uri",redirectUrlGoogle)
                .queryParam("scope",scope)
                .build().toUri();
        return loginURI.toString();
    }

    public static final String getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        String tokenURL = "https://accounts.google.com/o/oauth2/token";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("code",code);
        requestBody.put("client_id",clientId);
        requestBody.put("client_secret",clientSecret);
        requestBody.put("redirect_uri",redirectUrlGoogle);
        requestBody.put("grant_type",  "authorization_code");

        //POST with param code
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(tokenURL,requestBody, Map.class);
        Map<String, String> responseBody = responseEntity.getBody();

        return responseBody != null ? responseBody.get("access_token") : null;
    }




}
