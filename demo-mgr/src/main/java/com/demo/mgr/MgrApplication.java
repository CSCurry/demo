package com.demo.mgr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@EnableAsync
//扫描Mapper接口文件 **表示有n个包 *表示1个包
@MapperScan(value = "com.demo.**.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.demo.*"})
public class MgrApplication {

    public static void main(String[] args) {
        SpringApplication.run(MgrApplication.class, args);
        System.out.println(" :: Mgr Start SUCCESS :: ");
    }
}