package kr.or.ddit.hw02;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hw02/worldtime/ui")
public class WorldTimeModel2UIServlet extends HttpServlet{
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1. 데이터 수집 :JVM 지원 모든 로케일, JVM 지원 모든 시간대
	   Locale[] locales = Locale.getAvailableLocales();
		Set<String>zoneSet=ZoneId.getAvailableZoneIds();
		
		
//        2. Locale[] -> localeMap, Set<String> -> zoneMap 서버사이드렌더링 (데이터 와 템플릿)
		Map<String,String>localeMap= new HashMap<>();
		for (Locale locale : locales) {
			String lanugaeTag=locale.toLanguageTag();
			String name=locale.getDisplayName(locale);
			if(name.isBlank()) continue;
			localeMap.put(lanugaeTag,name);
		}
		
		Map<String,String>zoneMap= new HashMap<>();
		for(String id:zoneSet) {
			ZoneId tz =ZoneId.of(id);
			String name=tz.getDisplayName(TextStyle.FULL, req.getLocale());
		zoneMap.put(id,name);
		}
		
		req.setAttribute("localeMap", localeMap);
		req.setAttribute("zoneMap", zoneMap);
		String view="/WEB-INF/views/hw02/world-time-ui.jsp";
		req.getRequestDispatcher(view).forward(req, resp);

   }
}