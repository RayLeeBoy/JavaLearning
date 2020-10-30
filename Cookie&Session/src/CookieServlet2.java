import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookieServlet2")
public class CookieServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost...");

        // 设置格式
        response.setContentType("text/html;charset=utf-8");

        // 获取所有cookie
        Cookie[] cookies = request.getCookies();

        // 建立标记
        boolean flag = false;
        if (cookies != null && cookies.length > 0) {

            // 遍历cookie
            for (Cookie cookie : cookies) {
                String msg = cookie.getValue();
                String name = cookie.getName();

                // 如果有这个时间cookies, 表示用户不是第一次登录
                if (name.equals("lastTime")) {
                    flag = true;

                    // 重新设置登录时间
                    Date date = new Date();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String format = dateFormat.format(date);
                    String encode = URLEncoder.encode(format, "utf-8");
                    Cookie lastTime = new Cookie("lastTime", encode);
                    response.addCookie(lastTime);

                    // 向用户回写信息
                    PrintWriter writer = response.getWriter();
                    writer.write("欢迎回来! 您上次登录时间为"+ URLDecoder.decode(msg, "utf-8"));
                    break;
                }
                System.out.println(msg);
            }
        }

        // 如果用户第一次登录
        if (cookies == null || cookies.length == 0 || flag == false) {
            // 设置登录时间
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String format = dateFormat.format(date);
            String encode = URLEncoder.encode(format, "utf-8");
            Cookie lastTime = new Cookie("lastTime", encode);
            response.addCookie(lastTime);

            // 向用户回写信息
            PrintWriter writer = response.getWriter();
            writer.write("欢迎您!!!");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
