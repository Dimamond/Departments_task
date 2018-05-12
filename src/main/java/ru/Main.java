package ru;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.mybatis.mapper.DepartmentMapper;




@SpringBootApplication
@ComponentScan
@MapperScan(basePackages = "ru.mybatis.mapper")
public class Main implements CommandLineRunner {



	public static void main(String[] args)  {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
