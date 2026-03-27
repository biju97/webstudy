package kr.or.ddit.servlet03;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.servlet03.dto.DummyRequest;

@WebServlet("/03/request-data")
public class RequestDataReceiveServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		//request line : url,method,protocal
	String uri=	req.getRequestURI();
	String method=req.getMethod();
	String protocal=req.getProtocol();
	System.out.printf("protocal:%s,method:%s\n",protocal,uri,method);
	String queString=req.getQueryString();
	System.out.printf("query string:%s\n",queString);
//request header =accept=*content-*,user-agent...

		String accept=req.getHeader("accept");
		Locale acceptLaunguage= req.getLocale();
		String contentType=	req.getContentType();
		long contentLength=req.getContentLengthLong();
		String userAgent=	req.getHeader("user-agent");
		System.out.printf("contentLength:%d,contentType:%s\n",contentLength,contentType);
 //request body
	
//url encoding 문자열 파라미터 집합:{name=%EA%B8%B8%EB%8F%99&age=44	
	 Map<String,String[]>parameterMap=req.getParameterMap(); 
	  parameterMap.forEach((key,values)->{ //
	System.out.printf("key: %s,values:%s\n",key,Arrays.toString(values)); 
	});
		
		System.out.println("===========================================");
	 //		req.getReader().lines().forEach(System.out::println);
//
//		if("post".equalsIgnoreCase(method)&&contentType.contains("json")) {
//			receiveJsonPayload(req);
//			
//		}else if("post".equalsIgnoreCase(method)&&contentType.contains("urlencoded")) {
//			receiveParameters(req);
//		}else if("get".equalsIgnoreCase(method)) {
//			
//		}
	}
	/*
	 *  파라미터 맵을 수신하고 콘솔에 출력
	 *  get_query string
	 *  post+ url endoded content type
	 */

		private  void	receiveParameters(HttpServletRequest req){
	String name=req.getParameter("name");
			String ageParam=req.getParameter("age");
			int age=Integer.parseInt(ageParam);
			DummyRequest reqDto= new DummyRequest(name, age);
		
			System.out.printf("수신한 json 객체:$s \n",reqDto);
	}
		
		/*
		 * json payload를 수신하고 콘솔에 출력
		 * post+json content type
		 */
		
	private void receiveJsonPayload(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		
//		json 수신 -> 역직렬화 -> java object(xxxrequest dto)
		DummyRequest reqDtO=new Gson().fromJson(req.getReader(),DummyRequest.class);
		System.out.printf("수신한 json 객체:$s \n",reqDtO);
	}
}
