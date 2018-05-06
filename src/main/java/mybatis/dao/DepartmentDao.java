package mybatis.dao;

import bean.Department;
import org.apache.ibatis.annotations.Param;
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

    public Department getDepartmentByName(String name){
        return sqlSession.selectOne("getDepartmentByName", name);
    }

    List<Department> getAllDepartments(){
        return sqlSession.selectList("getAllDepartments");
    }

    public List<Department> getParentDepartmentsByName(String name){
        return sqlSession.selectList("getParentDepartmentsByName", name);
    }

    public List<Department> getChildDepartmentsByName(String name){
        return sqlSession.selectList("getChildDepartmentsByName", name);
    }

    public List<Department> getAllChildDepartmentsByName(String name){
        return sqlSession.selectList("getAllChildDepartmentsByName", name);
    }

    public void insertDepartment(String name, Date dateOfCreation, String nameParent){

        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("dateOfCreation", dateOfCreation);
        map.put("nameParent", nameParent);
        sqlSession.insert("insertDepartment", map);
    }

    public void insertHeadDepartment(String name, Date dateOfCreation){

        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("dateOfCreation", dateOfCreation);
        sqlSession.insert("insertHeadDepartment", map);
    }

    public void deleteDepartment(String name){
        sqlSession.delete("deleteDepartment", name);
    }

    public void updateNameDepartment(String oldName, String newName){
        Map<String, Object> map = new HashMap<>();
        map.put("oldName", oldName);
        map.put("newName", newName);
        sqlSession.insert("updateNameDepartment", map);
    }

    public void updateParentDepartment(String name, String parent){
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("parent", parent);
        sqlSession.insert("updateParentDepartment", map);
    }

    public void updateDeleteParentDepartment(String name){
        sqlSession.update("updateDeleteParentDepartment", name);
    }



}
