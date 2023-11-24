<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
	<%-- 
		<% ! %> : Khai báo biến
		<% %> : Thẻ xử lý logic code
		<% = %>: Xuất giá trị của biến ra HTML
	 --%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	<%! int count = 0; %>
	<% 
		count++;
	%>
	<body>
			Hello Servlet
			<% if(count % 2 == 0) {%>
			<h1 style="background-color: red" ><%}else{%></h1><%} %>
			<h1><%= count %></h1>
	</body>
</html>