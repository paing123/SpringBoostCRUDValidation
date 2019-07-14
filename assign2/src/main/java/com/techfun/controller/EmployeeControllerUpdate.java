package com.techfun.controller;

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
public class EmployeeControllerUpdate {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/update/{id}")
	public ModelAndView showUpdateEmployee(@ModelAttribute("id") int id) {
		Employee employee = employeeService.findById(id);
		ModelAndView mav = new ModelAndView("employeeEdit");
		mav.addObject("updateEmployee", employee);
		return mav;
	}
	
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public ModelAndView UpdateEmployee(@ModelAttribute("updateEmployee") Employee employee) {
		employeeService.update(employee);
		ModelAndView mav = new ModelAndView("redirect:/employeeList");
		return mav;
	}
}
