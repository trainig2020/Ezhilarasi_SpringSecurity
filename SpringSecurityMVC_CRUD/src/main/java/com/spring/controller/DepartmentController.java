package com.spring.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.Department;
import com.spring.model.Employee;
import com.spring.service.DepartmentService;
import com.spring.service.DepartmentServiceImpl;
import com.spring.service.EmployeeService;
import com.spring.service.EmployeeServiceImpl;

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService = new DepartmentServiceImpl();
	@Autowired
	private EmployeeService employeeService = new EmployeeServiceImpl();
	
	@RequestMapping("/")
	public String loginPage(){
		return "redirect:/listDept";
		
	}
	
	
	@RequestMapping("deptControl")
	public String deptControl(HttpServletRequest request){
		List<Department> lst = departmentService.getAllDepartments();
		request.setAttribute("deptLst", lst);
		request.setAttribute("hoser", "hseval");
		return "form";
	}
	
	@RequestMapping("home")
	public String listDeptId(HttpServletRequest request){
		List<Department> lst = departmentService.getAllDepartments();
		int deptId = lst.get(0).getDeptId();
		return "redirect:/listDeptName?deptId="+deptId;
		
		
	}
	@RequestMapping("listDeptName")
	public ModelAndView listDeptName(HttpServletRequest request, ModelAndView modelAndView){
		int id = Integer.parseInt(request.getParameter("deptId"));
		
		//Department department = departmentService.getDeptById(id);
		List<Department> lst = departmentService.getAllDepartments();
		List<Employee> empLst =   employeeService.getEmployeesByDept(id);
		//modelAndView.addObject("department", department);
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("empLst", empLst);
		modelAndView.addObject("deptLst", lst);
		modelAndView.addObject("empLst", empLst);
		modelAndView.addObject("home", "homemp");
		modelAndView.addObject("check", "checklist");
		modelAndView.setViewName("form");
		
		return modelAndView;
		
	}
	
	@RequestMapping(value = "listDept", method=RequestMethod.GET)
	public ModelAndView listDepartment(HttpServletRequest request) {

		List<Department> lst = departmentService.getAllDepartments();
		ModelAndView modelAndView = new ModelAndView("form");
		modelAndView.addObject("deptList", lst);
		
		HttpSession session = request.getSession();
		session.setAttribute("hoser", "hseval");
		session.setAttribute("deptList", lst);
		return modelAndView;
	}

	@RequestMapping(value = "/newDept", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model,HttpServletRequest request) {
		String Register  = "NewFormDept";
		HttpSession session1 = request.getSession();
		List<Department> lst =(List<Department>)session1.getAttribute("deptList");
		session1.setAttribute("deptList", lst);
		model.addObject("Register", Register);
		model.addObject("insertDept", "newDept");
		model.setViewName("form");
		Department department = new Department();
		model.addObject("department", department);
		session1.setAttribute("hoser", "hseval");
		return model;
	}

	@RequestMapping(value = "/saveDept", method = RequestMethod.POST)
	public ModelAndView saveDepartment( @ModelAttribute Department department, BindingResult result) {
		if(result.hasErrors()){
			ModelAndView mv = new ModelAndView("form");
			return mv;
		}
		else{
		Department department1 = new Department();
		department1.setDeptId(department.getDeptId());
		department1.setDeptName(department.getDeptName());
		departmentService.insertDepartment(department1);
		return new ModelAndView("redirect:/listDept");
		}
	}

	@RequestMapping(value = "/deleteDept", method = RequestMethod.GET)
	public ModelAndView deleteDepartment(HttpServletRequest request) {
		int departId = Integer.parseInt(request.getParameter("id"));
		departmentService.deleteDepartment(departId);
		return new ModelAndView("redirect:/listDept");
	}

	@RequestMapping(value = "/updateDept", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int departId = Integer.parseInt(request.getParameter("id"));
		HttpSession session2 = request.getSession();
		Department department = departmentService.getDeptById(departId);
		session2.setAttribute("department",department);
		List<Department> lst =(List<Department>) session2.getAttribute("deptList");
		session2.setAttribute("deptList", lst);
		ModelAndView model = new ModelAndView("form");
		model.addObject("deptList", lst);
		model.addObject("departId", departId);    
		return model;
	}
}
