package ru.mybatis.dao;

import org.apache.ibatis.session.ResultHandler;
import ru.bean.Department;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import ru.bean.SalaryFund;


import java.util.*;

@Component
public class DepartmentDao {



    private final SqlSession sqlSession;

    public DepartmentDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }




    public void insertDepartment(String name, Date dateOfCreation, String nameParent){

        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("dateOfCreation", dateOfCreation);
        map.put("nameParent", nameParent);
        sqlSession.insert("insertDepartment", map);
    }

    public void updateNameDepartment(Long id, String newName){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("newName", newName);
        sqlSession.insert("updateNameDepartment", map);
    }

    public void deleteDepartment(Long id){
        sqlSession.delete("deleteDepartment", id);
    }

    public Department getDepartmentById(Long id){
        return sqlSession.selectOne("getDepartmentById", id);
    }

    public List<Department> getChildDepartmentsById(Long id){
        return sqlSession.selectList("getChildDepartmentsById", id);
    }

    public List<Department> getAllChildDepartmentsById(Long id){
        return sqlSession.selectList("getAllChildDepartmentsById", id);
    }

    void updateParentDepartment(Long id, String parent){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("parent", parent);
        sqlSession.insert("updateParentDepartment", map);


    }

    public List<Department> getParentDepartmentsById(Long id){
        return sqlSession.selectList("getParentDepartmentsById", id);
    }

    public Department getDepartmentByName(String name){
        return sqlSession.selectOne("getDepartmentByName", name);
    }


    public List<Department> getAllDepartments(){
        return sqlSession.selectList("getAllDepartments");
    }

    public List<SalaryFund> getAllSalaryFund(){
        return sqlSession.selectList("getAllSalaryFund");
    }


    public void insertSalaryFund(String nameDepartment, Integer salary){
        Map<String, Object> map = new HashMap<>();
        map.put("nameDepartment", nameDepartment);
        map.put("salary", salary);
        sqlSession.insert("insertSalaryFund", map);
    }
    public void updateSalaryFund(String nameDepartment, Integer salary){
        Map<String, Object> map = new HashMap<>();
        map.put("nameDepartment", nameDepartment);
        map.put("salary", salary);
        sqlSession.update("updateSalaryFund", map);
    }
    public void deleteSalaryFund(Long department_id){
        sqlSession.delete("deleteSalaryFund", department_id);
    }

    public int getSalaryFromSalaryFundTableByDepartmentId(Long departmentId){
        return sqlSession.selectOne("getSalaryFromSalaryFundTableByDepartmentId", departmentId);
    }

    public SalaryFund getSalaryFundFromSalaryFundTableByDepartmentName(String departmentName){
        return sqlSession.selectOne("getSalaryFundFromSalaryFundTableByDepartmentName", departmentName);
    }




}
