package ru.mybatis.dao;

import ru.bean.Worker;
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



    public Worker getHeadByDepartmentId(Long departmentId){
        return sqlSession.selectOne("getHeadByDepartmentId", departmentId);
    }

    public Integer getCountWorkersByDepartmentId(Long departmentId){
        return sqlSession.selectOne("getCountWorkersByDepartmentId", departmentId);
    }

    public Integer getSumSalaryWorkersDepartmentByDepartmentId(Long departmentId){
        return sqlSession.selectOne("getSumSalaryWorkersDepartmentByDepartmentId", departmentId);
    }

    public List<Worker> getWorkersDepartmentByDepartmentId(Long departmentId){
        return sqlSession.selectList("getWorkersDepartmentByDepartmentId", departmentId);
    }

    public void insertWorker(String firstName, String lastName, String middleName, Boolean sex, Date birthday,
                             String phoneNumber, String email, Date employmentDate, String position, Integer salary,
                             Boolean headDepartment, Long departmentId){

        Map<String, Object> map = new HashMap<>();
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("middleName", middleName);
        map.put("sex", sex);
        map.put("birthday", birthday);
        map.put("phoneNumber", phoneNumber);
        map.put("email", email);
        map.put("employmentDate", employmentDate);
        map.put("position", position);
        map.put("salary", salary);
        map.put("headDepartment", headDepartment);
        map.put("departmentId", departmentId);

        sqlSession.insert("insertWorker", map);

    }

    public void updateWorker(Long id ,String firstName, String lastName, String middleName, Boolean sex, Date birthday,
                             String phoneNumber, String email, Date employmentDate, Date dateOfDismissal, String position,
                             Integer salary, Boolean headDepartment){

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
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


        sqlSession.update("updateWorker", map);

    }


    public void updateDismissalWorker(Long id, Date dateOfDismissal){
        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("dateOfDismissal", dateOfDismissal);

        sqlSession.update("updateDismissalWorker", map);

    }

    public void deleteWorker(Long id){
        sqlSession.delete("deleteWorker", id);
    }

    public Worker getWorkerById(Long id){
        return sqlSession.selectOne("getWorkerById", id);
    }

    public void updateTransferWorker(Long id , String nameDepartment){
        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("nameDepartment", nameDepartment);

        sqlSession.update("updateTransferWorker", map);

    }

    public void updateNewDepartment(Long departmentId, String newDepartmentName){
        Map<String, Object> map = new HashMap<>();

        map.put("departmentId", departmentId);
        map.put("newDepartmentName", newDepartmentName);

        sqlSession.update("updateNewDepartment", map);

    }

    public Worker getHeadByIdWorker(Long id){
        return sqlSession.selectOne("getHeadByIdWorker", id);
    }

    public List<Worker> getWorkersByBirthday(Date birthday){
        return sqlSession.selectList("getWorkersByBirthday", birthday);
    }
    public List<Worker> getWorkersByFirstNameAndLastName(String firstName, String lastName){
        Map<String, Object> map = new HashMap<>();
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        return sqlSession.selectList("getWorkersByFirstNameAndLastName", map);
    }

    public Integer getIdPositionByName(String name){
        return sqlSession.selectOne("getIdPositionByName", name);
    }

    public List<Worker> getAllWorkers(){
        return sqlSession.selectList("getAllWorkers");
    }

    public List<Worker> getAllWorkersExceptWorkerById(Long id){
        return sqlSession.selectList("getAllWorkersExceptWorkerById", id);
    }

    public Worker getHeadByDepartmentIdExceptWorkerById(Long departmentId, Long id){

        Map<String, Object> map = new HashMap<>();
        map.put("departmentId", departmentId);
        map.put("id", id);
        return sqlSession.selectOne("getHeadByDepartmentIdExceptWorkerById", map);

    }




}
