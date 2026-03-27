<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="jakarta.tags.core"  prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>World Time Service</title>
</head>
<body>
	<h1>세계 시간 서비스(SSR)</h1>
	<form method="GET" action="${pageContext.request.contextPath}/hw02/worldtime">
		<label for="locale">로케일:</label> <select name="locale" >
		<c:forEach items="${localeMap }" var="entry">
			<option value="${entry.key}">${entry.value }</option>		
		</c:forEach>
		</select> <br>
		<br> <label for="timezone">타임존:</label> <select name="timezone"
			> 
			<c:forEach items="${zoneMap }" var="entry">
			<option value="${entry.key}">${entry.value }</option>		
		</c:forEach>
			
		</select> <br>
		<br>
		<button type="submit">시간 확인(Sync+SSR)</button>
	</form>
	<h1>세계 시간 서비스(CSR)</h1>
	<form method="GET" action="${pageContext.request.contextPath}/hw02/worldtime" id="async-form">
		<label for="locale">로케일:</label> 
		<select name="locale" id="locale">
	
		</select> <br>
		<br> <label for="timezone">타임존:</label>
		 <select name="timezone"id="timezone"> 
			
		</select> 
		<br><br>
		<button type="submit">시간 확인(aSync+CSR)</button>
	</form>
	<div id="result"></div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/app/hw03/world-time-cn.js"></script>
</body>
</html>