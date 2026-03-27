package kr.or.ddit.hw03;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import com.google.gson.Gson;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 *    "D:/00.medias/images" 디렉토리의 이미지 파일의 목록을 2가지 컨텐츠로 서비스함
 *    컨텐츠의 종류 accept에 따라 결덩됨 (content negotiation)
 *    1. accept(text/html) : Model2/template engine 활용
 *    2. accept(application/json) : Gson/jackson json으로 marshalling(serialization) 함
 */
@WebServlet("/hw03/image-list")
public class ImageListContentNegotiationServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String directory = "D:/00.medias/images";
      
//      1. 해당 디렉토리내의 모든 파일의 목록 조회
      File folder = new File(directory);
      String[] imageFiles = folder.list((d,n)->{
         ServletContext application = getServletContext();
         
         return Optional.ofNullable(application.getMimeType(n))
               .filter(mime -> mime.startsWith("image/") )
               .isPresent();
         
      });
//      2. accept 해더 수신
      String accept = req.getHeader("accept");
//      3. accept해더에 따라 컨텐츠 협상
      if(accept.contains("json")) {
//         1) json : 마샬링
         resp.setContentType("application/json;charset=UTF-8");
         try(PrintWriter out = resp.getWriter()){
            new Gson().toJson(imageFiles, out);
         }
         
      }else {
//         2) html : setattribute로 데이터 전달 -> view 위치 결정 -> 포워딩
         req.setAttribute("imageFiles", imageFiles);
         String view = "/WEB-INF/views/hw03/image-list.jsp";
         req.getRequestDispatcher(view).forward(req, resp);
      }


   }
   
}
