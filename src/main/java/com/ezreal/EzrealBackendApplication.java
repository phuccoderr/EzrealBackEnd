package com.ezreal;

import com.ezreal.db.Postgres;
import com.ezreal.db.statement.DefStmt;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class EzrealBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EzrealBackendApplication.class, args);
	}

	@Bean
	ApplicationRunner Runner() {
		return args -> {
			Thread.startVirtualThread(() -> {
				var postgres = new Postgres();
                try {
                    var connection = postgres.Connect();
					connection.beginRequest();
					var defStmt = new DefStmt();
					var stmt = connection.prepareStatement(defStmt.test());
					var rs = stmt.executeQuery();
					while (rs.next()) {
						System.out.println(rs.getString(1));
					}
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
		};
	}
}
