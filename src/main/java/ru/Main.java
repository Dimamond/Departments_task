package ru;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.bean.SalaryFund;
import ru.mybatis.mapper.DepartmentMapper;

import java.util.List;


@SpringBootApplication
@ComponentScan
@MapperScan(basePackages = "ru.mybatis.mapper")
public class Main implements CommandLineRunner {

	private final DepartmentMapper departmentMapper;

	public Main(DepartmentMapper departmentMapper) {
		this.departmentMapper = departmentMapper;
	}

	public static void main(String[] args)  {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}


}
