package ru.service;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import ru.Exception.MyInvalidArgumentException;
import ru.bean.Department;
import ru.mybatis.mapper.DepartmentMapper;
import ru.mybatis.mapper.WorkerMapper;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Component
@MapperScan(basePackages = "ru.mybatis.mapper")
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
    }

    public void renameDepartment(Long id, String name)throws Exception{
        Department department = null;

        department = departmentMapper.getDepartmentById(id);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким id не существует.");

        department = departmentMapper.getDepartmentByName(name);
        if(department != null)throw new MyInvalidArgumentException("ERROR: Департамента с таким наименованием уже существует.");

        departmentMapper.updateNameDepartment(id, name);

    }


    public void deleteDepartment(Long id)throws Exception{
        Department department = null;
        department = departmentMapper.getDepartmentById(id);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким id и так не существует.");
        int count = workerMapper.getCountWorkersByDepartmentId(id);

        if(count > 0)throw new MyInvalidArgumentException("ERROR: В департаменте еще остались сотрудники.");

        departmentMapper.deleteDepartment(id);

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
        Department department = null;

        department = departmentMapper.getDepartmentById(id);
        if(department == null)throw new MyInvalidArgumentException("ERROR: Департамент с таким id не существует.");

        department = departmentMapper.getDepartmentByName(nameParent);
        if(department == null & nameParent != null)throw new MyInvalidArgumentException("ERROR: Родительского департамента с таким наименованием не существует.");
        departmentMapper.updateParentDepartment(id, nameParent);
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




}
