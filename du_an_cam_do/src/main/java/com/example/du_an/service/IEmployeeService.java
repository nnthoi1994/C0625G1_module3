package com.example.du_an.service;

import com.example.du_an.dto.EmployeeDto;
import com.example.du_an.entity.Account;
import com.example.du_an.entity.Employee;
import com.example.du_an.entity.Login;

import java.util.List;

public interface IEmployeeService {
    List<EmployeeDto> findAlls();
    boolean add(Employee employee, Login login);
    boolean update(Employee employee);
    boolean delete(int employeeId);
    Employee findById(int employeeId);
    List<EmployeeDto> search(String keyword);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
}
