package ru.mybatis.mapper;

import ru.bean.Worker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface WorkerMapper {


    Worker getHeadByDepartmentId(Long departmentId);
    Integer getCountWorkersByDepartmentId(Long departmentId);
    Integer getSumSalaryWorkersDepartmentByDepartmentId(Long departmentId);
    List<Worker> getWorkersDepartmentByDepartmentId(Long departmentId);
    void insertWorker(@Param("firstName")String firstName, @Param("lastName")String lastName, @Param("middleName")String middleName,
                      @Param("sex")Boolean sex, @Param("birthday")Date birthday, @Param("phoneNumber") String phoneNumber,
                      @Param("email")String email, @Param("employmentDate")Date employmentDate, @Param("position")String position,
                      @Param("salary")Integer salary, @Param("headDepartment")Boolean headDepartment, @Param("departmentId")Long departmentId);

    void updateWorker(@Param("id")Long id ,@Param("firstName")String firstName, @Param("lastName")String lastName, @Param("middleName")String middleName,
                      @Param("sex")Boolean sex, @Param("birthday")Date birthday, @Param("phoneNumber") String phoneNumber,
                      @Param("email")String email, @Param("employmentDate")Date employmentDate, @Param("dateOfDismissal")Date dateOfDismissal,
                      @Param("position")String position, @Param("salary")Integer salary, @Param("headDepartment")Boolean headDepartment);

    void updateDismissalWorker(@Param("id")Long id, @Param("dateOfDismissal")Date dateOfDismissal);
    void deleteWorker(Long id);
    Worker getWorkerById(Long id);
    void updateTransferWorker(@Param("id")Long id , @Param("nameDepartment")String nameDepartment);
    void updateNewDepartment(@Param("departmentId")Long departmentId, @Param("newDepartmentName")String newDepartmentName);
    Worker getHeadByIdWorker(Long id);
    List<Worker> getWorkersByBirthday(Date birthday);
    List<Worker> getWorkersByFirstNameAndLastName(@Param("firstName")String firstName, @Param("lastName")String lastName);

    Integer getIdPositionByName(String name);

    List<Worker> getAllWorkers();

    List<Worker> getAllWorkersExceptWorkerById(Long id);

    Worker getHeadByDepartmentIdExceptWorkerById(@Param("departmentId")Long departmentId, @Param("id")Long id);








}
