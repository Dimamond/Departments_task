

import bean.Department;
import bean.Worker;
import mybatis.mapper.DepartmentMapper;
import mybatis.mapper.WorkerMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@MapperScan(basePackages = "mybatis.mapper")
@ComponentScan("controller")
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
		System.out.println("Hello");

		 workerMapper.updateTransferWorker("ПетрII","ПорошенкоII", "Головной отдел");



	}
}
