package ru.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import ru.bean.Department;
import ru.bean.Worker;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@Api(value = "11", description = "1111")
public class HelloWorldController {



    @RequestMapping(value = "/")
    @ApiOperation(value = "hello", response = Map.class)
    public  Map<String, Object> helloWorld(){
        Map<String, Object> map = new HashMap<>();



        Department department = new Department();
        department.setName("Головной отдел");
        department.setDateOfCreation(new Date());

        map.put("Department", department);
        map.put("Head", new Worker());
        map.put("Count", 5);

        return map;
    }



}