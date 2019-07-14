package com.techfun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techfun.dao.EmployeeDao;
import com.techfun.model.Employee;

@Service("employeeService")
public class EmployeeServiceImp implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<Employee> findAll() {
		return employeeDao.findAll();
	}
	
	@Override
	public void save(Employee employee) {
		employeeDao.save(employee);
	}

	public void delete(Integer id) {
		employeeDao.delete(id);
	}

	public void update(Employee employee) {
		employeeDao.update(employee);
	}

	public Employee findById(Integer id) {
		return employeeDao.findById(id);
	}
	
	public List<Employee> findByName(String name) {
		return employeeDao.findByName(name);
	}
}
