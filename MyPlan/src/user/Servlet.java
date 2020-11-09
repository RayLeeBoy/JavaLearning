package user;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/loginServlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 第二种方式 封装对象
        User loginUser = new User();

        // 获取所有请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap.toString());

        try {
            BeanUtils.populate(loginUser, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
        System.out.println("loginUser - " + loginUser.getUsername() + ":" + loginUser.getPassword());

        // 判断用户是否存在
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);

        if (user == null) {
            Map<String, String> map = new HashMap<>();
            map.put("code", "1");
            map.put("msg", "登录失败");
            response.getWriter().write((new JSONObject(map).toString()));
            System.out.println("用户不存在");
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("code", "0");
            map.put("msg", "登录成功");
            response.getWriter().write((new JSONObject(map).toString()));
            System.out.println("用户存在");
            System.out.println("user - " + user.getUsername() + ":" + user.getPassword());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
