package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *  웹 어플리케이션에서 사용자가 소비할  최종 ui 컨텐츠가 완성되는 엔드포인트에 따라 2가지 렌더링 구조가 있음
 *  Server Side Rendering :UI 컨텐츠가 서버 사이드에서 완성됨
 *  	UI 컨텐츠 => 템플릿 +데이터 (서버사이드)
 *  
 *  Model1 vs model2 :책임 분리 여부에 따른 아키텍처
 *  Model2 구조란?
 *   	:request 와 response 를 별개의 객체로 분리 처리하는 구조
 *   	request 처리 (컨트롤러,서블릿) : 요청 수신 및 검증 , 데이터 생성 
 *   						: 뷰로 전달할 데이터를 SCOPE 를 이용함
 *   						: 뷰로 혹은 또 다른 컨트롤러로 이동하기 위한 흐름제어 구문이 필요함
 *  	 response 처리(view ,JSP,THYMLEAF,MUSTACH)	: 템플릿과 데이터를 결합해 최종 컨텐츠 생성
 *  						: SCOPE 에서 꺼낸 데이터와 템플릿을 결합할 떄 일정한 규칙에 따라 결합하고 렌더링하는 도구 => 템플릿 엔진
 *   
 *  템플릿 엔진?
 *  Client Side Rendering :UI 컨텐츠가 클라이언트 사이드에서 완성됨
 * 		 UI 컨텐츠 => 템플릿 +데이터 (클라이언트 사이드)
 */
@WebServlet("/server-info")
public class RenderingDescServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 데이터: 서버의 기본 언어와 국가 (locale), 기본 타임존 
		//UI 컨텐츠 => 템플릿 +데이터 (서버사이드)
		
		Locale clientLocale=req.getLocale();
		Locale serverLocale=Locale.getDefault();
		String language =serverLocale.getDisplayLanguage(clientLocale);
		String country =serverLocale.getDisplayCountry(clientLocale);
		ZoneId serverTImezone=	ZoneId.systemDefault();
		String timeZone=	serverTImezone.getDisplayName(TextStyle.FULL, clientLocale);
		System.out.println(country);
//=========================================================================== (request 위 response 밑 => 구분해줌 ->모델2)
	
		req.setAttribute("language", language);
		req.setAttribute("country", country);
		req.setAttribute("timeZone", timeZone);
		
		
		req.getRequestDispatcher("/01/info.jsp").forward(req, resp);
		
	}

}
