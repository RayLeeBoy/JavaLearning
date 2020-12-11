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

@WebServlet("/planFinished")
public class PlanFinished extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        // 第二种方式 封装对象
        Plan plan = new Plan();

        // 获取所有请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(plan, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        PlanDao planDao = new PlanDao();

        Boolean isSuccess = planDao.finished(plan);
        if (isSuccess) {
            System.out.println("计划操作成功");
            Map<String, String> map = new HashMap<>();
            map.put("code", "0");
            map.put("msg", "计划操作成功");
            response.getWriter().write((new JSONObject(map).toString()));

            return;
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("code", "1");
            map.put("msg", "计划操作失败");
            response.getWriter().write((new JSONObject(map).toString()));
            System.out.println("计划操作失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
