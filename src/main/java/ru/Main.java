package ru;

import ru.bean.Department;
import ru.bean.Worker;
import ru.mybatis.mapper.DepartmentMapper;
import ru.mybatis.mapper.WorkerMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;


@SpringBootApplication
@MapperScan(basePackages = "ru.mybatis.mapper")
@ComponentScan
public class Main implements CommandLineRunner {

	private final DepartmentMapper departmentMapper;
	private final WorkerMapper workerMapper;

	public Main(DepartmentMapper departmentMapper, WorkerMapper workerMapper) {
		this.departmentMapper = departmentMapper;
		this.workerMapper = workerMapper;
	}



	public static void main(String[] args)  {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {




		//workerMapper.updateWorker("ПетрII", "ПорошенкоII", "Петр", "Порошенко",
		//		null, true, new Date(), "7777", "11@mail.ru", new Date(), null,
		//		"Повар", 10000, true,"Отдел обслуживания");
	}
}
