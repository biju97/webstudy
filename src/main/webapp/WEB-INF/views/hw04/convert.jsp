<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
	<h4>변환 처리 결과 :${convertResult.formattedResult}</h4>
</div>

<form method="post" enctype="application/x-www-form-urlencoded">
   <input type="text" name="value" placeholder="변환 수치"/>
   <select name="from">
   <c:forEach items="${unitGroup}" var="entry">
      <c:set value="${entry.key }" var="unitType"/>
      <c:set value="${entry.value }" var="unitList"/>
      <optgroup label="${unitType }">
         <c:forEach items="${unitList }" var="unit">
            <option>${unit }</option>
         </c:forEach>
      </optgroup>
   </c:forEach>
   
   
   </select>
   <select name="to">
      <c:forEach items="${unitGroup}" var="entry">
         <c:set value="${entry.key }" var="unitType"/>
         <c:set value="${entry.value }" var="unitList"/>
         <optgroup label="${unitType }">
            <c:forEach items="${unitList }" var="unit">
               <option>${unit }</option>
            </c:forEach>
         </optgroup>
      </c:forEach>
      
      
   </select>
   
   <button type="submit">전송</button>
</form>
</body>
</html>