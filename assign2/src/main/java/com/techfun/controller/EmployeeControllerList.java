package com.techfun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.techfun.model.Employee;
import com.techfun.service.EmployeeService;

@Controller
public class EmployeeControllerList {
	
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = { "/","/employeeList" }, method = RequestMethod.GET)
	public ModelAndView listEmployee() {
		ModelAndView mav = new ModelAndView("index");
		Employee employee = new Employee();
		mav.addObject("employee", employee);
		List<Employee> employees = employeeService.findAll();
		mav.addObject("employees", employees);
		return mav;
	}
	
	@GetMapping("/detail/{id}")
	public ModelAndView showDetailEmployee(@ModelAttribute("id") int id) {
		Employee employee = employeeService.findById(id);
		ModelAndView mav = new ModelAndView("employeeDetail");
		mav.addObject("employeeDetail", employee);
		return mav;
	}
}
