package cn.edu.guet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.guet.mapper")
public class HrMsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrMsystemApplication.class, args);
    }

}
