package mybatis.dao;

import bean.Worker;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WorkerDao {

    private final SqlSession sqlSession;

    public WorkerDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


    public Worker getWorkerByFirstNameAndLastName(String firstName, String lastName){
        Map<String, Object> map = new HashMap<>();
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        return sqlSession.selectOne("getWorkerByFirstNameAndLastName", map);

    }

    public List<Worker> getWorkersDepartmentByDepartmentName(String departmentName){
        return sqlSession.selectList("getWorkersDepartmentByDepartmentName", departmentName);
    }

    public Worker getHeadDepartmentByDepartmentName(String departmentName){
        return sqlSession.selectOne("getHeadDepartmentByDepartmentName", departmentName);
    }

    public List<Worker> getAllWorkers(){
        return sqlSession.selectList("getAllWorkers");
    }

    public Integer getSumSalaryWorkersDepartmentByDepartmentName(String departmentName){
        return sqlSession.selectOne("getSumSalaryWorkersDepartmentByDepartmentName", departmentName);
    }




}
