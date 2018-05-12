package ru.service;


import org.springframework.stereotype.Component;

import ru.Exception.MyInvalidArgumentException;
import ru.bean.Department;
import ru.bean.Worker;
import ru.mybatis.mapper.DepartmentMapper;
import ru.mybatis.mapper.WorkerMapper;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Component
public class Service {

    private final   DepartmentMapper departmentMapper;
    private final   WorkerMapper workerMapper;

    public Service(DepartmentMapper departmentMapper, WorkerMapper workerMapper) {
        this.departmentMapper = departmentMapper;
        this.workerMapper = workerMapper;
    }

    public void createDepartment(String name, String nameParent)throws Exception{
        Department department = null;

        department = departmentMapper.getDepartmentByName(name);
        if(department != null)throw new MyInvalidArgumentException("ERROR: Департамент с таким наименованием уже существует.");

        department = departmentMapper.getDepartmentByName(nameParent);
        if(department == null & nameParent != null)throw new MyInvalidArgumentException("ERROR: Родительского департамента с таким наименованием не существует.");

        departmentMapper.insertDepartment(name,new Date(), nameParent);
        departmentMapper.insertChangesDepartments(name, new Date(), "Департамент создан.");
    }

    public void renameDepartment(Long id, String name)throws Exception{
        Department department = departmentMapper.getDepartmentById(id);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким id не существует.");

        Department department1 = departmentMapper.getDepartmentByName(name);
        if(department1 != null)throw new MyInvalidArgumentException("ERROR: Департамента с таким наименованием уже существует.");

        departmentMapper.updateNameDepartment(id, name);
        departmentMapper.insertChangesDepartments(name, new Date(), String.format("Департамент переименован с '%s' на '%s'.", department.getName(), name));

    }


    public void deleteDepartment(Long id)throws Exception{
        Department department = departmentMapper.getDepartmentById(id);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким id и так не существует.");
        int count = workerMapper.getCountWorkersByDepartmentId(id);

        if(count > 0)throw new MyInvalidArgumentException("ERROR: В департаменте еще остались сотрудники.");

        departmentMapper.deleteDepartment(id);
        departmentMapper.insertChangesDepartments(department.getName(), new Date(), "Департамент удален.");

    }

    public Map<String, Object> selectDepartment(Long id)throws Exception{
        Department department = null;
        department = departmentMapper.getDepartmentById(id);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким id не существует.");

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("Department", department);
        map.put("HeadDepartment", workerMapper.getHeadByDepartmentId(id));
        map.put("CountWorkers", workerMapper.getCountWorkersByDepartmentId(id));

        return map;

    }

    public List<Department> selectChildDepartment(Long id)throws Exception{
        Department department = null;
        department = departmentMapper.getDepartmentById(id);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким id не существует.");

        return departmentMapper.getChildDepartmentsById(id);

    }

    public List<Department> selectAllChildDepartment(Long id)throws Exception{
        Department department = null;
        department = departmentMapper.getDepartmentById(id);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким id не существует.");

        return departmentMapper.getAllChildDepartmentsById(id);
    }

    public void transferDepartment(Long id, String nameParent)throws Exception{
        Department department = departmentMapper.getDepartmentById(id);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким id не существует.");

        Department department1 = departmentMapper.getDepartmentByName(nameParent);
        if(department1 == null & nameParent != null)throw new MyInvalidArgumentException("ERROR: Родительского департамента с таким наименованием не существует.");
        departmentMapper.updateParentDepartment(id, nameParent);
        departmentMapper.insertChangesDepartments(department.getName(), new Date(), String.format("У департемента новый родительский департамент '%s'.", nameParent));
    }

    public List<Department> selectParentDepartment(Long id)throws Exception{
        Department department = null;
        department = departmentMapper.getDepartmentById(id);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким id не существует.");

        return departmentMapper.getParentDepartmentsById(id);
    }

    public Department selectDepartmentByName(String name)throws Exception{
        Department department = null;
        department = departmentMapper.getDepartmentByName(name);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким наименованием не существует.");

        return department;
    }

    public Integer salaryDepartment(Long id)throws Exception{
        Department department = null;
        department = departmentMapper.getDepartmentById(id);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким id не существует.");

        return workerMapper.getSumSalaryWorkersDepartmentByDepartmentId(id);
    }

