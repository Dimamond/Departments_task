package ru.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import ru.Exception.MyInvalidArgumentException;
import ru.bean.Department;
import ru.bean.Worker;
import ru.service.Service;


import java.util.List;
import java.util.Map;

@RestController
@Api
public class Controller {

    @Autowired
    private Service service;

    @ApiOperation(value = "Создание департамента.", response = String.class)
    @RequestMapping(value = "/department/create", method = RequestMethod.GET)
    public String createDepartment(@RequestParam("name") String name,
                                   @RequestParam(value = "name_parent", required = false) String nameParent)throws Exception{
        service.createDepartment(name, nameParent);
        return "Департамент успещно создан.";

    }

    @ApiOperation(value = "Изменение наименования департамента.", response = String.class)
    @RequestMapping(value = "/department/{id}/rename", method = RequestMethod.GET)
    public String renameDepartment(@PathVariable Long id,
                                   @RequestParam("name") String name)throws Exception{
        service.renameDepartment(id, name);
        return "Департамент успешно переименован.";

    }

    @ApiOperation(value = "Удаление департамента.", response = String.class)
    @RequestMapping(value = "/department/{id}/delete", method = RequestMethod.GET)
    public String deleteDepartment(@PathVariable Long id)throws Exception{
        service.deleteDepartment(id);
        return "Департамент успешно удален.";
    }

    @ApiOperation(value = "Просмотр сведений о департаменте.", response = Map.class)
    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
    public Map<String, Object> selectDepartment(@PathVariable Long id)throws Exception{
        return service.selectDepartment(id);
    }

    @ApiOperation(value = "Предоставление информации о департаментах," +
            " находящихся в непосредственном подчинении данного департамента (на уровень ниже).",
            response = List.class)
    @RequestMapping(value = "/department/{id}/child", method = RequestMethod.GET)
    public List<Department> selectChildDepartment(@PathVariable Long id)throws Exception{
        return service.selectChildDepartment(id);
    }

    @ApiOperation(value = "Предоставление информации о всех департаментах," +
            " находящихся в подчинении данного департамента.",
            response = List.class)
    @RequestMapping(value = "/department/{id}/all_child", method = RequestMethod.GET)
    public List<Department> selectAllChildDepartment(@PathVariable Long id)throws Exception{
        return service.selectAllChildDepartment(id);
    }

    @ApiOperation(value = "Перенос департамента.", response = String.class)
    @RequestMapping(value = "/department/{id}/transfer", method = RequestMethod.GET)
    public String transferDepartment(@PathVariable Long id,
                                    @RequestParam(value = "name_parent", required = false) String nameParent)throws Exception{
        service.transferDepartment(id, nameParent);
        return "Департамент успешно перенесен.";
    }

    @ApiOperation(value = "Получение информации о всех вышестоящих департаментах данного департамента.",
            response = List.class)
    @RequestMapping(value = "/department/{id}/parent", method = RequestMethod.GET)
    public List<Department> selectParentDepartment(@PathVariable Long id)throws Exception{
        return service.selectParentDepartment(id);
    }

    @ApiOperation(value = "Поиск департамента по наименованию.", response = Department.class)
    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public Department selectDepartmentByName(@RequestParam("name") String name)throws Exception{
        return service.selectDepartmentByName(name);
    }

    @ApiOperation(value = "Получение информации о фонде заработной платы департамента.", response = Integer.class)
    @RequestMapping(value = "/department/{id}/salary", method = RequestMethod.GET)
    public Integer salaryDepartments(@PathVariable Long id)throws Exception{
        return service.salaryDepartment(id);
    }

    @ApiOperation(value = "Получение списка сотрудников департамента.", response = List.class)
    @RequestMapping(value = "/department/{id}/workers", method = RequestMethod.GET)
    public List<Worker> selectWorkersDepartment(@PathVariable Long id)throws Exception{
        return service.selectWorkersDepartment(id);
    }

    @ApiOperation(value = "Создание сотрудника департамента.", response = String.class)
    @RequestMapping(value = "/department/{id}/create_worker", method = RequestMethod.GET)
    public String createWorker(@PathVariable Long id,
                                   @RequestParam("fname") String fname, @RequestParam("lname") String lname,
                                   @RequestParam(value = "mname", required = false) String mname, @RequestParam("sex") Boolean sex,
                                   @RequestParam("birthday") String birthday, @RequestParam("pnumber") String pnumber,
                                   @RequestParam("email") String email, @RequestParam("edate") String edate,
                                   @RequestParam("position") String position, @RequestParam("salary") Integer salary,
                                   @RequestParam("hdepartment") Boolean hdepartment)throws Exception{

        validator(fname, lname, mname, pnumber, email);
        service.createWorker(fname, lname, mname, sex, birthday, pnumber, email, edate, position, salary, hdepartment, id);

        return "Работник департамента успещно создан.";

    }

