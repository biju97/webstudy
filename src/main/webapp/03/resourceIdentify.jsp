
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>자원의 접근 경로에 따른 분류</h4>
<pre>
	FileSystem Resource :물리적인 절대 경로(file system path)를 통해 접근 할수 잇는 자원
	ex)	D:\00.medias\images\cat1.jpg
	Classpath Resource :classpath 이후의 qualified name 을 통해 접근 할 수잇는 자원
	ex)/kr/or/ddit/dummy.properties
	Web Resource :url 을 통해 접근할수잇는 네트워크에 공개된 자원 ,위치 경로
	 ex) http://localhost:80/WebStudy01/03/echo
	 
	 
	 URI (Uniform Resource identifier) : URL 을 포함한 자원의 식별 방법에 대한 포괄적인 명칭
	 URL(~LOCATOER)
	 protocol//host[:port][pathname]
	 	pathname=context path+resource path ,/ WebStudy01/03/resourceIdentify.jsp
	 	origin =protocol +host +port
	 	URL (젇대경로1)=origin+pathname <a href="https://www.naver.com/">네이버</a>
	 	URL (젇대경로2)=pathname( 동일출처의 자원에 접근하는 경우) 
	 	<a class="dummy" href="https://localhost:80/WebStudy01/03/data-form.jsp">data-form.jsp</a>
	 		<a class="dummy" href="/WebStudy01/03/data-form.jsp">data-form.jsp</a>
	 		<a class="dummy" href="">???</a>
	 		상대경로 : 현재 자원의 위치에 따라 절대 경로가 완성됨
	 				<a class="dummy" href="data-form.jsp">data-form.jsp</a>
	 	Sop(same origin policy)란 ? ui 자원을 제공한 출처(origin) 과 데이터를 제공한 출처(origin)이 동일해야함
</pre>
<script>
document.querySelectorAll(".dummy").forEach((a)=>console.log(a.href));
</script>
</body>
</html>