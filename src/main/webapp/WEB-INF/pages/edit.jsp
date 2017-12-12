<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑页面</title>
<%
	pageContext.setAttribute("ctp", request.getContextPath());
%>
</head>
<body>
	<center>
		<h1>员工修改页面</h1>

		<form action="${ctp }/updatestu/${student.sno }" method="post">
		<input type="hidden" name="_method" value="put" />
			学号：<input type="text"value="${student.sno}" name="sno" /><br /> 
			名字:<input type="text" value="${student.sname}" name="sname" /><br /> 
			性别：<input type="text" value="${student.ssex}" name="ssex" /><br />
			班级：<input type="text" value="${student.classes}" name="classes" /><br />
			<input type="submit" value="提交" />
		</form>
	</center>
</body>
</html>