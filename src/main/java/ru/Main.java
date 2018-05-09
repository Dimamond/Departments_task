package ru;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bean.Department;
import ru.bean.Worker;
import ru.mybatis.mapper.DepartmentMapper;
import ru.mybatis.mapper.WorkerMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.service.Service;


@SpringBootApplication
@ComponentScan
public class Main implements CommandLineRunner {
	@Autowired
	private Service service;





	public static void main(String[] args)  {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