    public List<Worker> selectWorkersDepartment(Long id)throws Exception{
        Department department = null;
        department = departmentMapper.getDepartmentById(id);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким id не существует.");
        return workerMapper.getWorkersDepartmentByDepartmentId(id);

    }

    public void createWorker(String firstName, String lastName, String middleName, Boolean sex, String birthday,
                             String phoneNumber, String email, String employmentDate, String position, Integer salary,
                             Boolean headDepartment, Long departmentId)throws Exception{


        Department department = departmentMapper.getDepartmentById(departmentId);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким id не существует.");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setLenient(false);
        Date birthday1 = null;
        try {
            birthday1 = simpleDateFormat.parse(birthday);
        } catch (ParseException e) {
            throw new MyInvalidArgumentException("ERROR: Дата дня рождения введена не в соответствии с образцом 'dd-MM-yyyy'.");
        }
        Date employmentDate1 = null;
        try {
            employmentDate1 = simpleDateFormat.parse(employmentDate);
        } catch (ParseException e) {
            throw new MyInvalidArgumentException("ERROR: Дата приема на работу введена не в соответствии с образцом 'dd-MM-yyyy'.");
        }
        if(birthday1.compareTo(employmentDate1) > -1)throw new MyInvalidArgumentException("ERROR: День рождения позже чем дата приема на работу.");


        List<Worker> workers = workerMapper.getAllWorkers();
        for (int i = 0; i < workers.size(); i++){
            Worker worker = workers.get(i);
            if(worker.getPhoneNumber().equalsIgnoreCase(phoneNumber)){
                throw new MyInvalidArgumentException("ERROR: такой номер телефона уже есть в базе.");
            }
            if(worker.getEmail().equalsIgnoreCase(email)){
                throw new MyInvalidArgumentException("ERROR: такой email уже есть в базе.");
            }
        }

        Integer idPosition = workerMapper.getIdPositionByName(position);
        if(idPosition == null)throw new MyInvalidArgumentException("ERROR: Позиции с таким наименованием не существует.");

        if(salary < 9489)throw new MyInvalidArgumentException("ERROR: Заработная плата ниже МРОТ.");
        Worker head = workerMapper.getHeadByDepartmentId(departmentId);
        if(head != null && salary > head.getSalary())throw new MyInvalidArgumentException("ERROR: Заработная плата выше чем у руководителя.");

        if(headDepartment && head != null)throw new MyInvalidArgumentException("ERROR: Руководитель у департамента уже есть.");

        workerMapper.insertWorker(firstName, lastName, middleName, sex, birthday1, phoneNumber, email, employmentDate1,
                position, salary, headDepartment, departmentId);

    }

    public void updateWorker(Long id, String firstName, String lastName, String middleName, Boolean sex, String birthday,
                             String phoneNumber, String email, String employmentDate, String dateOfDismissal, String position, Integer salary,
                             Boolean headDepartment, Long departmentId)throws Exception{

        Worker worker = workerMapper.getWorkerById(id);
        if(worker == null)throw new MyInvalidArgumentException("ERROR: Работника с таким id не существует.");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setLenient(false);
        Date birthday1 = null;
        try {
            birthday1 = simpleDateFormat.parse(birthday);
        } catch (ParseException e) {
            throw new MyInvalidArgumentException("ERROR: Дата дня рождения введена не в соответствии с образцом 'dd-MM-yyyy'.");
        }
        Date employmentDate1 = null;
        try {
            employmentDate1 = simpleDateFormat.parse(employmentDate);
        } catch (ParseException e) {
            throw new MyInvalidArgumentException("ERROR: Дата приема на работу введена не в соответствии с образцом 'dd-MM-yyyy'.");
        }
        if(birthday1.compareTo(employmentDate1) > -1)throw new MyInvalidArgumentException("ERROR: День рождения позже чем дата приема на работу.");

        Date dateOfDismissal1 = null;
        if(dateOfDismissal != null){
            try {
                dateOfDismissal1 = simpleDateFormat.parse(dateOfDismissal);
            } catch (ParseException e) {
                throw new MyInvalidArgumentException("ERROR: Дата увольнения введена не в соответствии с образцом 'dd-MM-yyyy'.");
            }
            if(employmentDate1.compareTo(dateOfDismissal1) > -1)throw new MyInvalidArgumentException("ERROR: Дата приема на работу позже чем дата увольнения.");
        }

        List<Worker> workers = workerMapper.getAllWorkersExceptWorkerById(id);
        for (int i = 0; i < workers.size(); i++){
            Worker worker1 = workers.get(i);
            if(worker1.getPhoneNumber().equalsIgnoreCase(phoneNumber)){
                throw new MyInvalidArgumentException("ERROR: такой номер телефона уже есть в базе.");
            }
            if(worker1.getEmail().equalsIgnoreCase(email)){
                throw new MyInvalidArgumentException("ERROR: такой email уже есть в базе.");
            }
        }

        Integer idPosition = workerMapper.getIdPositionByName(position);
        if(idPosition == null)throw new MyInvalidArgumentException("ERROR: Позиции с таким наименованием не существует.");

        if(salary < 9489)throw new MyInvalidArgumentException("ERROR: Заработная плата ниже МРОТ.");

        Worker head = workerMapper.getHeadByDepartmentIdExceptWorkerById(departmentId, id);
        if(head != null && salary >= head.getSalary())throw new MyInvalidArgumentException("ERROR: Заработная плата выше чем у руководителя.");

        if(headDepartment && head != null)throw new MyInvalidArgumentException("ERROR: Руководитель у департамента уже есть.");

        workerMapper.updateWorker(id ,firstName, lastName, middleName, sex, birthday1, phoneNumber, email, employmentDate1,
               dateOfDismissal1 ,position, salary, headDepartment);

    }


