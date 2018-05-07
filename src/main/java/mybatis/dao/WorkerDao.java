package mybatis.dao;

import bean.Worker;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.Date;
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

    public List<Worker> getWorkersByBirthday(Date birthday){
        return sqlSession.selectList("getWorkersByBirthday", birthday);
    }
    public List<Worker> getWorkersByFirstName(String firstName){
        return sqlSession.selectList("getWorkersByFirstName", firstName);
    }

    public void insertWorker(String firstName, String lastName, String middleName, Boolean sex, Date birthday,  String phoneNumber,
                      String email, Date employmentDate, Date dateOfDismissal, String position, Integer salary, Boolean headDepartment,
                      String nameDepartment){

        Map<String, Object> map = new HashMap<>();
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("middleName", middleName);
        map.put("sex", sex);
        map.put("birthday", birthday);
        map.put("phoneNumber", phoneNumber);
        map.put("email", email);
        map.put("employmentDate", employmentDate);
        map.put("dateOfDismissal", dateOfDismissal);
        map.put("position", position);
        map.put("salary", salary);
        map.put("headDepartment", headDepartment);
        map.put("nameDepartment", nameDepartment);

        sqlSession.insert("insertWorker", map);

    }

    public void updateWorker (String oldFirstName, String oldLastName,  String firstName, String lastName, String middleName,
                       Boolean sex, Date birthday,  String phoneNumber, String email, Date employmentDate, Date dateOfDismissal,
                       String position, Integer salary, Boolean headDepartment, String nameDepartment){

        Map<String, Object> map = new HashMap<>();

        map.put("oldFirstName", firstName);
        map.put("oldLastName", lastName);
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("middleName", middleName);
        map.put("sex", sex);
        map.put("birthday", birthday);
        map.put("phoneNumber", phoneNumber);
        map.put("email", email);
        map.put("employmentDate", employmentDate);
        map.put("dateOfDismissal", dateOfDismissal);
        map.put("position", position);
        map.put("salary", salary);
        map.put("headDepartment", headDepartment);
        map.put("nameDepartment", nameDepartment);

        sqlSession.update("updateWorker", map);


    }


    void updateDismissalWorker(String firstName, String lastName, Date dateOfDismissal){
        Map<String, Object> map = new HashMap<>();


        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("dateOfDismissal", dateOfDismissal);

        sqlSession.update("updateDismissalWorker", map);
    }

    void updateTransferWorker(String firstName, String lastName, String nameDepartment){
        Map<String, Object> map = new HashMap<>();

        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("nameDepartment", nameDepartment);
        sqlSession.update("updateTransferWorker", map);
    }

    public Worker getHeadByFirstNameAndLastNameWorker(String firstName, String lastName){
        Map<String, Object> map = new HashMap<>();

        map.put("firstName", firstName);
        map.put("lastName", lastName);

        return sqlSession.selectOne("getHeadByFirstNameAndLastNameWorker", map);
    }

    public void deleteWorker(String firstName, String lastName){
        Map<String, Object> map = new HashMap<>();

        map.put("firstName", firstName);
        map.put("lastName", lastName);

        sqlSession.delete("deleteWorker", map);
    }

    void updateNewDepartment(String oldDepartment, String newDepartment){
        Map<String, Object> map = new HashMap<>();

        map.put("oldDepartment", oldDepartment);
        map.put("newDepartment", newDepartment);

        sqlSession.update("updateNewDepartment", map);
    }

    void updateHeadDepartment(String firstName, String lastName){

        Map<String, Object> map = new HashMap<>();

        map.put("firstName", firstName);
        map.put("lastName", lastName);

        sqlSession.update("deleteWorker", map);

    }




}
