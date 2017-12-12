<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生添加</title>
</head>
<body>
	<center>
		<h1>学生添加</h1>
		<%
			pageContext.setAttribute("ctp", request.getContextPath());
		%>
		<form:form action="${ctp }/addStu" modelAttribute="student"
			method="POST">
			<!-- path就是原来html-input的name项：需要写 
		path：
			1）、当做原生的name项
			2）、自动回显隐含模型中某个对象对应的这个属性的值
	-->
	sno:<form:input path="sno" />
			<br />
	sname:<form:input path="sname" />
			<br />
	ssex:<form:input path="ssex" />
			<br />
	classes:<form:input path="classes" />
			<br />
			<input type="submit" value="保存" />
		</form:form>


	</center>

</body>
</html>