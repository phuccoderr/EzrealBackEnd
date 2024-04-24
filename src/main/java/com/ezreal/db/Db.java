package com.ezreal.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface Db {
    Connection Connect() throws SQLException;
}
