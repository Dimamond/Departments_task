package ru.bean;

import java.util.Date;

public class Worker {
    private String firstName;
    private String lastName;
    private String middleName;
    private boolean sex;
    private Date birthday;
    private String phoneNumber;
    private String email;
    private Date employmentDate;
    private Date dateOfDismissal;
    private String position;
    private int salary;
    private boolean headDepartment;



    public Worker() {
    }




    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public void setDateOfDismissal(Date dateOfDismissal) {
        this.dateOfDismissal = dateOfDismissal;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setHeadDepartment(boolean headDepartment) {
        this.headDepartment = headDepartment;
    }



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public boolean isSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public Date getDateOfDismissal() {
        return dateOfDismissal;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }

    public boolean isHeadDepartment() {
        return headDepartment;
    }
}
