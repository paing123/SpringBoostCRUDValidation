package com.techfun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.techfun.model.Employee;
import com.techfun.service.EmployeeService;

@Controller
public class EmployeeControllerSearch {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = { "/searchEmployee" }, method = RequestMethod.GET)
	public String SearchEmployee(@ModelAttribute("employee") Employee employee, Model model) {
		String name = employee.getFirstName();

		if (name != null) {
			List<Employee> employees = employeeService.findByName(name);
			model.addAttribute("employees", employees);
			return "index";
		} else {
			return "index";
		}
	}
}
