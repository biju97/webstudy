<%@page import="java.io.InputStream"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.URI"%>
<%@page import="kr.or.ddit.servlet03.EchoServlet"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Path"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
	FileSystem Resource :물리적인 절대 경로(file system path)를 통해 접근 할수 잇는 자원
	ex)	D:\00.medias\images\cat1.jpg
	
 	
<%
	String physicalPath="D:/00.medias/images/cat1.jpg";
	
		File cat1= new File("physicalPath");
	out.println(cat1.length());
	Path cat1Path=Paths.get("D:/00.medias/images/cat1.jpg");
	out.println(Files.size(cat1Path));
	Path destPath=Paths.get("D:/",cat1Path.getFileName().toString());
	out.println(destPath);
	Files.copy(cat1Path, destPath);
	
	%>
	
	Classpath Resource :classpath 이후의 qualified name 을 통해 접근 할 수잇는 자원
	ex)/kr/or/ddit/dummy.properties
	
	
	<%--
	String logicalPath="/kr/or/ddit/dummy.properties";
	URI classPathURI=	EchoServlet.class.getResource(logicalPath).toURI();
	Path classPathResPath=	Paths.get( classPathURI);
	out.println(classPathResPath);
	Path destPath=Paths.get("D:/",classPathResPath.getFileName().toString());
	out.println(destPath);
	Files.copy(classPathResPath, destPath);
	
	--%> 
	Web Resource :url 을 통해 접근할수잇는 네트워크에 공개된 자원 ,위치 경로
	 ex) https://pokeapi.co/api/v2/pokemon/ditto
<%-- <%
 	String logical= "https://pokeapi.co/api/v2/pokemon/ditto";
 	URL url =URI.create(logical).toURL();
	InputStream is=url.openStream();
	out.println(is.available());
	Path destPath=Paths.get("D:/", "ditto.json");
	Files.copy(is,destPath);
	out.println(destPath);
%>
 --%>


</pre>

</body>
</html>