import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置编码

        // 第一种方式 封装对象
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        System.out.println(username + "-" + password);
//        User loginUser = new User();
//        loginUser.setUsername(username);
//        loginUser.setPassword(password);

        // 第二种方式 封装对象
        User loginUser = new User();

        // 获取所有请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();

        try {
            BeanUtils.populate(loginUser, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 判断用户是否存在
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);

        if (user == null) {
            request.getRequestDispatcher("/failureServlet").forward(request, response);
        } else {

//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/successServlet");
//            requestDispatcher.forward(request, response);
            request.setAttribute("state", "AlreadyLogin");
            String state = (String) request.getAttribute("state");

            request.setAttribute("user", user);
            request.getRequestDispatcher("/successServlet").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