    public void dismissalWorker(Long id, String dateOfDismissal)throws Exception {

        Worker worker = workerMapper.getWorkerById(id);
        if (worker == null) throw new MyInvalidArgumentException("ERROR: Работника с таким id не существует.");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setLenient(false);
        Date dateOfDismissal1 = null;
        try {
            dateOfDismissal1 = simpleDateFormat.parse(dateOfDismissal);
        } catch (ParseException e) {
            throw new MyInvalidArgumentException("ERROR: Дата увольнения введена не в соответствии с образцом 'dd-MM-yyyy'.");
        }
        if (worker.getEmploymentDate().compareTo(dateOfDismissal1) > -1)
            throw new MyInvalidArgumentException("ERROR: Дата приема на работу позже чем дата увольнения.");

        workerMapper.updateDismissalWorker(id, dateOfDismissal1);

    }

    public Worker selectWorker(Long id)throws Exception {

        Worker worker = workerMapper.getWorkerById(id);
        if (worker == null) throw new MyInvalidArgumentException("ERROR: Работника с таким id не существует.");

        return workerMapper.getWorkerById(id);
    }

    public void transferWorker(Long id, String nameDepartment)throws Exception {

        Worker worker = workerMapper.getWorkerById(id);
        if (worker == null) throw new MyInvalidArgumentException("ERROR: Работника с таким id не существует.");

        Department department = departmentMapper.getDepartmentByName(nameDepartment);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким наименованием не существует.");

        workerMapper.updateTransferWorker(id, nameDepartment);

    }

    public void transferWorkersDepartments(Long idDepartment, String newNameDepartment)throws Exception {

        Department department = departmentMapper.getDepartmentById(idDepartment);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким id не существует.");

        department = departmentMapper.getDepartmentByName(newNameDepartment);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким наименованием не существует.");

        workerMapper.updateNewDepartment(idDepartment, newNameDepartment);
    }

    public Worker selectHeadWorker(Long id)throws Exception {

        Worker worker = workerMapper.getWorkerById(id);
        if (worker == null) throw new MyInvalidArgumentException("ERROR: Работника с таким id не существует.");

        return workerMapper.getHeadByIdWorker(id);
    }

    public List<Worker> selectWorkersByFirstNameAndLastName(String firstName, String lastName)throws Exception {

        List<Worker> workers = workerMapper.getWorkersByFirstNameAndLastName(firstName, lastName);
        if (workers.size() == 0) throw new MyInvalidArgumentException("ERROR: Ни одного работника не найденно с такими данными.");

        return workers;
    }

    public List<Worker> selectWorkersByBirthday(String birthday)throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setLenient(false);
        Date birthday1 = null;
        try {
            birthday1 = simpleDateFormat.parse(birthday);
        } catch (ParseException e) {
            throw new MyInvalidArgumentException("ERROR: Дата дня рождения введена не в соответствии с образцом 'dd-MM-yyyy'.");
        }


        List<Worker> workers = workerMapper.getWorkersByBirthday(birthday1);
        if (workers.size() == 0) throw new MyInvalidArgumentException("ERROR: Ни одного работника не найденно с такими данными.");

        return workers;
    }

}
