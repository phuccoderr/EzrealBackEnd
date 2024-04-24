package com.ezreal.ops.account;

import com.ezreal.db.Postgres;
import com.ezreal.db.statement.account.AccountStmt;
import com.ezreal.model.entity.account.Account;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Optional;

@Component
public class AccountOps {
    public Optional<Account> Login(String name, String email, String avatarUrl) throws SQLException {
        var postgres = new Postgres();
        var as = new AccountStmt();
        var stmt = postgres.Connect().prepareStatement(as.Login(email, name, avatarUrl));
        var res = stmt.executeQuery();
        return Optional.of(new Account(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getTimestamp(5).toLocalDateTime(), res.getBoolean(6)));
    }
}
