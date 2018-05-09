package ru.mybatis.mapper;

import ru.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface DepartmentMapper {


    void insertDepartment(@Param("name")String name, @Param("dateOfCreation")Date dateOfCreation, @Param("nameParent")String nameParent);
    void updateNameDepartment(@Param("id")Long id, @Param("newName")String newName);
    void deleteDepartment(Long id);
    Department getDepartmentById(Long id);
    List<Department> getChildDepartmentsById(Long id);
    List<Department> getAllChildDepartmentsById(Long id);
    void updateParentDepartment(@Param("id")Long id, @Param("parent")String parent);
    List<Department> getParentDepartmentsById(Long id);
    Department getDepartmentByName(String name);




}
