package mybatis.dao;

import bean.Department;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.List;

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

}
