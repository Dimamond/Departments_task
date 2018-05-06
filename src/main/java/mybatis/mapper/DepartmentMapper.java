package mybatis.mapper;

import bean.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    Department getDepartmentByName(String name);
    List<Department> getParentDepartmentsByName(String name);
    List<Department> getAllDepartments();
}
