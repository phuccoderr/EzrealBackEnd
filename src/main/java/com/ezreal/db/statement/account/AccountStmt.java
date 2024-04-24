package com.ezreal.db.statement.account;

public class AccountStmt {
    public String Login(String email, String name, String avatarUrl) {
        return String.format("select * from account.login('%s', '%s', '%s');", email, name, avatarUrl);
    }
}
