package com.jsyjy.sms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsyjy.sms.pojo.Student;
import com.jsyjy.sms.service.ExcelStudentService;
import com.jsyjy.sms.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private ExcelStudentService excelStudentService;

	@RequestMapping("/hello")
	public String handle01() {
		return "success";
	}

	/**
	 * 列表信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public String queryStudentList(Model model) {
		List<Student> list = this.studentService.queryStudentList();
		model.addAttribute("students", list);
		return "list";

	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/stu/{sno}", method = RequestMethod.DELETE)
	public String deleteStu(@PathVariable("sno") Integer sno) {
		this.studentService.delete(sno);
		return "redirect:/students";
	}

	/**
	 * 去修改页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upstu/{sno}", method = RequestMethod.GET)
	public String getStu(@PathVariable("sno") Integer sno, Model model) {
		Student student = this.studentService.queryStudentById(sno);
		model.addAttribute("student", student);
		return "edit";
	}

	/**
	 * 暂时存储信息
	 * 
	 * @param id
	 * @param model
	 */
	@ModelAttribute
	public void myModelAttribute(
			@RequestParam(value = "sno", required = false) Integer id,
			Model model) {
		if (id != null) {
			Student student = this.studentService.queryStudentById(id);
			model.addAttribute("student", student);
		}
	}

	/**
	 * 修改信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updatestu/{sno}", method = RequestMethod.PUT)
	public String updateStu(@ModelAttribute("student") Student student) {
		this.studentService.save2update(student);
		return "redirect:/students";
	}

	/**
	 * 去添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/to_add_page")
	public String toAddPage(Model model) {
		model.addAttribute("student", new Student());
		return "add";
	}

	/**
	 * 添加学生
	 * 
	 * @param student
	 * @return
	 */
	@RequestMapping(value = "addStu", method = RequestMethod.POST)
	public String addStu(Student student) {
		if (student!=null) {
			this.studentService.save(student);
		}
		return "redirect:/students";
	}

	/**
	 * 导出学生信息
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "exportStudentInfo")
	public String exportStudentInfo(Model model) throws IOException {
		List<Student> students = this.studentService.queryStudentList();
		this.excelStudentService.exportStudentInfo(students);
		model.addAttribute("path", "E:/学生信息表.xlsx");
		return "export_success";

	}

}