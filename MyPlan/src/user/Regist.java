package user;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@WebServlet("/regist")
public class Regist extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");

        // 第二种方式 封装对象
        User registUser = new User();

        // 获取所有请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(registUser, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
        System.out.println(registUser.getUsername() + ":" + registUser.getPassword());

        UserDao userDao = new UserDao();

        Boolean isExist = userDao.check(registUser);
        if (isExist) {
            System.out.println("用户已存在");
            Map<String, String> map = new HashMap<>();
            map.put("code", "1");
            map.put("msg", "用户已存在");
            response.getWriter().write((new JSONObject(map).toString()));

            return;
        } else {
            System.out.println("用户不存在");
        }

        Boolean isSuccess = userDao.regist(registUser);
        if (isSuccess) {
            Map<String, String> map = new HashMap<>();
            map.put("code", "0");
            map.put("msg", "注册成功");
            response.getWriter().write((new JSONObject(map).toString()));
            System.out.println("注册成功");

        } else {
            Map<String, String> map = new HashMap<>();
            map.put("code", "1");
            map.put("msg", "注册失败");
            response.getWriter().write((new JSONObject(map).toString()));
            System.out.println("注册失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
