package com.techfun.service;

import java.util.List;

import com.techfun.model.Employee;

public interface EmployeeService {

	List<Employee> findAll();

	void save(Employee employee);

	void delete(Integer id);

	void update(Employee employee);

	Employee findById(Integer id);
	
	List<Employee> findByName(String name);
}