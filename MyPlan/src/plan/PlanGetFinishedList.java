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
import java.util.List;
import java.util.Map;

@WebServlet("/planGetFinishedList")
public class PlanGetFinishedList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");

        PlanDao planDao = new PlanDao();

        List<Plan> planList = planDao.getFinishedPlan();
        if (planList == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", "0");
            map.put("msg", "操作成功");
            map.put("planList", null);
            response.getWriter().write((new JSONObject(map).toString()));
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("code", "0");
            map.put("msg", "操作成功");
            map.put("planList", planList);
            response.getWriter().write((new JSONObject(map).toString()));
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
