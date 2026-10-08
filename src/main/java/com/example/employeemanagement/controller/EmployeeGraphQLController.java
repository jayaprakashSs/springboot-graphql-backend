package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeRequest;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class EmployeeGraphQLController {

    private final EmployeeService employeeService;

    @QueryMapping
    public List<Employee> employees() {
        return employeeService.getAllEmployees();
    }

    @QueryMapping
    public Employee employee(@Argument Long id) {
        return employeeService.getEmployeeById(id);
    }

    @MutationMapping
    public Employee createEmployee(@Argument("input") EmployeeRequest input) {
        return employeeService.createEmployee(input);
    }

    @MutationMapping
    public Employee updateEmployee(@Argument Long id, @Argument("input") EmployeeRequest input) {
        return employeeService.updateEmployee(id, input);
    }

    @MutationMapping
    public Map<String, Object> deleteEmployee(@Argument Long id) {
        employeeService.deleteEmployee(id);
        return Map.of("success", true, "message", "Employee deleted successfully");
    }
}
