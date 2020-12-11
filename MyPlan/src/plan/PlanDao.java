package plan;

import Utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class PlanDao {

    // 声明 JdbcTemplate 对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public Boolean check(Plan createPlan) {
        try {
            // 编写sql语句: 添加一行数据
            String sql = "select * from myPlan where name = ?";
            List<Plan> list = template.query(sql, new BeanPropertyRowMapper<>(Plan.class), createPlan.getName());
            if (list.size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean create(Plan createPlan) {
        try {
            // 编写sql语句: 添加一行数据
            String sql = "insert into myPlan (name, content) values (?, ?)";
            template.update(sql, createPlan.getName(), createPlan.getContent());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean addTime(Plan plan) {
        try {
            Date date = new Date();

            // 编写sql语句: 更新一行数据
            String sql = "update myPlan set totalMinutes = ?, count = ?, updateTime = ? where name = ?";
            template.update(sql, plan.getTotalMinutes(), plan.getCount(), date.toString(), plan.getName());

            String s = (new Date()).toString();
            sql = "insert into myPlanHistory (planName, createTime, duration) values (?, ?, ?)";
            template.update(sql, plan.getName(), s, plan.getDuration());

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean finished(Plan plan) {
        try {
            Date date = new Date();

            // 编写sql语句: 更新一行数据
            String sql = "update myPlan set status = ? where name = ?";
            template.update(sql, plan.getStatus(), plan.getName());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public List<Plan> get() {
        try {
            // 编写sql语句: 添加一行数据
            String sql = "select * from myPlan where status = '0'";
            List<Plan> list = template.query(sql, new BeanPropertyRowMapper<>(Plan.class));
            if (list.size() > 0) {
                return list;
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Plan> getFinishedPlan() {
        try {
            // 编写sql语句
            String sql = "select * from myPlan where status = '1'";
            List<Plan> list = template.query(sql, new BeanPropertyRowMapper<>(Plan.class));
            if (list.size() > 0) {
                return list;
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Plan> getPlanHistyory(String planName) {
        try {
            // 编写sql语句: 添加一行数据
            String sql = "select * from myPlanHistory where planName = ?";
            List<Plan> list = template.query(sql, new BeanPropertyRowMapper<>(Plan.class), planName);
            if (list.size() > 0) {
                return list;
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
