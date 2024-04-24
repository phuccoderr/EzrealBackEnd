package com.ezreal.db;

import com.ezreal.config.Dotenv;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class Postgres implements Db{

    @Override
    public Connection Connect() throws SQLException {
        return DriverManager.getConnection(Dotenv.getProperty("POSTGRES_URL"));
    }
}
