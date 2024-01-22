package com.attraya.service;

import com.attraya.annotation.LogRequestAndResponse;
import com.attraya.annotation.TrackExecutionTime;
import com.attraya.entity.Employee;
import com.attraya.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @TrackExecutionTime
    public String saveEmployee(Employee employee){
        int count = employeeRepository.save(employee);
        return "RECORD INSERTED! "+count;
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeesUsingBeanPropertyRowMapper(){
        return employeeRepository.getAllEmployees();
    }

    @LogRequestAndResponse
    public Employee getEmployeeById(int id){
        Employee getEmployee = null;
        try {
            getEmployee = employeeRepository.findById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Employee not available with id- "+id);
        }
        return getEmployee;
    }

    public String getNameById(int id){
        return employeeRepository.getNameById(id);
    }

    public List<Employee> findEmployeesByNameAndDept(String name, String dept){
        return employeeRepository.findByNameAndDept(name, dept);
    }

    public String updateEmployee(Employee employee){
        int count = employeeRepository.update(employee);
        return count+" RECORD UPDATED";
    }

    public String deleteEmployee(int id){
        int count = employeeRepository.delete(id);
        return count+" RECORD DELETED!";
    }

}
