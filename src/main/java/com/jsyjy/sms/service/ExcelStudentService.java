package com.jsyjy.sms.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.jsyjy.sms.pojo.Student;
import com.jsyjy.sms.utils.ExcelUtil;

@Service
public class ExcelStudentService {

	public void exportStudentInfo(List<Student> students) throws IOException {
		JSONArray jsonArray = new JSONArray();
		for (Student student : students) {
			jsonArray.add(student);
		}
		// 设置单元格的表头
		String title = "学生信息";

		// 设置单元格各个列的各个列的名字
		Map<String, String> headMap = new LinkedHashMap<String, String>();
		headMap.put("sno", "学号");
		headMap.put("sname", "姓名");
		headMap.put("ssex", "性别");
		headMap.put("classes", "班级");

		OutputStream outXlsx = new FileOutputStream("E://学生信息表.xlsx");
		System.out.println("正在导出xlsx....");
		ExcelUtil.exportExcelX(title, headMap, jsonArray, null, 0, outXlsx);
		outXlsx.close();
		System.out.println("导出xlsx完成....");
	}
}
