package plan;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/planHistoryGet")
public class PlanHistoryGet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        String planName = request.getParameter("planName");

        PlanDao planDao = new PlanDao();

        List<Plan> planHistory = planDao.getPlanHistyory(planName);

        if (planHistory == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", "0");
            map.put("msg", "操作成功");
            map.put("planHistory", null);
            System.out.println(new JSONObject(map));
            response.getWriter().write((new JSONObject(map).toString()));
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("code", "0");
            map.put("msg", "操作成功");
            map.put("planHistory", planHistory);
            System.out.println(new JSONObject(map));
            response.getWriter().write((new JSONObject(map).toString()));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
