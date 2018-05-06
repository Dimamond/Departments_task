package mybatis.mapper;

import bean.Department;
import bean.Worker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorkerMapper {
    Worker getWorkerByFirstNameAndLastName(@Param("firstName")String firstName, @Param("lastName")String lastName);
    List<Worker> getWorkersDepartmentByDepartmentName(String departmentName);
    Worker getHeadDepartmentByDepartmentName(String departmentName);
    List<Worker> getAllWorkers();
    Integer getSumSalaryWorkersDepartmentByDepartmentName(String departmentName);

}