    @ApiOperation(value = "Редактирование сведений о сотруднике департамента.", response = String.class)
    @RequestMapping(value = "/department/{departmentId}/update_worker{id}", method = RequestMethod.GET)
    public String updateWorker(@PathVariable Long id,
                                   @RequestParam("fname") String fname, @RequestParam("lname") String lname,
                                   @RequestParam(value = "mname", required = false) String mname, @RequestParam("sex") Boolean sex,
                                   @RequestParam("birthday") String birthday, @RequestParam("pnumber") String pnumber,
                                   @RequestParam("email") String email, @RequestParam("edate") String edate,
                                   @RequestParam(value = "ddate", required = false) String ddate,
                                   @RequestParam("position") String position, @RequestParam("salary") Integer salary,
                                   @RequestParam("hdepartment") Boolean hdepartment, @PathVariable Long departmentId)throws Exception{

        validator(fname, lname, mname, pnumber, email);
        service.updateWorker(id,fname, lname, mname, sex, birthday, pnumber, email, edate, ddate, position, salary, hdepartment, departmentId);

        return "Информация о работнике департамента успешно обновленна.";

    }


    @ApiOperation(value = "Увольнение сотрудника с указанием даты увольнения.", response = String.class)
    @RequestMapping(value = "/worker/{id}/dismissal", method = RequestMethod.GET)
    public String dismissalWorker(@PathVariable Long id,
                                  @RequestParam("ddate") String ddate)throws Exception{

        service.dismissalWorker(id, ddate);
        return "Сотрудник уволен.";

    }


    @ApiOperation(value = "Получение информации о сотруднике.", response = Worker.class)
    @RequestMapping(value = "/worker/{id}", method = RequestMethod.GET)
    public Worker selectWorker(@PathVariable Long id)throws Exception{
        return service.selectWorker(id);
    }

    @ApiOperation(value = "Перевод сотрудника из одного департамента в другой.", response = String.class)
    @RequestMapping(value = "/worker/{id}/transfer", method = RequestMethod.GET)
    public String transferWorker(@PathVariable Long id,
                                 @RequestParam("named") String named)throws Exception{

        service.transferWorker(id, named);
        return "Сотрудник успешно переведен";
    }

    @ApiOperation(value = "Перевод всех сотрудников департамента в другой департамент.", response = String.class)
    @RequestMapping(value = "/department/{id}/transfer_workers", method = RequestMethod.GET)
    public String transferWorkersDepartments(@PathVariable Long id,
                                             @RequestParam("named") String named)throws Exception{

        service.transferWorkersDepartments(id, named);
        return "Сотрудники департамента успешно переведены в другой департамент.";
    }

    @ApiOperation(value = "Получение информации о руководителе данного сотрудника.", response = Worker.class)
    @RequestMapping(value = "/worker/{id}/head", method = RequestMethod.GET)
    public Worker selectHeadWorker(@PathVariable Long id)throws Exception{
        return service.selectHeadWorker(id);
    }

    @ApiOperation(value = "Поиск сотрудников по атрибутам(имя и фамилия).", response = List.class)
    @RequestMapping(value = "/workers/fname_lname", method = RequestMethod.GET)
    public List<Worker> selectWorkersByFirstNameAndLastName(@RequestParam("fname") String fname,
                                                            @RequestParam("lname") String lname)throws Exception{
        validator(fname, lname, null, null, null);
        return service.selectWorkersByFirstNameAndLastName(fname, lname);
    }

    @ApiOperation(value = "Поиск сотрудников по атрибутам(день рождения).", response = List.class)
    @RequestMapping(value = "/workers/birthday", method = RequestMethod.GET)
    public List<Worker> selectWorkersByBirthday(@RequestParam("birthday") String birthday)throws Exception{
        return service.selectWorkersByBirthday(birthday);
    }


    private void validator(String fname, String lname, String mname, String phoneNumber, String email)throws MyInvalidArgumentException{
        String pattern1 = "^[А-Яа-я[-]]+";
        String pattern2 = "[[+][-]0-9[(][)]\\s]+";
        String pattern3 = "^(.+)@(.+)[.](com|ru)$";
        if(fname != null){
            if(!fname.matches(pattern1)){
                throw new MyInvalidArgumentException("ERROR: В качестве допустимых символов для атрибута “имя” допускается использование только русских символов и знака “-”.");
            }
        }
        if(lname != null){
            if(!lname.matches(pattern1)){
                throw new MyInvalidArgumentException("ERROR: В качестве допустимых символов для атрибута “фамилия” допускается использование только русских символов и знака “-”.");
            }

        }if(mname != null){
            if(!mname.matches(pattern1)){
                throw new MyInvalidArgumentException("ERROR: В качестве допустимых символов для атрибута “отчество” допускается использование только русских символов и знака “-”.");
            }

        }if(phoneNumber != null){
            if(!phoneNumber.matches(pattern2)){
                throw new MyInvalidArgumentException("ERROR: В качестве допустимых символов для атрибута “контактный телефон” могут использоваться символы “+-0123456789()” и пробел.");
            }

        }if(email != null){
            if(!email.matches(pattern3)){
                throw new MyInvalidArgumentException("ERROR: Значение атрибута “адрес электронной почты” должно быть валидным.");
            }
        }
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


