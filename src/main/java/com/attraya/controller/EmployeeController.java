package com.attraya.controller;

import com.attraya.annotation.LogRequestAndResponse;
import com.attraya.entity.Employee;
import com.attraya.service.EmployeeService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ObservationRegistry observationRegistry;

    @PostMapping
    @LogRequestAndResponse
    public String saveEmployee(@RequestBody Employee employee){
        /* Throwing exception to demonstrate @AfterThrowing */
        if (employee.getEmail().contains("gmail")){
            throw new RuntimeException("Gmail not allowed");
        }
        String newEmployee = employeeService.saveEmployee(employee);
        /* To view metrics, hit the request, then goto http://localhost:8080/actuator/metrics/saveEmployee */
//        Observation.createNotStarted("saveEmployee", observationRegistry)
//                .observe(()->newEmployee);

        return newEmployee;
    }

    @GetMapping
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("/2nd")
    public List<Employee> getEmployeesBy2ndApproach(){
        return employeeService.getEmployeesUsingBeanPropertyRowMapper();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/name/{id}")
    public String getNameById(@PathVariable int id){
        return employeeService.getNameById(id);
    }

    @GetMapping("/{name}/{dept}")
    public List<Employee> findEmployeesByNameAndDept(@PathVariable String name,@PathVariable String dept){
        return employeeService.findEmployeesByNameAndDept(name, dept);
    }

    @PutMapping
    @LogRequestAndResponse
    public String updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id){
        return employeeService.deleteEmployee(id);
    }
}
