package ru.service;



import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.bean.SalaryFund;
import ru.mybatis.mapper.DepartmentMapper;

import java.util.List;


@EnableScheduling
@Component
public class ScheduledTasksService {
    private final DepartmentMapper departmentMapper;

    public ScheduledTasksService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Scheduled(fixedRate = 300000)
    public void saveSalaryFundInDb(){
        List<SalaryFund> salaryFunds = departmentMapper.getAllSalaryFund();

        for (int i = 0; i < salaryFunds.size(); i++){
            SalaryFund salaryFund = salaryFunds.get(i);
            String nameDepartment = salaryFund.getDepartment().getName();
            int newSalary = salaryFund.getSalaryFund();
            SalaryFund salaryFundTable = departmentMapper.getSalaryFundFromSalaryFundTableByDepartmentName(nameDepartment);
            if(salaryFundTable == null)departmentMapper.insertSalaryFund(nameDepartment, newSalary);
            else{
                int salary = salaryFundTable.getSalaryFund();
                if(newSalary != salary){
                    departmentMapper.updateSalaryFund(nameDepartment, newSalary);
                }
            }
        }
    }
}
