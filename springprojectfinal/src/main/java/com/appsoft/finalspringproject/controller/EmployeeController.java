package com.appsoft.finalspringproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.appsoft.finalspringproject.model.Department;
import com.appsoft.finalspringproject.model.Employee;
import com.appsoft.finalspringproject.service.DepartmentService;
import com.appsoft.finalspringproject.service.EmployeeService;
import com.appsoft.finalspringproject.utils.DepartmentExcelView;
import com.appsoft.finalspringproject.utils.DepartmentPdfView;
import com.appsoft.finalspringproject.utils.EmpExcelView;
import com.appsoft.finalspringproject.utils.EmpPdfView;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	
	@GetMapping("/add")
	public String getEmployee(Model model) {
		model.addAttribute("dList", departmentService.getAllDepts());
		return "EmployeeForm";
	}
	
	@PostMapping("/add")
	public String postEmployee(@ModelAttribute Employee emp) {
		
		employeeService.addEmp(emp);
		
		return "redirect:add";
	}
	
	@GetMapping("/list")
	public String getAllEmployees(Model model) {
		model.addAttribute("eList", employeeService.getAllEmp());
		return "EmployeeListForm";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") int id) {
		
		employeeService.deleteEmp(id);
		return "redirect:list";
	}
	
	@GetMapping("/edit")
	public String editEmployee(@RequestParam("id") int id, Model model) {
		
		model.addAttribute("emp", employeeService.getEmpById(id));
		return "EmployeeEditForm";
	}
	
	@GetMapping("/view")
	public String viewEmployee(@RequestParam("id") int id, Model model) {
		
		model.addAttribute("emp", employeeService.getEmpById(id));
		return "EmployeeViewForm";
	}
	
	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute Employee emp) {
		
		employeeService.updateEmp(emp);
		return "redirect:list";
	}


	@GetMapping("/excel")
	public ModelAndView excel() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("eList",employeeService.getAllEmp());
		mv.setView(new EmpExcelView());		
		return mv;
	}
	
	
	@GetMapping("/pdf")
	public ModelAndView pdf() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("eList",employeeService.getAllEmp());
		mv.setView(new EmpPdfView());		
		return mv;
	}
	
}