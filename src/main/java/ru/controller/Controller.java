package ru.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import ru.bean.Department;
import ru.service.Service;


import java.util.List;
import java.util.Map;

@RestController
@Api
public class Controller {

    @Autowired
    private Service service;

    @ApiOperation(value = "create department", response = String.class)
    @RequestMapping(value = "/department/create")
    public String createDepartment(@RequestParam("name") String name,
                                   @RequestParam(value = "name_parent", required = false) String nameParent)throws Exception{
        service.createDepartment(name, nameParent);
        return "Департамент успещно создан.";

    }

    @ApiOperation(value = "rename department", response = String.class)
    @RequestMapping(value = "/department/{id}/rename")
    public String renameDepartment(@PathVariable Long id,
                                   @RequestParam("name") String name)throws Exception{
        service.renameDepartment(id, name);
        return "Департамент успешно переименован.";

    }

    @ApiOperation(value = "delete department", response = String.class)
    @RequestMapping(value = "/department/{id}/delete")
    public String deleteDepartment(@PathVariable Long id)throws Exception{
        service.deleteDepartment(id);
        return "Департамент успешно удален.";
    }

    @ApiOperation(value = "select department", response = Map.class)
    @RequestMapping(value = "/department/{id}")
    public Map<String, Object> selectDepartment(@PathVariable Long id)throws Exception{
        return service.selectDepartment(id);
    }

    @ApiOperation(value = "select child department", response = List.class)
    @RequestMapping(value = "/department/{id}/child")
    public List<Department> selectChildDepartment(@PathVariable Long id)throws Exception{
        return service.selectChildDepartment(id);
    }

    @ApiOperation(value = "select all child department", response = List.class)
    @RequestMapping(value = "/department/{id}/all_child")
    public List<Department> selectAllChildDepartment(@PathVariable Long id)throws Exception{
        return service.selectAllChildDepartment(id);
    }

    @ApiOperation(value = "transfer department", response = String.class)
    @RequestMapping(value = "/department/{id}/transfer")
    public String transferDepartment(@PathVariable Long id,
                                    @RequestParam(value = "name_parent", required = false) String nameParent)throws Exception{
        service.transferDepartment(id, nameParent);
        return "Департамент успешно перенесен.";
    }

    @ApiOperation(value = "select parent department", response = List.class)
    @RequestMapping(value = "/department/{id}/parent")
    public List<Department> selectParentDepartment(@PathVariable Long id)throws Exception{
        return service.selectParentDepartment(id);
    }

    @ApiOperation(value = "select department by name", response = Department.class)
    @RequestMapping(value = "/department")
    public Department selectDepartmentByName(@RequestParam("name") String name)throws Exception{
        return service.selectDepartmentByName(name);
    }

    @ApiOperation(value = "salary department", response = Integer.class)
    @RequestMapping(value = "/department/{id}/salary")
    public Integer salaryDepartments(@PathVariable Long id)throws Exception{
        return service.salaryDepartment(id);
    }


    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e){
        return e.getMessage();
    }



    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }


}
