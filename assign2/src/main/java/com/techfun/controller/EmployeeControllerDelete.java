package com.techfun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.techfun.service.EmployeeService;

@Controller
public class EmployeeControllerDelete {
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/delete/{id}")
	public ModelAndView deleteEmployee(@ModelAttribute("id") Integer id) {
		ModelAndView mav = new ModelAndView("redirect:/employeeList");
		employeeService.delete(id);
		return mav;
	}
}
