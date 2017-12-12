package com.jsyjy.sms.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsyjy.sms.pojo.TClass;
import com.jsyjy.sms.pojo.TStudent;
import com.jsyjy.sms.service.SMSService;

@Controller
public class SMSController {

	@Autowired
	private SMSService smsService;

	/**
	 * 信息展示
	 */
	@RequestMapping(value = "sms2list0")
	public String sms2list(Model model) {
		List<TStudent> students = smsService.sms2list4student();
		model.addAttribute("Students", students);
		return "sms2list";
	}

	/**
	 * 信息展示
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "sms2list")
	public String sms2list4all(Model model) {
		Collection<Object> collection = smsService.sms2list();
		List<Object> lists = null;
		for (Object object : collection) {
			if (object.equals(TStudent.class)) {
				TStudent student = (TStudent) object;
				lists.add(student);
			}
			if (object.equals(TClass.class)) {
				TClass tClass = (TClass) object;
				lists.add(tClass);
			}

		}
		model.addAttribute("lists", lists);
		return "sms2list";
	}

	/**
	 * 修改信息
	 */
	@RequestMapping(value = "sms2list1")
	public String sms2update() {
		return "";
	}

	/**
	 * 删除信息
	 */
	@RequestMapping(value = "sms2list2")
	public String sms2delete() {
		return "";
	}

	/**
	 * 增加信息
	 */
	@RequestMapping(value = "sms2list3")
	public String sms2add() {
		return "";
	}
}
