package com.ezreal;

import com.ezreal.db.Postgres;
import com.ezreal.db.statement.DefStmt;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
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

	@Bean(name = "dotenv")
	public Dotenv loadEnv() {
		return Dotenv.load();
	}

}
