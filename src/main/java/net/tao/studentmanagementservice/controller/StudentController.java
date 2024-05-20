package net.tao.studentmanagementservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import net.tao.studentmanagementservice.entity.Student;
import net.tao.studentmanagementservice.service.StudentService;

@Controller
public class StudentController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
	public String veiwHomePage(Model model) {
		logger.info("Started StudentController::veiwHomePage()::");
		return findPaginated(1, model);
	}
	
	@GetMapping("/showNewStudentForm")
	public String showNewStudentForm(Model model) {
		logger.info("Started StudentController::showNewStudentForm()::");
		Student student = new Student();
		model.addAttribute("student", student);
		logger.info("End StudentController::showNewStudentForm()::");
		return "new_student";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@Valid @ModelAttribute("student") Student student, 
			BindingResult bindingResult) {
		logger.info("Started StudentController::saveStudent()::studentName:: "+student.getName());
		if(bindingResult.hasErrors()) {
			logger.info("End StudentController::saveStudent()::With Validation errors");
			return "new_student";
		}else {
			studentService.saveStudent(student);
			logger.info("End StudentController::saveStudent()::studentName:: "+student.getName());
			return "redirect:/";
		}
	}
	
	@PostMapping("/updateStudent")
	public String updateStudent(@Valid @ModelAttribute("student") Student student, 
			BindingResult bindingResult) {
		logger.info("Started StudentController::updateStudent()::studentName:: "+student.getName());
		if(bindingResult.hasErrors()) {
			logger.info("End StudentController::updateStudent()::With Validation errors");
			return "update_student";
		}else {
			studentService.saveStudent(student);
			logger.info("End StudentController::updateStudent()::studentName:: "+student.getName());
			return "redirect:/";
		}
	}
	
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable long id, Model model) {
		logger.info("Started StudentController::showFormForUpdate()::studentId:: "+id);
		Student student = studentService.getStudentById(id);
		model.addAttribute("student", student);
		logger.info("End StudentController::showFormForUpdate()::studentId:: "+id);
		return "update_student";
	}
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable long id, Model model) {
		logger.info("Started StudentController::deleteStudent()::studentId:: "+id);
		studentService.deleteStudentById(id);
		logger.info("End StudentController::deleteStudent()::studentId:: "+id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable int pageNo, Model model) {
		logger.info("Started StudentController::findPaginated()::");
		int pageSize = 5;
		Page<Student> page = studentService.findPaginated(pageNo, pageSize);
		List<Student> studentList = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalRecords", page.getTotalElements());
		model.addAttribute("listStudents", studentList);
		logger.info("End StudentController::findPaginated()::");
		return "index";
	}
	
}
