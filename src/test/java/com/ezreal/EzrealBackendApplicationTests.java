package com.ezreal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
class EzrealBackendApplicationTests {

    @Test
    void contextLoads() throws ParseException {
        var d = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(d.parse(LocalDate.now().toString()));
    }

}
