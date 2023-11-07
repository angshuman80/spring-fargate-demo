package com.demo.app.controller;


import com.demo.app.model.Employee;
import com.demo.app.repository.EmployeeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;
import org.assertj.core.api.Assertions.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RestControllerTest {

    @InjectMocks
    RestController restController;

    @Mock
    EmployeeRepository employeeRepository;


    public static final Logger log = LogManager.getLogger(RestControllerTest.class);
    @BeforeAll
    public static void init(){
        log.info("Starting of the test....");
    }
    @Test
    void testGetEmployees() {
        Employee employee1 = new Employee("Lokesh", 40, "2023-1-22");
        Employee employee2 = new Employee( "Angshuman", 44,"2022-1-22");
        List<Employee> list = new ArrayList<Employee>();
        list.addAll(Arrays.asList(employee1, employee2));
        when(employeeRepository.findAll()).thenReturn(list);
        // when
        ResponseEntity<List<Employee>> result = restController.getAllEmployees();

        Assertions.assertEquals(result.getStatusCode().value(),200);
    }

    @Test
    void testCreateEmployee() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Employee employee = new Employee();
        employee.setId(0);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee employeeToAdd = new Employee();

        employeeToAdd.setName("Angshuman");
        employeeToAdd.setAge(40);
        employeeToAdd.setDob("1987-1-22");
        ResponseEntity<Employee> result = restController.createEmployee(employeeToAdd);

        Assertions.assertEquals(result.getStatusCode().value(),201);
        Assertions.assertEquals(result.getBody().getId(),0);
    }

}
