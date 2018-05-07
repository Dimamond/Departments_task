package mybatis.mapper;

import bean.Department;
import bean.Worker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface WorkerMapper {
    Worker getWorkerByFirstNameAndLastName(@Param("firstName")String firstName, @Param("lastName")String lastName);
    List<Worker> getWorkersDepartmentByDepartmentName(String departmentName);
    Worker getHeadDepartmentByDepartmentName(String departmentName);
    List<Worker> getAllWorkers();
    List<Worker> getWorkersByBirthday(Date birthday);
    List<Worker> getWorkersByFirstName(String firstName);

    Worker getHeadByFirstNameAndLastNameWorker(@Param("firstName")String firstName, @Param("lastName")String lastName);

    Integer getSumSalaryWorkersDepartmentByDepartmentName(String departmentName);
    void insertWorker(@Param("firstName")String firstName, @Param("lastName")String lastName, @Param("middleName")String middleName,
                      @Param("sex")Boolean sex, @Param("birthday")Date birthday, @Param("phoneNumber") String phoneNumber,
                      @Param("email")String email, @Param("employmentDate")Date employmentDate, @Param("dateOfDismissal")Date dateOfDismissal,
                      @Param("position")String position, @Param("salary")Integer salary, @Param("headDepartment")Boolean headDepartment,
                      @Param("nameDepartment")String nameDepartment);

    void updateWorker (@Param("oldFirstName")String oldFirstName, @Param("oldLastName")String oldLastName,  @Param("firstName")String firstName,
                       @Param("lastName")String lastName, @Param("middleName")String middleName,
                       @Param("sex")Boolean sex, @Param("birthday")Date birthday, @Param("phoneNumber") String phoneNumber,
                       @Param("email")String email, @Param("employmentDate")Date employmentDate, @Param("dateOfDismissal")Date dateOfDismissal,
                       @Param("position")String position, @Param("salary")Integer salary, @Param("headDepartment")Boolean headDepartment,
                       @Param("nameDepartment")String nameDepartment);

    void updateDismissalWorker(@Param("firstName")String firstName, @Param("lastName")String lastName, @Param("dateOfDismissal")Date dateOfDismissal);
    void updateTransferWorker(@Param("firstName")String firstName, @Param("lastName")String lastName, @Param("nameDepartment")String nameDepartment);
    void updateNewDepartment(@Param("oldDepartment")String oldDepartment, @Param("newDepartment")String newDepartment);
    void updateHeadDepartment(@Param("firstName")String firstName, @Param("lastName")String lastName);
    void deleteWorker(@Param("firstName")String firstName, @Param("lastName")String lastName);


}
