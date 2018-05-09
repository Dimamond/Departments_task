package ru.mybatis.dao;

import ru.bean.Department;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



}
