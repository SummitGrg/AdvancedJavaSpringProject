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
import com.appsoft.finalspringproject.service.DepartmentService;
import com.appsoft.finalspringproject.utils.DepartmentExcelView;
import com.appsoft.finalspringproject.utils.DepartmentPdfView;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService deptService;
	
	@GetMapping("/add")
	public String getDepartment() {
		
		return "DepartmentForm";
	}
	
	@PostMapping("/add")
	public String postDepartment(@ModelAttribute Department dept) {
		
		deptService.addDept(dept);
		
		return "DepartmentForm";
	}
	
	@GetMapping("/list")
	public String getAllDepartments(Model model) {
		model.addAttribute("dList", deptService.getAllDepts());
		return "DepartmentListForm";
	}
	
	@GetMapping("/delete")
	public String deleteDepartment(@RequestParam("id") int id) {
		
		deptService.deleteDept(id);
		return "redirect:list";
	}
	
	@GetMapping("/edit")
	public String editDepartment(@RequestParam("id") int id, Model model) {
		
		model.addAttribute("dept", deptService.getDeptById(id));
		return "DepartmentEditForm";
	}
	
	@PostMapping("/update")
	public String updateDepartment(@ModelAttribute Department dept) {
		
		deptService.updateDept(dept);
		return "redirect:list";
	}
	
	@GetMapping("/excel")
	public ModelAndView excel() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("dList",deptService.getAllDepts());
		mv.setView(new DepartmentExcelView());		
		return mv;
	}
	
	@GetMapping("/pdf")
	public ModelAndView pdf() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("dList",deptService.getAllDepts());
		mv.setView(new DepartmentPdfView());		
		return mv;
	}
}