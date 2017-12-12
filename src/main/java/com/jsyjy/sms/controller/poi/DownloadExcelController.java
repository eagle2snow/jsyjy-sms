package com.jsyjy.sms.controller.poi;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsyjy.sms.pojo.Student;
import com.jsyjy.sms.service.StudentService;

@Controller
public class DownloadExcelController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/download")
	@SuppressWarnings("all")
	// 我这是根据前端传来的起始时间来查询数据库里的数据，如果没有输入变量要求，保留前两个就行
	public String downstudents2excel(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// 导出的Excel头部，这个要根据自己项目改一下
		String[] headers = { "学号", "姓名", "性别", "班级" };

		// 查询出来的数据，根据自己项目改一下
		List<Student> dataset = studentService.queryStudentList();

		// 下面的完全不动就行了（Excel数据中不包含图片）

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet();
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 18);
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {

			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		// 遍历集合数据，产生数据行
		Iterator it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			Student t = (Student) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName,
							new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					String textValue = null;

					if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						textValue = sdf.format(date);
					} else {
						// 其它数据类型都当作字符串简单处理
						textValue = value.toString();
					}

					HSSFRichTextString richString = new HSSFRichTextString(
							textValue);
					HSSFFont font3 = workbook.createFont();
					// 定义Excel数据颜色
					font3.setColor(HSSFColor.BLUE.index);
					richString.applyFont(font3);
					cell.setCellValue(richString);

				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition",
				"attachment;filename=students.xls");// 默认Excel名称
		response.flushBuffer();
		workbook.write(response.getOutputStream());
		return "donwload2ok";
	}

}
