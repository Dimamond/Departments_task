package ru.mybatis.mapper;


import ru.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.bean.SalaryFund;

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

    List<Department> getAllDepartments();

    List<SalaryFund> getAllSalaryFund();

    void insertSalaryFund(@Param("nameDepartment")String nameDepartment, @Param("salary")Integer salary);
    void updateSalaryFund(@Param("nameDepartment")String nameDepartment, @Param("salary")Integer salary);
    void deleteSalaryFund(Long department_id);
    int getSalaryFromSalaryFundTableByDepartmentId(Long departmentId);
    SalaryFund getSalaryFundFromSalaryFundTableByDepartmentName(String departmentName);

}
