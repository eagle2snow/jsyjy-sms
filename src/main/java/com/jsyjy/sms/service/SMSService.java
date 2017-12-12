package com.jsyjy.sms.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsyjy.sms.mapper.TClassMapper;
import com.jsyjy.sms.mapper.TStudentMapper;
import com.jsyjy.sms.pojo.TClass;
import com.jsyjy.sms.pojo.TStudent;

@Service
public class SMSService {

	@Autowired
	private TStudentMapper studentMapper;

	@Autowired
	private TClassMapper classMapper;

	private Collection<Object> collection;

	/**
	 * 学生列表信息
	 * 
	 * @return
	 */
	public List<TStudent> sms2list4student() {
		List<TStudent> students = studentMapper.selectByExample(null);
		return students;
	}

	/**
	 * 班级列表信息
	 * 
	 * @return
	 */
	public List<TClass> sms2list4class() {
		List<TClass> classes = classMapper.selectByExample(null);
		return classes;
	}

	/**
	 * 学生/班级列表信息
	 * 
	 * @return
	 */
	public Collection<Object> sms2list() {
		collection = null;
		List<TStudent> students = studentMapper.selectByExample(null);
		List<TClass> classes = classMapper.selectByExample(null);
		collection.addAll(students);
		collection.addAll(classes);
		return collection;
	}
}
