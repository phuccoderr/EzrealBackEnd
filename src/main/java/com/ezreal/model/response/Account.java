package com.ezreal.model.response;

public class Account {
    private String accessToken;
    private AccountInfo info;

    public Account() {
    }

    public Account(String accessToken, AccountInfo info) {
        this.accessToken = accessToken;
        this.info = info;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AccountInfo getInfo() {
        return info;
    }

    public void setInfo(AccountInfo info) {
        this.info = info;
    }
}
