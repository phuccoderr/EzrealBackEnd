package com.ezreal.service;

import com.ezreal.db.Postgres;
import com.ezreal.db.statement.DefStmt;
import com.ezreal.model.response.AccountInfo;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class AccountService {
    private DefStmt defStmt;
    private Postgres postgres = new Postgres();
    private PreparedStatement preStatement;
    private AccountInfo responseInfo;


    public AccountInfo getUser(AccountInfo account) throws SQLException {
        String email = account.getEmail();
        try (Connection connection = postgres.Connect()) {
            defStmt = new DefStmt();

            // Find Account
            preStatement = connection.prepareStatement(defStmt.findAccountByEmail());
            preStatement.setString(1,email);

            ResultSet resultSetFind = preStatement.executeQuery();
            // Create Account Info
            responseInfo = new AccountInfo();

            if (resultSetFind.next()) {
                responseInfo.setEmail(resultSetFind.getString("email"));
                responseInfo.setName(resultSetFind.getString("name"));
                responseInfo.setPicture(resultSetFind.getString("avatar_url"));
                responseInfo.setIs_violate(resultSetFind.getBoolean("is_violate"));
                return responseInfo;

            } else {
                // Save User If Account Not Find
                preStatement = connection.prepareStatement(defStmt.insertAccount(account));
                ResultSet resultSetSave = preStatement.executeQuery();
                responseInfo.setEmail(resultSetSave.getString("email"));
                responseInfo.setName(resultSetSave.getString("name"));
                responseInfo.setPicture(resultSetSave.getString("avatar_url"));
                responseInfo.setIs_violate(resultSetSave.getBoolean("is_violate"));

                return responseInfo;
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }


}
