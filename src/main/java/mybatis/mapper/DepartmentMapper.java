package mybatis.mapper;

import bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface DepartmentMapper {
    Department getDepartmentByName(String name);
    List<Department> getParentDepartmentsByName(String name);
    List<Department> getChildDepartmentsByName(String name);
    List<Department> getAllChildDepartmentsByName(String name);
    List<Department> getAllDepartments();
    void insertDepartment(@Param("name")String name, @Param("dateOfCreation")Date dateOfCreation, @Param("nameParent")String nameParent);
    void insertHeadDepartment(@Param("name")String name, @Param("dateOfCreation")Date dateOfCreation);
    void updateNameDepartment(@Param("oldName")String oldName, @Param("newName")String newName);
    void updateParentDepartment(@Param("name")String name, @Param("parent")String parent);
    void updateDeleteParentDepartment(String name);
    void deleteDepartment(String name);

}
