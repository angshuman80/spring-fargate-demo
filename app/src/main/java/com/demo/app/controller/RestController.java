package com.demo.app.controller;

import com.demo.app.model.Employee;
import com.demo.app.repository.EmployeeRepository;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RestController {
    private static final Logger log = LogManager.getLogger(RestController.class);
    @Autowired
    EmployeeRepository employeeRepository;
    @GetMapping("/health")
    public String health(){
        log.info("Making health request");
        return "Success";
    }
    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        log.info("Creating Employee --> "+employee);
        employeeRepository.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        log.info("Getting Employees ---");
        List<Employee> employeeList = employeeRepository.findAll();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/employees/{name}")
    public ResponseEntity<Employee> getEmployeesbyName(@PathVariable("name") String name){
        log.info("Getting Employees by name ---");
        Employee employee = employeeRepository.findEmployeeByName(name);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

}
