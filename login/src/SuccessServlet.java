import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/successServlet")
public class SuccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        response.setContentType("text/html; charset=utf-8");
        //
        User user = (User) request.getAttribute("user");
//        System.out.println("success...");
//        System.out.println(user.getUsername() + ":" + user.getPassword());

        // 回写数据
        if (user != null) {
            response.getWriter().write("登录成功! " + user.getUsername() + ", 欢迎您!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
