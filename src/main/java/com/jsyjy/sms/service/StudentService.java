package com.jsyjy.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsyjy.sms.mapper.Student2Mapper;
import com.jsyjy.sms.mapper.StudentMapper;
import com.jsyjy.sms.pojo.Student;

@Service
public class StudentService {

	@Autowired
	private Student2Mapper student2Mapper;
	@Autowired
	private StudentMapper studentMapper;

	public List<Student> queryStudentList() {

		return this.student2Mapper.selectByExample(null);
	}

	public Student queryStudentById(Integer sno) {
		return this.studentMapper.selectByPrimaryKey(sno);
	}

	public void delete(Integer sno) {
		this.studentMapper.deleteByPrimaryKey(sno);

	}

	public void save(Student student) {
		this.studentMapper.insert(student);
	}

	public void save2update(Student student) {
		this.studentMapper.updateByPrimaryKey(student);
	}

}
