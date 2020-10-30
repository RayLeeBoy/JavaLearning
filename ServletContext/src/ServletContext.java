import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletContext")
public class ServletContext extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("doPost...");
        // servletContext第一种获取方式
        javax.servlet.ServletContext context1 =  this.getServletContext();

        // servletContext第二种获取方式
        javax.servlet.ServletContext context2 =  request.getServletContext();
        System.out.println(this.getServletContext());
        System.out.println(request.getServletContext());
        if (context1 == context2) {
            System.out.println("一样");
        }
        
        System.out.println(request.getContentType());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
        System.out.println("doGet...");
    }

}
