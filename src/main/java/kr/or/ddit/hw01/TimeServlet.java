package kr.or.ddit.hw01;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/now")
public class TimeServlet extends HttpServlet{
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //JSR-310 방식의 시간 API활용
      LocalDateTime now = LocalDateTime.now();
      String formatted = now.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM));
      
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
      String mime = "text/plain;charset=UTF-8";
      
      resp.setContentType(mime);
      //try with resource
      try (PrintWriter out = resp.getWriter();){
         out.print(html);
      }
      
   }
}
