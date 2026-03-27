package kr.or.ddit.hw01;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * locale과 timezone 이라는 파라미터로 결정된,
 * locale 과 zoneId 에 따른 세계 시간 서비스
 * timezone=Europe/London
 * locale=ko_KR (locale code, language tag)
 */
@WebServlet("/hw01/worldtime")
public class WorldTimeServlet extends HttpServlet{
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      
      //JSR-310 방식의 시간 API활용
      //요청 파라미터로 변경
//      String timeZone = req.getParameter("timezone");
//      ZoneId zone = ZoneId.of(timeZone);
      
      ZoneId zone = Optional.ofNullable(req.getParameter("timezone"))
         .map(ZoneId::of)
         .orElse(ZoneId.systemDefault());
      
      
      LocalDateTime now = LocalDateTime.now(zone);
      
      //요청 파라미터로 변경
      //String localeParam = req.getParameter("locale");
      //Locale locale = Locale.forLanguageTag(localeParam);
      
      Locale locale = Optional.ofNullable( req.getParameter("locale"))
         .map(Locale::forLanguageTag)
         .filter(l -> !l.getLanguage().isBlank())
         .orElse(Locale.getDefault());
      
      String formatted = now.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM)
                     .localizedBy(locale)
            );
      
      //서버사이드 백앤드에서는 동적 응답 컨텐트(!)를 생성해야함
      //컨텐츠의 종류는 몇가지나 존재할까?
      
      String html = """
            <html>
               <body>
                  <h1>%s</h1>
               </body>
            </html>
            
            """.formatted(formatted);
      //MIME 은 컨텐츠의 종류와 인코딩 방식을 표현하는 문자열. maintype/subtype;charset=인토딩
      //text/html, text/javascript, text/css, text/plain;charset=UTF-8
      //image/png, video/mpeg
      String mime = "text/html;charset=UTF-8";
      
      resp.setContentType(mime);
      //try with resource
      try (PrintWriter out = resp.getWriter();){
         out.print(html);
      }
      
   }
}
