import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

@WebServlet("/parametersServlet")
public class ParametersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get - getParameter ...");

        System.out.println(request.getContentType());
        
        String username = request.getParameter("username");
        System.out.println(username);

        String password = request.getParameter("password");
        System.out.println(password);

        response.setContentType("application/json;charset=utf-8");

        HashMap<String, String> map = new HashMap<>();

        map.put("code", "0");
        map.put("msg", "success");

        JSONObject object = new JSONObject(map);

        response.getWriter().write(object.toString());

        /*
        System.out.println("get - getParameterMap ...");
        Map<String, String[]> map =  request.getParameterMap();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.println(key);
            String[] names = map.get(key);
            for (String name : names) {
                System.out.println(name);
            }
            System.out.println("------");
        }

        System.out.println("get - getParameterNames ...");
        Enumeration<String> enumeration = request.getParameterNames();
        while(enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getParameter(name);
            System.out.println(name + ":" + value);
        }

        System.out.println("get - getParameterValues ...");
        String[] hobbies = request.getParameterValues("hobby");

        for (String hobby : hobbies) {
            System.out.println(hobby);
        }*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
