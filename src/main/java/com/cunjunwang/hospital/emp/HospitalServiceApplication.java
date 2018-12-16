package com.cunjunwang.hospital.emp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by CunjunWang on 2018/11/25.
 */
@SpringBootApplication
@MapperScan("com.cunjunwang.hospital.emp.dao")
public class HospitalServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospitalServiceApplication.class, args);
    }
}
