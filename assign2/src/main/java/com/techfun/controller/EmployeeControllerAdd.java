package com.techfun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.techfun.model.Employee;
import com.techfun.service.EmployeeService;

@Controller
public class EmployeeControllerAdd {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = { "/addEmployee" }, method = RequestMethod.GET)
	public String showEmployeePage(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employee";
	}

	@RequestMapping(value = { "/addEmployee" }, method = RequestMethod.POST)
	public ModelAndView addEmployee(@Validated @ModelAttribute("employee") Employee employee, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("redirect:/employeeList");
		
		if (bindingResult.hasErrors()) {
			ModelAndView mav1 = new ModelAndView("employee");
			return mav1;
		}
		employeeService.save(employee);
		return mav;
	}

}
