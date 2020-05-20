package com.duing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

// 声明这是springboot项目
// 这个类是主程序入口
@SpringBootApplication
@MapperScan("com.duing.mapper")
// 打开对定时任务的使用
@EnableScheduling
// 增加异步任务的开关
@EnableAsync
public class DuingPractiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuingPractiseApplication.class, args);
	}

}
