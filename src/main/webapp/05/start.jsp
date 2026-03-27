<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>출발지(A)</h4>
<%
	pageContext.setAttribute("message", "PAGE 에 저장된 메시지");
	request.setAttribute("message", "REQUEST 에 저장된 메시지");
	session.setAttribute("message", "SESSION 에 저장된 메시지");
	application.setAttribute("message", "APPLICATION 에 저장된 메시지");

// 1. forward
	 String path ="/05/dest.jsp";
	//RequestDispatcher rd=request.getRequestDispatcher(path); 
	//rd.forward(request, response);
//2.include
	//rd.include(request, response);
//3.redirect 
String location =request.getContextPath() +path;
response.sendRedirect(location);


%>
<h1>A에서 만든 꼬릿말</h1>
</body>
</html>