package com.ezreal.db.statement;

import com.ezreal.model.response.Account;
import com.ezreal.model.response.AccountInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DefStmt {
    public String test() {
        return "select * from account.account a where a.id = 2";
    }

    public String insertAccount(AccountInfo infoAccountGoogle) {

        //convert time to string
        LocalDateTime createAt = LocalDateTime.now();
        String formatTime = String.valueOf(createAt);

        //convert boolean to string
        boolean isViolate = infoAccountGoogle.isIs_violate();
        String formatBoolean = String.valueOf(isViolate);

        return String.format("insert into account.account (email,name,avatar_url,created_at,is_violate) " +
                "values ('%s','%s','%s','%s','%s')",
                infoAccountGoogle.getEmail(),
                infoAccountGoogle.getName(),
                infoAccountGoogle.getPicture(),
                formatTime,
                formatBoolean);
    }

    public String findAccountByEmail() {
        return "SELECT * FROM account.account a WHERE a.email = ?";
    }
}
