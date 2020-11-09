package plan;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/planCreate")
public class PlanCreate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");

        // 第二种方式 封装对象
        Plan createPlan = new Plan();

        // 获取所有请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(createPlan, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(createPlan.getName() + ":" + createPlan.getContent());

        PlanDao planDao = new PlanDao();

        Boolean isExist = planDao.check(createPlan);
        if (isExist) {
            System.out.println("计划已存在");
            Map<String, String> map = new HashMap<>();
            map.put("code", "1");
            map.put("msg", "计划已存在");
            response.getWriter().write((new JSONObject(map).toString()));

            return;
        } else {
            System.out.println("计划不存在");
        }

        Boolean isSuccess = planDao.create(createPlan);
        if (isSuccess) {
            Map<String, String> map = new HashMap<>();
            map.put("code", "0");
            map.put("msg", "计划创建成功");
            response.getWriter().write((new JSONObject(map).toString()));
            System.out.println("计划创建成功");

        } else {
            Map<String, String> map = new HashMap<>();
            map.put("code", "1");
            map.put("msg", "计划创建失败");
            response.getWriter().write((new JSONObject(map).toString()));
            System.out.println("计划创建失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
