<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.time.LocalDateTime"%>
    <%
    response.setHeader("Cache-Control", "no-store");
    response.addHeader("Cache-Control", "no-catche");
    
    response.setIntHeader("Refresh", 1);
    

    
    %>
<%=LocalDateTime.now()%>